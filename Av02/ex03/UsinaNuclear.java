import java.util.ArrayDeque;
import java.util.Deque;

public class UsinaNuclear {
    private PlantState estadoAtual;
    private final Deque<String> historico = new ArrayDeque<>();
    private ManutencaoState estadoManutencao; 

    public UsinaNuclear(PlantState inicial) {
        this.estadoAtual = inicial;
        registrar(estadoAtual.nome());
    }

    public PlantState getEstadoAtual() { return estadoAtual; }

    public void avaliar(SensorData dados) {
        if (estadoManutencao != null) {
            PlantState prox = estadoManutencao.proximo(this, dados);
            if (prox != null) setEstadoInterno(prox, dados);
            return;
        }
        PlantState prox = estadoAtual.proximo(this, dados);
        if (prox != null) setEstado(prox, dados);
    }

    public void setEstado(PlantState novo, SensorData dados) {
        if (!novo.podeEntrarDe(estadoAtual, this, dados)) {
            System.out.println(">> BLOQUEADO: Transição " + estadoAtual.nome() + " -> " + novo.nome() + " reprovada por regra do destino.");
            return;
        }
        if (isFlipFlop(estadoAtual.nome(), novo.nome())) {
            System.out.println(">> BLOQUEADO: Transição circular " + estadoAtual.nome() + " -> " + novo.nome() + " detectada.");
            return;
        }
        setEstadoInterno(novo, dados);
    }

    private void setEstadoInterno(PlantState novo, SensorData dados) {
        System.out.println(">> TRANSIÇÃO: " + estadoAtual.nome() + " -> " + novo.nome() + " | " + dados);
        this.estadoAtual = novo;
        registrar(novo.nome());
    }

    private boolean isFlipFlop(String atual, String alvo) {
        if (historico.size() < 1) return false;
        String ultimo = historico.peekLast(); 
        if (ultimo == null) return false;
        if (historico.size() >= 2) {
            String penultimo = historico.toArray(new String[0])[historico.size()-2];
            return penultimo.equals(alvo) && ultimo.equals(atual);
        }
        return false;
    }

    private void registrar(String nome) {
        if (historico.size() == 8) historico.removeFirst();
        historico.addLast(nome);
    }

    public void entrarManutencao() {
        if (estadoManutencao != null) return;
        estadoManutencao = new ManutencaoState(estadoAtual);
        System.out.println(">> MODO MANUTENÇÃO ATIVADO (sobrepondo estados normais).");
    }

    public void sairManutencao() {
        if (estadoManutencao == null) return;
        PlantState retornar = estadoManutencao.getEstadoAnterior();
        estadoManutencao = null;
        System.out.println(">> MODO MANUTENÇÃO DESATIVADO. Voltando para: " + retornar.nome());
        this.estadoAtual = retornar;
        registrar(estadoAtual.nome());
    }
}
