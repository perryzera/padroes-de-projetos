import java.util.HashMap;
import java.util.Locale;

public class AdapterProcessadorTransacoes implements ProcessadorTransacoes {

    private final SistemaBancarioLegado legado;
    private final String merchantIdObrigatorio;
    private final String canal; 
    public AdapterProcessadorTransacoes(SistemaBancarioLegado legado, String merchantIdObrigatorio, String canal) {
        this.legado = legado;
        this.merchantIdObrigatorio = merchantIdObrigatorio;
        this.canal = (canal == null || canal.isEmpty()) ? "ECOM" : canal;
    }

    @Override
    public TransacaoResposta autorizar(String cartao, double valor, String moeda) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("numeroCartao", cartao);
        map.put("valorCentavos", toCentavos(valor));
        map.put("codMoeda", mapMoeda(moeda));
        map.put("merchantId", merchantIdObrigatorio); 
        map.put("canal", canal);                      

        HashMap<String, Object> respostaLegado = legado.processarTransacao(map);
        return converterResposta(respostaLegado, moeda);
    }

    private long toCentavos(double valor) {
        return Math.round(valor * 100.0d);
    }
    private int mapMoeda(String moeda) {
        if (moeda == null) return 3;
        String m = moeda.trim().toUpperCase(Locale.ROOT);
        switch (m) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL":
            case "R$":
            case "REAL":
                return 3;
            default: 
                return 3;
        }
    }

    private TransacaoResposta converterResposta(HashMap<String, Object> respLegado, String moedaOriginal) {
        int status = safeInt(respLegado.get("status_code"), 99);
        String auth = String.valueOf(respLegado.getOrDefault("autorizacao", ""));
        String msg = String.valueOf(respLegado.getOrDefault("msg", "SEM_MENSAGEM"));

        boolean aprovado = (status == 0);
        return new TransacaoResposta(aprovado, auth, msg, moedaOriginal);
    }

    private int safeInt(Object o, int def) {
        if (o == null) return def;
        if (o instanceof Integer) return (Integer)o;
        try { return Integer.parseInt(o.toString()); } catch (Exception e) { return def; }
    }
}
