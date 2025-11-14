public interface PlantState {
    String nome();
    PlantState proximo(UsinaNuclear ctx, SensorData dados);

    default boolean podeEntrarDe(PlantState anterior, UsinaNuclear ctx, SensorData dados) { return true; }
}
