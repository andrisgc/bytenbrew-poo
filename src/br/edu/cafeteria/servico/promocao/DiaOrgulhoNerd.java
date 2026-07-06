package br.edu.cafeteria.servico.promocao;

import br.edu.cafeteria.modelo.pedido.ItemPedido;
import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.modelo.produto.Bebida;

public class DiaOrgulhoNerd implements Promocao {
    private final static float porcentagemDesconto = 0.10F;

    @Override
    public void aplicarDesconto(Pedido pedido) {
        for (ItemPedido itemPedido : pedido.getItensPedido()) {
            if (itemPedido.getProdutoItemPedido() instanceof Bebida) {
                itemPedido.aplicarDesconto(porcentagemDesconto);
            }
        }
    }

    public String toString() {
        String resposta = "";
        resposta += "Dia Orgulho Nerd | 10% de desconto em bebidas";

        return resposta;
    }
}
