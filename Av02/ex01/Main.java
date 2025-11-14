import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> losses = Arrays.asList(0.8, 1.2, 0.5, 2.1, 0.9, 1.7);
        RiskParameters params = new RiskParameters(
                1_000_000.00, // PV: R$ 1M
                0.99,         // 99% de confiança
                10,           // 10 dias de horizonte
                0.15,         // choque de 15% no stress
                losses
        );

        RiskEngine engine = new RiskEngine(RiskAlgorithmFactory.fromEnum(RiskAlgorithmFactory.Algo.VAR));
        System.out.println(engine.run(params));

        engine.setStrategyByName("es");
        System.out.println("\n--- Troca dinâmica para ES ---");
        System.out.println(engine.run(params));

        engine.setStrategyByName("stress");
        System.out.println("\n--- Troca dinâmica para Stress ---");
        System.out.println(engine.run(params));
    }
}
