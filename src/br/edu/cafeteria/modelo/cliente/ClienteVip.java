package br.edu.cafeteria.modelo.cliente;

public class ClienteVip extends Cliente implements Conversao {
    private final static int taxaConversao = 2;

    public ClienteVip(String nome, String cpf, int expPoints) {
        super (nome, cpf);
        this.expPoints = expPoints;
    }

    @Override
    public int converterPontos(float valorPedido) {
        return (int)(valorPedido * taxaConversao);
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "CATEGORIA: Mestre da Guilda\n";

        return resposta;
    }
}
