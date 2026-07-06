package br.edu.cafeteria.servico.promocao;

import br.edu.cafeteria.modelo.pedido.ItemPedido;
import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.modelo.produto.Comida;

public class DiaSextaGeek implements Promocao {
    private final static float porcentagemDesconto = 0.05F;

    @Override
    public void aplicarDesconto(Pedido pedido) {
        for (ItemPedido itemPedido : pedido.getItensPedido()) {
            if (itemPedido.getProdutoItemPedido() instanceof Comida) {
                itemPedido.aplicarDesconto(porcentagemDesconto);
            }
        }
    }

    public String toString() {
        String resposta = "";
        resposta += "Dia Sexta Geek | 5% de desconto em comidas";

        return resposta;
    }
}
