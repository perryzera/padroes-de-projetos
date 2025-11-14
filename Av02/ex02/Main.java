public class Main {
    public static void main(String[] args) {
        SistemaBancarioLegado legado = new SistemaBancarioLegado();

        ProcessadorTransacoes processador = new AdapterProcessadorTransacoes(
                legado,
                "MERCHANT-ABC-123", 
                "ECOM"              
        );
        TransacaoResposta r1 = processador.autorizar("4111111111111111", 1500.00, "BRL");
        System.out.println("Resposta 1: " + r1);

        TransacaoResposta r2 = processador.autorizar("4111111111111111", 500.00, "USD");
        System.out.println("Resposta 2: " + r2);

        TransacaoResposta r3 = processador.autorizar("4000000000000002", 2000.00, "JPY");
        System.out.println("Resposta 3: " + r3);
    }
}
