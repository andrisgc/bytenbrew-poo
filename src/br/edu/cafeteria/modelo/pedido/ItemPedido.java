package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.modelo.produto.Produto;

public class ItemPedido {
    protected Produto produtoPedido;
    protected int quantidadePedido;

    public ItemPedido(Produto produtoPedido, int quantidadePedido) {
        this.produtoPedido = produtoPedido;
        this.quantidadePedido = quantidadePedido;
    }

    public boolean adicionarQuantidadeItemPedido(int adicionarQuantidade) {
        quantidadePedido += adicionarQuantidade;
        return true;
    }

    public boolean removerQuantidadeItemPedido(int removerQuantidade) {
        quantidadePedido -= removerQuantidade;
        return true;
    }

    public Produto getProdutoPedido() {
        return produtoPedido;
    }

    public void setProdutoPedido(Produto produtoPedido) {
        this.produtoPedido = produtoPedido;
    }

    public int getQuantidadePedido() {
        return quantidadePedido;
    }

    public void setQuantidadePedido(int quantidadePedido) {
        this.quantidadePedido = quantidadePedido;
    }

    public String toString() {
        String resposta = "";
        resposta += quantidadePedido + "x ";
        resposta += produtoPedido.getNomeProduto() + '\n';

        return resposta;
    }
}
