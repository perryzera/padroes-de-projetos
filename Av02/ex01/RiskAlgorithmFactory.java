public class RiskAlgorithmFactory {

    public enum Algo { VAR, ES, STRESS }

    public static RiskStrategy fromName(String name) {
        if (name == null) return new ValueAtRiskStrategy();
        String n = name.trim().toLowerCase();
        switch (n) {
            case "var":
            case "valueatrisk":
            case "value_at_risk":
                return new ValueAtRiskStrategy();
            case "es":
            case "expectedshortfall":
            case "expected_shortfall":
                return new ExpectedShortfallStrategy();
            case "stress":
            case "stress_test":
            case "stresstesting":
                return new StressTestingStrategy();
            default:
                return new ValueAtRiskStrategy();
        }
    }

    public static RiskStrategy fromEnum(Algo algo) {
        switch (algo) {
            case ES: return new ExpectedShortfallStrategy();
            case STRESS: return new StressTestingStrategy();
            case VAR:
            default: return new ValueAtRiskStrategy();
        }
    }
}
