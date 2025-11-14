import java.util.List;

public class ValueAtRiskStrategy implements RiskStrategy {

    @Override
    public String name() { return "Value at Risk (VaR)"; }

    @Override
    public String calculate(RiskParameters p) {
        double z = (p.getConfidenceLevel() >= 0.99) ? 2.33 :
                   (p.getConfidenceLevel() >= 0.975) ? 1.96 :
                   (p.getConfidenceLevel() >= 0.95) ? 1.65 : 1.0;

        List<Double> losses = p.getHistoricalLosses();
        double avg = 0.0;
        if (losses != null && !losses.isEmpty()) {
            for (double d : losses) avg += d;
            avg /= losses.size();
        }
        double var = 0.0;
        if (losses != null && losses.size() > 1) {
            for (double d : losses) var += Math.pow(d - avg, 2);
            var /= (losses.size() - 1);
        }
        double std = Math.sqrt(var);

        double oneDayVaR = p.getPortfolioValue() * (z * (0.001 + 0.1 * std)); 
        double scaled = oneDayVaR * Math.sqrt(Math.max(1, p.getTimeHorizonDays()));

        return "[VaR] CL=" + p.getConfidenceLevel() +
               ", Horizonte=" + p.getTimeHorizonDays() + "d, Estimativa=" +
               String.format("R$ %.2f", scaled);
    }
}
