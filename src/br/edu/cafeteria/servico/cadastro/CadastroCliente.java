package br.edu.cafeteria.servico.cadastro;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;

import java.util.ArrayList;
import java.util.List;

public class CadastroCliente {
    private final List<Cliente> cadastroCliente;
    private int numeroClientes;

    public CadastroCliente() {
        cadastroCliente = new ArrayList<>();
        numeroClientes = 0;
    }

    public void cadastrarCliente(Cliente clienteCadastro) {
        cadastroCliente.add(clienteCadastro);
        numeroClientes = cadastroCliente.size();
    }

    public void cadastrarCliente(String nome, String cpf) {
        ClienteStandard clienteCadastro = new ClienteStandard(nome , cpf);
        cadastroCliente.add(clienteCadastro);
        numeroClientes = cadastroCliente.size();
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente clienteCadastro : cadastroCliente) {
            if (cpf.equals(clienteCadastro.getCpf()))
                return clienteCadastro;
        }
        return null;
    }

    public void removerCliente(Cliente clienteCadastro) {
        cadastroCliente.remove(clienteCadastro);
        numeroClientes = cadastroCliente.size();
    }

    public void removerCliente(String cpf) {
        cadastroCliente.removeIf(clienteCadastro -> cpf.equals(clienteCadastro.getCpf()));
        numeroClientes = cadastroCliente.size();
    }

    public void atualizarCliente(Cliente clienteCadastroNovo, Cliente clienteCadastroAntigo) {
        if (clienteCadastroAntigo != null) {
            cadastroCliente.remove(clienteCadastroAntigo);
            cadastroCliente.add(clienteCadastroNovo);
        }
    }

    public String toString() {
        String resposta = "";
        resposta += "NÚMERO DE CLIENTES: " + numeroClientes + '\n';
        resposta += "CLIENTES:\n";
        for (Cliente clienteCadastro : cadastroCliente) {
            resposta += clienteCadastro.toString();
            resposta += " ---------------------------- \n";
        }

        return resposta;
    }
}
