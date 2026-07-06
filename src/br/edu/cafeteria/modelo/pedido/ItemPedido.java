package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.modelo.produto.Produto;

public class ItemPedido {
    private final Produto produtoItemPedido;
    private int quantidadeItemPedido;
    private final float valorItemPedido;
    private float valorItemPedidoDesconto;

    public ItemPedido(Produto produtoItemPedido) {
        this.produtoItemPedido = produtoItemPedido;
        valorItemPedido = produtoItemPedido.getPreco();
        valorItemPedidoDesconto = valorItemPedido;
    }

    public void adicionarQuantidadeItemPedido(int adicionarQuantidade) throws EstoqueInsuficienteException {
        if (adicionarQuantidade > produtoItemPedido.getEstoque()) {
            throw new EstoqueInsuficienteException("Estoque insuficiente do produto " + produtoItemPedido.getNomeProduto());
        }

        quantidadeItemPedido += adicionarQuantidade;
    }

    public void removerQuantidadeItemPedido(int removerQuantidade) {
        quantidadeItemPedido -= removerQuantidade;
    }

    public void aplicarDesconto(float porcentagemDesconto) {
        valorItemPedidoDesconto = valorItemPedido - (valorItemPedido * porcentagemDesconto);
    }

    public String toString() {
        String resposta = "";
        resposta += quantidadeItemPedido + "x ";
        resposta += produtoItemPedido.getNomeProduto() + " - " + valorItemPedido + '\n';

        return resposta;
    }

    public String toStringDesconto() {
        String resposta = "";
        resposta += quantidadeItemPedido + "x ";
        resposta += produtoItemPedido.getNomeProduto() + " - " + valorItemPedidoDesconto + '\n';
        resposta += " ---------------------------- \n";

        return resposta;
    }

    public float getValorItemPedido() {
        return valorItemPedido;
    }

    public float getValorItemPedidoDesconto() {
        return valorItemPedidoDesconto;
    }

    public Produto getProdutoItemPedido() {
        return produtoItemPedido;
    }

    public int getQuantidadeItemPedido() {
        return quantidadeItemPedido;
    }
}
