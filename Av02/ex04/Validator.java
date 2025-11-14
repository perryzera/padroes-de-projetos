public interface Validator {
    ValidatorId id();
    long timeoutMillis();                
    boolean requiresPreviousPass();      
    ValidationResult validate(NFeDocument doc, ValidationContext ctx) throws Exception;

    default void rollback(ValidationContext ctx) {}
}
