package br.edu.cafeteria.view;

import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.servico.cadastro.CadastroCliente;
import br.edu.cafeteria.servico.cadastro.CadastroProduto;

import java.util.Scanner;

public class MenuPrincipal {
    Scanner scanner = new Scanner(System.in);

    public void menuPrincipal (MenuPedido menuPedido, MenuCadastroCliente menuCadastroCliente, CadastroCliente cadastroCliente, CadastroProduto cadastroProduto) {
        int opcao;

        do {
            System.out.println("1 - Novo pedido");
            System.out.println("2 - Pedidos em andamento");
            System.out.println("3 - Menu de clientes");
            System.out.println("4 - Menu de produtos");
            System.out.println("5 - Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case (1):
                    Pedido pedido = new Pedido(menuPedido.menuCadastro(cadastroCliente));
                    menuPedido.menuPedido(cadastroProduto, pedido);
                    break;
                case (2):
                    System.out.println("EM MANUTENÇÃO");
                    break;
                case (3):
                    menuCadastroCliente.menuCadastroCliente();
                case (4):
                    System.out.println("EM MANUTENÇÃO");
                    break;
                case (5):
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente: ");
                    break;
            }
        } while (opcao != 5);
    }
}
