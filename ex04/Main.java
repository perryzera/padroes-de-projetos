import java.util.List;

public class Main {
    public static void main(String[] args) {
        NFeDocument ok = new NFeDocument("2025-0001", "<nfe><numero>2025-0001</numero></nfe>", "BRL", System.currentTimeMillis());
        NFeDocument ruimXml = new NFeDocument("2025-0002", "<nota>sem-nfe</nota>", "BRL", System.currentTimeMillis());
        NFeDocument negadaSefaz = new NFeDocument("2025-0999", "<nfe><numero>2025-0999</numero></nfe>", "BRL", System.currentTimeMillis());

        ChainExecutor exec = new ChainExecutor();

        System.out.println("==== Caso 1: Documento OK ====");
        ValidationContext ctx1 = new ValidationContext();
        List<ValidationResult> r1 = exec.run(ok, ctx1);
        r1.forEach(System.out::println);
        System.out.println("BD contem 2025-0001? " + ctx1.numerosPersistidos.contains("2025-0001"));

        System.out.println("\n==== Caso 2: XML inválido -> aciona regras de pulo e falhas ====");
        ValidationContext ctx2 = new ValidationContext();
        List<ValidationResult> r2 = exec.run(ruimXml, ctx2);
        r2.forEach(System.out::println);
        System.out.println("Circuit breaker abriu? falhas=" + ctx2.failures);

        System.out.println("\n==== Caso 3: SEFAZ nega -> rollback do BD ====");
        ValidationContext ctx3 = new ValidationContext();
        List<ValidationResult> r3 = exec.run(negadaSefaz, ctx3);
        r3.forEach(System.out::println);
        System.out.println("BD contem 2025-0999? " + ctx3.numerosPersistidos.contains("2025-0999"));
    }
}
