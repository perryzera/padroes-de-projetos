public class SensorData {
    public final double temperaturaC;
    public final double pressaoBar;
    public final double radiacaoSievert;
    public final int segundosAcima400;
    public final boolean resfriamentoOperacional;

    public SensorData(double temperaturaC, double pressaoBar, double radiacaoSievert,
                      int segundosAcima400, boolean resfriamentoOperacional) {
        this.temperaturaC = temperaturaC;
        this.pressaoBar = pressaoBar;
        this.radiacaoSievert = radiacaoSievert;
        this.segundosAcima400 = segundosAcima400;
        this.resfriamentoOperacional = resfriamentoOperacional;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "T=" + temperaturaC + "C, P=" + pressaoBar + "bar, R=" + radiacaoSievert +
                "Sv, >400C=" + segundosAcima400 + "s, CoolOK=" + resfriamentoOperacional + "}";
    }
}
