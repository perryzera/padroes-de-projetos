public class TransacaoResposta {
    private final boolean aprovado;
    private final String codigoAutorizacao;
    private final String mensagem;
    private final String moedaPadrao; 

    public TransacaoResposta(boolean aprovado, String codigoAutorizacao, String mensagem, String moedaPadrao) {
        this.aprovado = aprovado;
        this.codigoAutorizacao = codigoAutorizacao;
        this.mensagem = mensagem;
        this.moedaPadrao = moedaPadrao;
    }

    public boolean isAprovado() { return aprovado; }
    public String getCodigoAutorizacao() { return codigoAutorizacao; }
    public String getMensagem() { return mensagem; }
    public String getMoedaPadrao() { return moedaPadrao; }

    @Override
    public String toString() {
        return "TransacaoResposta{" +
                "aprovado=" + aprovado +
                ", codigoAutorizacao='" + codigoAutorizacao + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", moeda='" + moedaPadrao + '\'' +
                '}';
    }
}
