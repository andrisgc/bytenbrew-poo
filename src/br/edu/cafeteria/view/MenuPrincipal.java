package br.edu.cafeteria.view;

import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.servico.cadastro.CadastroCliente;
import br.edu.cafeteria.servico.cadastro.CadastroPedido;
import br.edu.cafeteria.servico.cadastro.CadastroProduto;
import br.edu.cafeteria.servico.promocao.Promocao;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {
    static Scanner scanner = new Scanner(System.in);

    public void menuPrincipal (MenuPedido menuPedido, MenuCadastroCliente menuCadastroCliente, MenuCadastroProduto menuCadastroProduto, CadastroCliente cadastroCliente, CadastroProduto cadastroProduto, CadastroPedido cadastroPedido, ArrayList<Promocao> listaPromocoes) {
        int opcao;

        do {
            limparTela();
            System.out.println("                            _                          ");
            System.out.println("     _____     _          _| |_    _____               ");
            System.out.println("    | __  |_ _| |_ ___   |   __|  | __  |___ ___ _ _ _ ");
            System.out.println("    | __ -| | |  _| -_|  |   __|  | __ -|  _| -_| | | |");
            System.out.println("    |_____|_  |_| |___|  |_   _|  |_____|_| |___|_____|");
            System.out.println("          |___|            |_|                         ");
            System.out.println();
            System.out.println("1 - Novo pedido");
            System.out.println("2 - Menu de clientes");
            System.out.println("3 - Menu de produtos");
            System.out.println("4 - Lista de pedidos");
            System.out.println("5 - Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case (1):
                    Pedido pedido = new Pedido(menuCadastroCliente.menuCadastro(cadastroCliente));
                    menuPedido.menuPedido(cadastroProduto, cadastroPedido, pedido, listaPromocoes);
                    break;
                case (2):
                    menuCadastroCliente.menuCadastroCliente(cadastroCliente);
                    break;
                case (3):
                    menuCadastroProduto.menuCadastroProduto(cadastroProduto);
                    break;
                case (4):
                    menuPedido.menuListaPedidos(cadastroPedido);
                    break;
                case (5):
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente: ");
                    break;
            }
        } while (opcao != 5);
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    public static void retornarMenu() {
        char respostaRetornar;

        System.out.println("Aperte X para retornar ao menu:");
        while (true) {
            respostaRetornar = scanner.next().charAt(0);
            if (respostaRetornar == 'X') {
                break;
            } else {
                System.out.println("Opção inválida. Por favor, digite novamente. (X)");
            }
        }
    }
}
