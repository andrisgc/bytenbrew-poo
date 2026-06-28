package br.edu.cafeteria.view;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVip;
import br.edu.cafeteria.servico.cadastro.CadastroCliente;

import java.util.Scanner;

public class MenuCadastroCliente {
    Scanner scanner = new Scanner(System.in);

    public void menuCadastroCliente (CadastroCliente cadastroCliente) {
        int opcao;

        do {
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Clientes cadastrados");
            System.out.println("3 - Buscar cliente");
            System.out.println("4 - Remover cliente");
            System.out.println("5 - Atualizar dados de cliente");
            System.out.println("6 - Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case (1):
                    menuCadastrarCliente(cadastroCliente);
                    break;
                case (2):
                    System.out.println(cadastroCliente);
                    break;
                case (3):
                    menuBuscarCliente(cadastroCliente);
                    break;
                case (4):
                    menuRemoverCliente(cadastroCliente);
                    break;
                case (5):
                    menuAtualizarCliente(cadastroCliente);
                    break;
                case (6):
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente: ");
            }

        } while (opcao != 6);


    }

    public Cliente menuCadastrarCliente(CadastroCliente cadastroCliente) {
        String cpf;
        String nome;

        System.out.println("Insira o nome do cliente:");
        scanner.nextLine();
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
        scanner.nextLine();
        nome = scanner.nextLine();

        ClienteStandard clienteNovo = new ClienteStandard(nome, cpf);
        cadastroCliente.cadastrarCliente(clienteNovo);
        return clienteNovo;
    }

    public void menuBuscarCliente(CadastroCliente cadastroCliente) {
        String cpf;

        System.out.println("Insira o CPF do cliente:");
        scanner.nextLine();
        cpf = scanner.nextLine();

        Cliente clienteBusca = cadastroCliente.buscarCliente(cpf);
        if (clienteBusca != null) {
            System.out.println(clienteBusca);
        } else {
            System.out.println("O cliente não existe!");
        }
    }

    public void menuRemoverCliente(CadastroCliente cadastroCliente) {
        String cpf;
        int respostaRemoverCliente;

        System.out.println("Insira o CPF do cliente:");
        scanner.nextLine();
        cpf = scanner.nextLine();

        Cliente clienteRemover = cadastroCliente.buscarCliente(cpf);

        if (clienteRemover != null) {
            System.out.println(clienteRemover);
            System.out.println("Deseja remover o cliente? (S/N)");

            while (true) {
                respostaRemoverCliente = scanner.next().charAt(0);
                if (respostaRemoverCliente == 'S') {
                    cadastroCliente.removerCliente(clienteRemover);
                    break;
                } else if (respostaRemoverCliente == 'N') {
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
                }
            }
        } else {
            System.out.println("O cliente não existe!");
        }
    }

    public void menuAtualizarCliente(CadastroCliente cadastroCliente) {
        String cpf;

        System.out.println("Insira o CPF do cliente:");
        scanner.nextLine();
        cpf = scanner.nextLine();

        Cliente clienteCadastroAntigo = cadastroCliente.buscarCliente(cpf);
        if (clienteCadastroAntigo != null) {
            String cpfNovo;
            String nomeNovo;

            System.out.println("Insira o CPF atualizado do cliente:");
            cpfNovo = scanner.nextLine();
            System.out.println("Insira o nome atualizado do cliente:");
            nomeNovo = scanner.nextLine();

            Cliente clienteCadastroNovo;
            if (clienteCadastroAntigo instanceof ClienteStandard) {
                clienteCadastroNovo = new ClienteStandard(nomeNovo, cpfNovo, ((ClienteStandard) clienteCadastroAntigo).getExpPoints());
            } else {
                clienteCadastroNovo = new ClienteVip(nomeNovo, cpfNovo, ((ClienteVip) clienteCadastroAntigo).getExpPoints());
            }

            cadastroCliente.atualizarCliente(clienteCadastroNovo, clienteCadastroAntigo);
        } else {
            System.out.println("O cliente não existe!");
        }
    }
}
