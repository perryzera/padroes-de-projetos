import java.util.*;
import java.util.concurrent.*;

public class ChainExecutor {
    private final List<Validator> chain = new ArrayList<>();

    public ChainExecutor() {
        chain.add(new SchemaValidator());
        chain.add(new CertificadoValidator());
        chain.add(new RegrasFiscaisValidator()); 
        chain.add(new BancoDadosValidator());    
        chain.add(new SefazServiceValidator());  
    }

    public List<ValidationResult> run(NFeDocument doc, ValidationContext ctx) {
        List<ValidationResult> results = new ArrayList<>();
        ExecutorService pool = Executors.newSingleThreadExecutor();

        ctx.skipOnFailure.put(ValidatorId.SCHEMA, Arrays.asList(ValidatorId.CERTIFICADO));

        boolean allPrevPassedSoFar = true;
        Set<ValidatorId> toBeSkippedDueToPreviousFailure = new HashSet<>();

        for (Validator v : chain) {
            if (ctx.failures >= 3) {
                results.add(ValidationResult.skipped(v.id(), "Circuit breaker abriu após 3 falhas."));
                continue;
            }

            if (toBeSkippedDueToPreviousFailure.contains(v.id())) {
                results.add(ValidationResult.skipped(v.id(), "Pulado pois validador anterior falhou (regra condicional)."));
                continue;
            }

            if (v.requiresPreviousPass() && !allPrevPassedSoFar) {
                results.add(ValidationResult.skipped(v.id(), "Executa apenas se anteriores passarem."));
                continue;
            }

            Future<ValidationResult> fut = pool.submit(() -> v.validate(doc, ctx));
            ValidationResult r;
            try {
                r = fut.get(v.timeoutMillis(), TimeUnit.MILLISECONDS);
            } catch (TimeoutException te) {
                fut.cancel(true);
                r = ValidationResult.timedOut(v.id(), "Timeout de " + v.timeoutMillis() + " ms.");
            } catch (Exception e) {
                r = ValidationResult.fail(v.id(), "Exceção: " + e.getMessage());
            }

            results.add(r);

            if (r.success) {
            } else if (!r.skipped) {
                ctx.failures++;
                allPrevPassedSoFar = false;
                List<ValidatorId> lst = ctx.skipOnFailure.get(v.id());
                if (lst != null) toBeSkippedDueToPreviousFailure.addAll(lst);
            }
        }
        pool.shutdownNow();
        boolean houveFalha = results.stream().anyMatch(x -> !x.success && !x.skipped);
        if (houveFalha) {
            ctx.rollbackAll(); 
        }

        return results;
    }
}
