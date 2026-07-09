package br.edu.cafeteria.modelo.cliente;

public abstract class Cliente {
    protected String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public final String getNome() {
        return nome;
    }

    public abstract String getCpf();

    public String toString() {
        String resposta = "";
        resposta += "NOME: " + nome + '\n';

        return resposta;
    }
}
