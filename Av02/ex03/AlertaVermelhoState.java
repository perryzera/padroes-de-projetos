public class AlertaVermelhoState implements PlantState {
    @Override public String nome() { return "ALERTA_VERMELHO"; }

    @Override
    public PlantState proximo(UsinaNuclear ctx, SensorData d) {
        if (!d.resfriamentoOperacional) return new EmergenciaState();
        if (d.temperaturaC <= 400 && d.pressaoBar < 160 && d.radiacaoSievert < 0.02) {
            return new AlertaAmareloState();
        }
        return null;
    }
}
