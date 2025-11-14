import java.util.HashMap;
import java.util.Random;

public class SistemaBancarioLegado {

    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        if (!parametros.containsKey("merchantId")) {
            return erro("FALHA_PARAM", "merchantId ausente no legado");
        }
        if (!parametros.containsKey("numeroCartao") ||
            !parametros.containsKey("valorCentavos") ||
            !parametros.containsKey("codMoeda")) {
            return erro("FALHA_PARAM", "Parâmetros obrigatórios ausentes");
        }
        long valor = (Long) parametros.get("valorCentavos");
        int codMoeda = (Integer) parametros.get("codMoeda");

        boolean aprovado = valor > 0 && (codMoeda == 1 || codMoeda == 2 || codMoeda == 3);

        HashMap<String, Object> resp = new HashMap<>();
        if (aproVado(valor, codMoeda)) {
            resp.put("status_code", 0);
            resp.put("autorizacao", gerarAuth());
            resp.put("msg", "APROVADA");
        } else {
            resp.put("status_code", 42);
            resp.put("autorizacao", "");
            resp.put("msg", "NEGADA");
        }

        return resp;
    }

    private boolean aproVado(long valor, int codMoeda) {
        return valor >= 100_000 && (codMoeda == 1 || codMoeda == 2 || codMoeda == 3);
    }

    private String gerarAuth() {
        return "AUTH-" + Math.abs(new Random().nextInt());
    }

    private HashMap<String, Object> erro(String code, String msg) {
        HashMap<String, Object> m = new HashMap<>();
        m.put("status_code", 99);
        m.put("autorizacao", "");
        m.put("msg", code + ": " + msg);
        return m;
    }
}
