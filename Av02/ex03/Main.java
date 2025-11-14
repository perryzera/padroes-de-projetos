public class Main {
    public static void main(String[] args) {
        UsinaNuclear usina = new UsinaNuclear(new DesligadaState());

        usina.avaliar(new SensorData(150, 120, 0.005, 0, true));

        usina.avaliar(new SensorData(320, 130, 0.008, 0, true)); 

        usina.avaliar(new SensorData(410, 155, 0.015, 40, true)); 

        usina.avaliar(new SensorData(420, 160, 0.02, 50, false));

        usina.avaliar(new SensorData(350, 140, 0.01, 0, true));

        usina.entrarManutencao();
        usina.avaliar(new SensorData(200, 120, 0.005, 0, true));

        usina.sairManutencao();

        usina = new UsinaNuclear(new AlertaVermelhoState());
        usina.avaliar(new SensorData(380, 150, 0.01, 0, true));  
        usina.avaliar(new SensorData(290, 140, 0.005, 0, true));

        usina = new UsinaNuclear(new AlertaAmareloState());

        usina.avaliar(new SensorData(450, 170, 0.03, 40, false)); 

        usina.avaliar(new SensorData(450, 170, 0.03, 40, false)); 
    }
}
