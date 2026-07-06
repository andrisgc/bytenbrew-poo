package br.edu.cafeteria.modelo.produto;

public class Comida extends Produto {
    protected int tempoPreparo;
    protected String indicadorRestricao;

    public Comida (String codigoIdentificador, String nomeProduto, int estoque, float preco, int tempoPreparo, String indicadorRestricao) {
        super (codigoIdentificador, nomeProduto, estoque, preco);
        this.tempoPreparo = tempoPreparo;
        this.indicadorRestricao = indicadorRestricao;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "TEMPO DE PREPARO: " + tempoPreparo + '\n';
        resposta += "INDICADOR: " + indicadorRestricao + '\n';

        return resposta;
    }
}
