public class RiskEngine {
    private RiskStrategy strategy;

    public RiskEngine(RiskStrategy initial) {
        this.strategy = initial;
    }

    public void setStrategyByName(String name) {
        this.strategy = RiskAlgorithmFactory.fromName(name);
    }

    public void setStrategy(RiskStrategy strategy) {
        this.strategy = strategy;
    }

    public String run(RiskParameters params) {
        if (strategy == null) throw new IllegalStateException("Strategy não definida.");
        String header = ">> Rodando algoritmo: " + strategy.name() + "\n";
        return header + strategy.calculate(params);
    }
}
