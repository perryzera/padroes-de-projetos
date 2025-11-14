public class ValidationResult {
    public final ValidatorId id;
    public final boolean success;
    public final boolean timeout;
    public final boolean skipped;
    public final String message;

    private ValidationResult(ValidatorId id, boolean success, boolean timeout, boolean skipped, String message) {
        this.id = id; this.success = success; this.timeout = timeout; this.skipped = skipped; this.message = message;
    }

    public static ValidationResult ok(ValidatorId id, String msg){ return new ValidationResult(id,true,false,false,msg); }
    public static ValidationResult fail(ValidatorId id, String msg){ return new ValidationResult(id,false,false,false,msg); }
    public static ValidationResult timedOut(ValidatorId id, String msg){ return new ValidationResult(id,false,true,false,msg); }
    public static ValidationResult skipped(ValidatorId id, String msg){ return new ValidationResult(id,false,false,true,msg); }

    @Override
    public String toString(){
        String s = id + " -> " + (skipped? "SKIPPED" : (success? "OK":"FAIL"));
        if (timeout) s += " (TIMEOUT)";
        return s + " | " + message;
    }
}
