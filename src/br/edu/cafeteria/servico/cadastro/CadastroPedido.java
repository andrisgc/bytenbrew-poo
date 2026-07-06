package br.edu.cafeteria.servico.cadastro;

import br.edu.cafeteria.modelo.pedido.Pedido;

import java.util.ArrayList;

public class CadastroPedido {
    private final ArrayList<Pedido> listaPedidos;
    private int numeroPedidos;

    public CadastroPedido() {
        listaPedidos = new ArrayList<>();
        numeroPedidos = 0;
    }

    public void adicionarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
        numeroPedidos++;
    }

    public void removerPedido(Pedido pedido) {
        listaPedidos.remove(pedido);
        numeroPedidos--;
    }

    public String toString() {
        String resposta = "";
        resposta += "NÚMERO DE PEDIDOS: " + numeroPedidos + '\n';

        resposta += "PEDIDOS:\n";
        for (Pedido pedido : listaPedidos) {
            resposta += pedido.toStringDesconto();
            resposta += " ---------------------------- \n";
        }

        return resposta;
    }
}