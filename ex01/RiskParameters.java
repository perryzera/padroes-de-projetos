import java.util.List;

public class RiskParameters {
    private final double portfolioValue;   // valor do portfólio
    private final double confidenceLevel;  // ex.: 0.95, 0.99
    private final int timeHorizonDays;     // horizonte em dias
    private final double marketShockPct;   // choque hipotético (ex.: 0.12 = 12%)
    private final List<Double> historicalLosses; // perdas históricas (valores positivos)

    public RiskParameters(double portfolioValue,
                          double confidenceLevel,
                          int timeHorizonDays,
                          double marketShockPct,
                          List<Double> historicalLosses) {
        this.portfolioValue = portfolioValue;
        this.confidenceLevel = confidenceLevel;
        this.timeHorizonDays = timeHorizonDays;
        this.marketShockPct = marketShockPct;
        this.historicalLosses = historicalLosses;
    }

    public double getPortfolioValue() { return portfolioValue; }
    public double getConfidenceLevel() { return confidenceLevel; }
    public int getTimeHorizonDays() { return timeHorizonDays; }
    public double getMarketShockPct() { return marketShockPct; }
    public List<Double> getHistoricalLosses() { return historicalLosses; }

    @Override
    public String toString() {
        return "RiskParameters{" +
               "PV=" + portfolioValue +
               ", CL=" + confidenceLevel +
               ", H=" + timeHorizonDays +
               ", Shock=" + marketShockPct +
               ", Losses=" + (historicalLosses == null ? 0 : historicalLosses.size()) +
               '}';
    }
}