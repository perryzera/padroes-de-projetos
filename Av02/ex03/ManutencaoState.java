public class ManutencaoState implements PlantState {
    private final PlantState estadoAnterior;

    public ManutencaoState(PlantState anterior) {
        this.estadoAnterior = anterior;
    }

    public PlantState getEstadoAnterior() { return estadoAnterior; }

    @Override public String nome() { return "MANUTENCAO"; }

    @Override
    public PlantState proximo(UsinaNuclear ctx, SensorData d) {

        return null;
    }

    @Override
    public boolean podeEntrarDe(PlantState anterior, UsinaNuclear ctx, SensorData d) {
        return true;
    }
}
