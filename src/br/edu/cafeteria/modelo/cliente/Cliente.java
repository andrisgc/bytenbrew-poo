package br.edu.cafeteria.modelo.cliente;

public class Cliente {
    protected String nome;
    protected String cpf;
    protected int expPoints;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        expPoints = 0;
    }

    public final String getNome() {
        return nome;
    }

    public final int getExpPoints() {
        return expPoints;
    }

    public final String getCpf() {
        return cpf;
    }

    public String toString() {
        String resposta = "";
        resposta += "NOME: " + nome + '\n';
        resposta += "CPF: " + cpf + '\n';
        resposta += "PONTOS: " + expPoints + '\n';

        return resposta;
    }
}
