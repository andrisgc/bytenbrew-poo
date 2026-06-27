package br.edu.cafeteria.view;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.servico.cadastro.CadastroCliente;

import java.util.Scanner;

public class MenuCadastroCliente {
    Scanner scanner = new Scanner(System.in);

    public void menuCadastroCliente () {
        int opcao;

        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Clientes cadastrados");
        System.out.println("3 - Buscar cliente");
        System.out.println("4 - Remover cliente");
        System.out.println("5 - Atualizar dados de cliente");
        System.out.println("6 - Sair");
        opcao = scanner.nextInt();
    }

    public Cliente menuCadastrarCliente(CadastroCliente cadastroCliente) {
        String cpf;
        String nome;

        System.out.println("Insira o nome do cliente:");
        nome = scanner.nextLine();
        System.out.println("Insira o CPF do cliente:");
        cpf = scanner.nextLine();

        ClienteStandard clienteNovo = new ClienteStandard(nome, cpf);
        cadastroCliente.cadastrarCliente(clienteNovo);
        return clienteNovo;
    }

    public Cliente menuCadastrarCliente(CadastroCliente cadastroCliente, String cpf) {
        String nome;

        System.out.println("Insira o nome do cliente:");
        nome = scanner.nextLine();

        ClienteStandard clienteNovo = new ClienteStandard(nome, cpf);
        cadastroCliente.cadastrarCliente(clienteNovo);
        return clienteNovo;
    }







}
