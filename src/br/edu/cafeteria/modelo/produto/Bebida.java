package br.edu.cafeteria.modelo.produto;

public class Bebida extends Produto {
    protected char tamanhoBebida;
    protected int cafeinaBebida;

    public Bebida (String codigoIdentificador, String nomeProduto, int estoque, float preco, int cafeinaBebida, char tamanhoBebida) {
        super (codigoIdentificador, nomeProduto, estoque, preco);
        this.tamanhoBebida = tamanhoBebida;
        this.cafeinaBebida = cafeinaBebida;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "TAMANHO DA BEBIDA: " + tamanhoBebida + '\n';
        resposta += "QUANTIDADE DE CAFEÍNA: " + cafeinaBebida + '\n';

        return resposta;
    }
}
