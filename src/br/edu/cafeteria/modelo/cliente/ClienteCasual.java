package br.edu.cafeteria.modelo.cliente;

public class ClienteCasual extends Cliente {
    public ClienteCasual(String nome) {
        super(nome);
    }

    @Override
    public String getCpf() {
        return null;
    }

    @Override
    public String toString() {
        String resposta = super.toString();
        resposta += "CATEGORIA: Cliente Casual\n";

        return resposta;
    }
}
