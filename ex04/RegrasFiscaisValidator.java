import java.util.concurrent.TimeUnit;

public class RegrasFiscaisValidator implements Validator {
    @Override public ValidatorId id(){ return ValidatorId.REGRAS_FISCAIS; }
    @Override public long timeoutMillis(){ return TimeUnit.SECONDS.toMillis(3); }
    @Override public boolean requiresPreviousPass(){ return true; } 

    @Override
    public ValidationResult validate(NFeDocument doc, ValidationContext ctx) {
        doc.impostoCalculado = "BRL".equalsIgnoreCase(doc.moeda) ? 0.17 : 0.0;
        return ValidationResult.ok(id(), "Imposto calculado (dummy): " + (doc.impostoCalculado*100) + "%");
    }
}
