import java.util.concurrent.TimeUnit;

public class SefazServiceValidator implements Validator {
    @Override public ValidatorId id(){ return ValidatorId.SEFAZ; }
    @Override public long timeoutMillis(){ return TimeUnit.SECONDS.toMillis(4); }
    @Override public boolean requiresPreviousPass(){ return true; } 

    @Override
    public ValidationResult validate(NFeDocument doc, ValidationContext ctx) throws Exception {
        Thread.sleep(500);
        boolean ok = !doc.numero.endsWith("999"); 
        if (!ok) return ValidationResult.fail(id(), "SEFAZ negou a autorização (dummy).");
        return ValidationResult.ok(id(), "SEFAZ: autorização prévia ok (dummy).");
    }
}
