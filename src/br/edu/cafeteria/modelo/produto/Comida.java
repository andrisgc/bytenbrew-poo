package br.edu.cafeteria.modelo.produto;

public class Comida extends Produto {
    protected int tempoPreparo;
    protected String indicador;

    public Comida (String codigoIdentificador, String nomeProduto, int estoque, float preco, int tempoPreparo, String indicador) {
        super (codigoIdentificador, nomeProduto, estoque, preco);
        this.tempoPreparo = tempoPreparo;
        this.indicador = indicador;
    }

    public final int getTempoPreparo() {
        return tempoPreparo;
    }

    public final void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public final String getIndicador() {
        return indicador;
    }

    public final void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "TEMPO DE PREPARO: " + tempoPreparo + '\n';
        resposta += "INDICADOR: " + indicador + '\n';

        return resposta;
    }
}
