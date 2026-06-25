package br.edu.cafeteria.modelo.produto;

public class Produto {
    protected String codigoIdentificador,
                     nomeProduto;
    protected int estoque;
    protected float preco;

    public Produto (String codigoIdentificador, String nomeProduto, int estoque, float preco) {
        this.codigoIdentificador = codigoIdentificador;
        this.nomeProduto = nomeProduto;
        this.estoque = estoque;
        this.preco = preco;
    }

    public final void somarEstoque(int quantidade) {
        estoque += quantidade;
    }

    public final void subtrairEstoque(int quantidade) {
        estoque -= quantidade;
    }

    public final void setCodigoIdentificador(String codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }

    public final void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public final void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public final void setPreco(float preco) {
        this.preco = preco;
    }

    public final float getPreco() {
        return preco;
    }

    public final int getEstoque() {
        return estoque;
    }

    public final String getNomeProduto() {
        return nomeProduto;
    }

    public final String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public String toString() {
        String resposta = "";
        resposta += "NOME DO PRODUTO: " + nomeProduto + '\n';
        resposta += "CODIGO IDENTIFICADOR: " + codigoIdentificador + '\n';
        resposta += "PREÇO: " + preco + '\n';
        resposta += "ESTOQUE: " + estoque + '\n';

        return resposta;
    }
}
