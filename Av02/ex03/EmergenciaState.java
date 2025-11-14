public class EmergenciaState implements PlantState {
    @Override public String nome() { return "EMERGENCIA"; }

    @Override
    public PlantState proximo(UsinaNuclear ctx, SensorData d) {
        return null;
    }

    @Override
    public boolean podeEntrarDe(PlantState anterior, UsinaNuclear ctx, SensorData d) {
        return anterior != null && "ALERTA_VERMELHO".equals(anterior.nome());
    }
}
