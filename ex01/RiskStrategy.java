public interface RiskStrategy {
    String name();
    String calculate(RiskParameters params);
}
