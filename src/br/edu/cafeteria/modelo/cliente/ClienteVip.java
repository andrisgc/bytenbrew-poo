package br.edu.cafeteria.modelo.cliente;

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

    public void removerPontos(int expPoints) {
        this.expPoints -= expPoints;
    }

    @Override
    public final String getCpf() {
        return cpf;
    }

    @Override
    public final int getExpPoints() {
        return expPoints;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "CPF: " + cpf + '\n';
        resposta += "EXP: " + expPoints + '\n';
        resposta += "CATEGORIA: Mestre da Guilda\n";
        return resposta;
    }
}
