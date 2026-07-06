package br.edu.cafeteria.modelo.cliente;

public class Cliente {
    protected String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public final String getNome() {
        return nome;
    }

    public String getCpf() {
        return null;
    }

    public String toString() {
        String resposta = "";
        resposta += "NOME: " + nome + '\n';

        return resposta;
    }
}
