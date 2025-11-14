public class DesligadaState implements PlantState {
    @Override public String nome() { return "DESLIGADA"; }

    @Override
    public PlantState proximo(UsinaNuclear ctx, SensorData d) {
        boolean seguro = d.temperaturaC < 200 && d.pressaoBar < 150 && d.radiacaoSievert < 0.01;
        if (seguro) return new OperacaoNormalState();
        return null;
    }
}
