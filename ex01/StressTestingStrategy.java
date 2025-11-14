public class StressTestingStrategy implements RiskStrategy {

    @Override
    public String name() { return "Stress Testing"; }

    @Override
    public String calculate(RiskParameters p) {
        double shockedLoss = p.getPortfolioValue() * p.getMarketShockPct();
        double buffer = Math.max(0, 0.10 * shockedLoss);
        double estimate = shockedLoss + buffer;

        return "[Stress] Choque=" + (int)Math.round(p.getMarketShockPct()*100) +
               "%, Horizonte=" + p.getTimeHorizonDays() + "d, Perda Estimada=" +
               String.format("R$ %.2f", estimate);
    }
}
