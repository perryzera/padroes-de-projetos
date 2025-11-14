public interface ProcessadorTransacoes {
    TransacaoResposta autorizar(String cartao, double valor, String moeda);
}
