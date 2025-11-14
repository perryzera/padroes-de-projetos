import java.util.concurrent.TimeUnit;

public class BancoDadosValidator implements Validator {
    @Override public ValidatorId id(){ return ValidatorId.BANCO_DADOS; }
    @Override public long timeoutMillis(){ return TimeUnit.SECONDS.toMillis(2); }
    @Override public boolean requiresPreviousPass(){ return false; }

    @Override
    public ValidationResult validate(NFeDocument doc, ValidationContext ctx) {
        if (ctx.numerosPersistidos.contains(doc.numero)) {
            return ValidationResult.fail(id(), "NF-e duplicada no banco.");
        }
        ctx.numerosPersistidos.add(doc.numero);
        ctx.pushRollback(() -> ctx.numerosPersistidos.remove(doc.numero));
        return ValidationResult.ok(id(), "Número registrado no banco (reserva).");
    }

    @Override
    public void rollback(ValidationContext ctx) {
    }
}
