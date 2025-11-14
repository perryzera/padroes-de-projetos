public class NFeDocument {
    public String numero;          
    public String xml;             
    public String moeda;           
    public long   emissaoMillis;   

    public double impostoCalculado;

    public NFeDocument(String numero, String xml, String moeda, long emissaoMillis) {
        this.numero = numero;
        this.xml = xml;
        this.moeda = moeda;
        this.emissaoMillis = emissaoMillis;
    }

    @Override
    public String toString() {
        return "NFe{num=" + numero + ", imposto=" + impostoCalculado + "}";
    }
}
