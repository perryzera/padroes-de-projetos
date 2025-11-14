import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpectedShortfallStrategy implements RiskStrategy {

    @Override
    public String name() { return "Expected Shortfall (ES)"; }

    @Override
    public String calculate(RiskParameters p) {
        List<Double> losses = (p.getHistoricalLosses() == null)
                ? new ArrayList<>()
                : new ArrayList<>(p.getHistoricalLosses());

        if (losses.isEmpty()) {
            return "[ES] Sem perdas históricas -> Estimativa base: R$ 0,00";
        }

        Collections.sort(losses, Collections.reverseOrder());
        int tailStart = (int)Math.floor(p.getConfidenceLevel() * losses.size());
        tailStart = Math.min(Math.max(0, tailStart), losses.size()-1);

        double sumTail = 0.0;
        int n = 0;
        for (int i = tailStart; i < losses.size(); i++) {
            sumTail += losses.get(i);
            n++;
        }
        double avgTailLoss = (n > 0) ? (sumTail / n) : 0.0;

        double estimate = p.getPortfolioValue() * (0.005 + 0.05 * avgTailLoss)
                          * Math.sqrt(Math.max(1, p.getTimeHorizonDays()));

        return "[ES] CL=" + p.getConfidenceLevel() +
               ", Cauda(" + n + " amostras), Estimativa=" +
               String.format("R$ %.2f", estimate);
    }
}
