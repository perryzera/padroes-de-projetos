public class OperacaoNormalState implements PlantState {
    @Override public String nome() { return "OPERACAO_NORMAL"; }

    @Override
    public PlantState proximo(UsinaNuclear ctx, SensorData d) {
        if (d.temperaturaC > 300) return new AlertaAmareloState();
        return null; 
    }
}
