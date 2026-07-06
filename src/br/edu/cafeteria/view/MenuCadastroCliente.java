package br.edu.cafeteria.view;

import br.edu.cafeteria.excecao.ClienteInexistenteException;
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
            MenuPrincipal.limparTela();
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Clientes cadastrados");
            System.out.println("3 - Buscar cliente");
            System.out.println("4 - Remover cliente");
            System.out.println("5 - Atualizar dados de cliente");
            System.out.println("6 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case (1):
                    menuCadastrarCliente(cadastroCliente);
                    break;
                case (2):
                    menuClientesCadastrados(cadastroCliente);
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
                    break;
            }
        } while (opcao != 6);
    }

    public Cliente menuCadastro (CadastroCliente cadastroCliente) {
        char respostaPossuiCadastro;

        System.out.println("O cliente possui cadastro? (S/N)");

        while (true) {
            respostaPossuiCadastro = scanner.next().charAt(0);
            if (respostaPossuiCadastro == 'S') {
                System.out.println("Insira o CPF:");
                String cpf = scanner.next();
                Cliente buscaCliente;

                try {
                    buscaCliente = cadastroCliente.buscarCliente(cpf);
                    return buscaCliente;
                } catch (ClienteInexistenteException clienteInexistenteException) {
                    System.out.println(clienteInexistenteException.getMessage());
                    return menuDesejaCadastrar(cadastroCliente, cpf);
                }
            } else if (respostaPossuiCadastro == 'N') {
                return menuDesejaCadastrar(cadastroCliente, null);
            } else {
                System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
            }
        }
    }

    public Cliente menuDesejaCadastrar (CadastroCliente cadastroCliente, String cpf) {
        char respostaDesejaCadastrar;
        System.out.println("Deseja realizar o cadastro? (S/N)");

        while (true) {
            respostaDesejaCadastrar = scanner.next().charAt(0);
            if (respostaDesejaCadastrar == 'S') {
                MenuCadastroCliente menuCadastroCliente = new MenuCadastroCliente();
                if (cpf != null) {
                    return menuCadastroCliente.menuCadastrarCliente(cadastroCliente, cpf);
                } else {
                    return menuCadastroCliente.menuCadastrarCliente(cadastroCliente);
                }
            } else if (respostaDesejaCadastrar == 'N') {
                System.out.println("Insira o nome do cliente: ");
                scanner.nextLine();
                String nome = scanner.nextLine();
                return new Cliente(nome);
            } else {
                System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
            }
        }
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

    public void menuBuscarCliente(CadastroCliente cadastroCliente) {
        String cpf;

        System.out.println("Insira o CPF do cliente:");
        cpf = scanner.nextLine();
        Cliente clienteBusca;

        try {
            clienteBusca = cadastroCliente.buscarCliente(cpf);
            System.out.println(clienteBusca);
        } catch (ClienteInexistenteException clienteInexistenteException) {
            System.out.println(clienteInexistenteException.getMessage());
        }

        MenuPrincipal.retornarMenu();
    }

    public void menuRemoverCliente(CadastroCliente cadastroCliente) {
        String cpf;
        int respostaRemoverCliente;

        System.out.println("Insira o CPF do cliente:");
        cpf = scanner.nextLine();

        Cliente clienteRemover;

        try {
            clienteRemover = cadastroCliente.buscarCliente(cpf);
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
        } catch (ClienteInexistenteException clienteInexistenteException) {
            System.out.println(clienteInexistenteException.getMessage());
            MenuPrincipal.retornarMenu();
        }
    }

    public void menuAtualizarCliente(CadastroCliente cadastroCliente) {
        String cpf;

        System.out.println("Insira o CPF do cliente:");
        cpf = scanner.nextLine();
        Cliente clienteCadastroAntigo;

        try {
            clienteCadastroAntigo = cadastroCliente.buscarCliente(cpf);
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
        } catch (ClienteInexistenteException clienteInexistenteException) {
            System.out.println(clienteInexistenteException.getMessage());
            MenuPrincipal.retornarMenu();
        }
    }

    public void menuClientesCadastrados (CadastroCliente cadastroCliente) {
        MenuPrincipal.limparTela();
        System.out.println(cadastroCliente);
        MenuPrincipal.retornarMenu();
    }
}
