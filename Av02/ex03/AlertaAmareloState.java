public class AlertaAmareloState implements PlantState {
    @Override public String nome() { return "ALERTA_AMARELO"; }

    @Override
    public PlantState proximo(UsinaNuclear ctx, SensorData d) {
        if (d.temperaturaC > 400 && d.segundosAcima400 > 30) return new AlertaVermelhoState();
        if (d.temperaturaC <= 300 && d.pressaoBar < 150) return new OperacaoNormalState();
        return null;
    }
}
