import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class SchemaValidator implements Validator {
    private static final Pattern MIN_XML = Pattern.compile("(?s)^\\s*<nfe>.*</nfe>\\s*$");

    @Override public ValidatorId id(){ return ValidatorId.SCHEMA; }
    @Override public long timeoutMillis(){ return TimeUnit.SECONDS.toMillis(2); }
    @Override public boolean requiresPreviousPass(){ return false; }

    @Override
    public ValidationResult validate(NFeDocument doc, ValidationContext ctx) {
        if (doc.xml == null || !MIN_XML.matcher(doc.xml).matches())
            return ValidationResult.fail(id(), "XML não atende ao XSD mínimo (dummy).");
        if (doc.numero == null || doc.numero.isEmpty())
            return ValidationResult.fail(id(), "Número ausente no XML.");
        return ValidationResult.ok(id(), "Schema válido.");
    }
}
