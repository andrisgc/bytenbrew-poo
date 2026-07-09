package br.edu.cafeteria.modelo.cliente;

public interface Conversao {
    int converterPontos(float valorPedido);
    void adicionarPontos(float valorPedido);
    int getExpPoints();
}
