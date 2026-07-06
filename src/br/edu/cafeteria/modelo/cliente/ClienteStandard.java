package br.edu.cafeteria.modelo.cliente;

public class ClienteStandard extends Cliente implements Conversao {
    private final static int taxaConversao = 1;
    private final String cpf;
    private int expPoints;

    public ClienteStandard(String nome, String cpf) {
        super (nome);
        this.cpf = cpf;
        expPoints = 0;
    }

    public ClienteStandard(String nome, String cpf, int expPoints) {
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

    public ClienteVip converterCliente() {
        return new ClienteVip(nome, cpf, expPoints);
    }

    public int getExpPoints() {
        return expPoints;
    }

    @Override
    public final String getCpf() {
        return cpf;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "CPF: " + cpf + '\n';
        resposta += "EXP: " + expPoints + '\n';
        resposta += "CATEGORIA: Aventureiro Iniciante\n";
        return resposta;
    }

}
