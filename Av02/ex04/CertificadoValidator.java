import java.util.concurrent.TimeUnit;

public class CertificadoValidator implements Validator {
    @Override public ValidatorId id(){ return ValidatorId.CERTIFICADO; }
    @Override public long timeoutMillis(){ return TimeUnit.SECONDS.toMillis(2); }
    @Override public boolean requiresPreviousPass(){ return false; }

    @Override
    public ValidationResult validate(NFeDocument doc, ValidationContext ctx) {
        long dias = (System.currentTimeMillis() - doc.emissaoMillis) / (1000L*60*60*24);
        if (dias > 365) return ValidationResult.fail(id(), "Certificado expirado (dummy).");
        if (doc.numero.startsWith("X")) return ValidationResult.fail(id(), "Certificado revogado (dummy).");
        return ValidationResult.ok(id(), "Certificado válido.");
    }
}
