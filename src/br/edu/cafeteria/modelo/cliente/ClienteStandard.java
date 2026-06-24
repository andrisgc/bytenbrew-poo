package br.edu.cafeteria.modelo.cliente;

public class ClienteStandard extends Cliente implements Conversao {
    private final static int taxaConversao = 1;

    public ClienteStandard(String nome, String cpf) {
        super (nome, cpf);
    }

    @Override
    public int converterPontos(float valorPedido) {
        return (int)(valorPedido * taxaConversao);
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "CATEGORIA: Aventureiro Iniciante\n";

        return resposta;
    }

}
