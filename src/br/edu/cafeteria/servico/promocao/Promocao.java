package br.edu.cafeteria.servico.promocao;

import br.edu.cafeteria.modelo.pedido.Pedido;

public interface Promocao {
    void aplicarDesconto(Pedido pedido);
    String toString();
}
