package br.edu.cafeteria.servico.conversao;

public interface Conversao {
    int converterPontos(float valorPedido);
    void adicionarPontos(float valorPedido);
    void removerPontos(float valorPedido);
}
