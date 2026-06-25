package br.edu.cafeteria.modelo.cliente;

import br.edu.cafeteria.servico.conversao.Conversao;

public class ClienteVip extends Cliente implements Conversao {
    private final static int taxaConversao = 2;
    private final String cpf;
    private int expPoints;

    public ClienteVip(String nome, String cpf, int expPoints) {
        super (nome);
        this.cpf = cpf;
        this.expPoints = expPoints;
    }

    @Override
    public int converterPontos(float valorPedido) {
        return (int)(valorPedido * taxaConversao);
    }

    @Override
    public void adicionarPontos(float valorPedido) {
        expPoints += converterPontos(valorPedido);
    }

    @Override
    public void removerPontos(float valorPedido) {
        expPoints -= converterPontos(valorPedido);
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "CPF: " + cpf + '\n';
        resposta += "EXP: " + expPoints + '\n';
        resposta += "CATEGORIA: Mestre da Guilda\n";
        return resposta;
    }
}
