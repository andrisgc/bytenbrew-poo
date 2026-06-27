package br.edu.cafeteria.view;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVip;
import br.edu.cafeteria.modelo.pedido.ItemPedido;
import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.servico.cadastro.CadastroCliente;
import br.edu.cafeteria.servico.cadastro.CadastroProduto;

import java.util.Scanner;

public class MenuPedido {
    Scanner scanner = new Scanner(System.in);

    public Cliente menuCadastro (CadastroCliente cadastroCliente) {
        char respostaPossuiCadastro;

        System.out.println("O cliente possui cadastro? (S/N)");

        while (true) {
            respostaPossuiCadastro = scanner.next().charAt(0);
            if (respostaPossuiCadastro == 'S') {
                System.out.println("Insira o CPF:");
                String cpf = scanner.next();
                Cliente buscaCliente = cadastroCliente.buscarCliente(cpf);
                if (buscaCliente == null) {
                    System.out.println("Cliente não possui cadastro.");
                    return menuDesejaCadastrar(cadastroCliente, cpf);
                } else {
                    return buscaCliente;
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
                String nome = scanner.nextLine();
                return new Cliente(nome);
            } else {
                System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
            }
        }
    }

    public void menuPedido(CadastroProduto cadastroProduto, Pedido pedido) {
        int opcao;

        do {
            System.out.println("1 - Adicionar item ao pedido");
            System.out.println("2 - Remover item do pedido");
            System.out.println("3 - Consultar cardápio");
            System.out.println("4 - Finalizar pedido");
            System.out.println("5 - Cancelar pedido");
            opcao = scanner.nextInt();

            switch (opcao) {
                case (1):
                    menuPedidoAdicionar(cadastroProduto, pedido);
                    break;
                case (2):
                    menuPedidoRemover(cadastroProduto, pedido);
                    break;
                case (3):
                    System.out.println("EM MANUTENÇÃO");
                    break;
                case (4):
                    menuFinalizarPedido(pedido);
                    break;
                case (5):
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente: ");
                    break;
            }

        } while (opcao != 5);
    }

    public boolean menuPedidoAdicionar(CadastroProduto cadastroProduto, Pedido pedido) {
        System.out.println("Insira o código do produto que deseja adicionar:");
        String codigoIdentificador = scanner.next();
        System.out.println("Insira a quantidade:");
        int respostaQuantidadeItemPedido = scanner.nextInt();

        boolean achouItem = pedido.acharItem(codigoIdentificador);
        if (!achouItem) {
            ItemPedido itemPedido = new ItemPedido((cadastroProduto.buscarProduto(codigoIdentificador)), respostaQuantidadeItemPedido);
            pedido.adicionarItem(itemPedido);
            return true;
        } else {
            System.out.println("Este produto já foi adicionado ao seu pedido.");
            System.out.println("Deseja aumentar a quantidade? (S/N)");
            char respostaAumentarQuantidade;
            ItemPedido itemPedido = pedido.buscarItem(codigoIdentificador);

            while (true) {
                respostaAumentarQuantidade = scanner.next().charAt(0);
                if (respostaAumentarQuantidade == 'S') {
                    System.out.println("Digite a quantidade que deseja adicionar:");
                    respostaQuantidadeItemPedido = scanner.nextInt();

                    return itemPedido.adicionarQuantidadeItemPedido(respostaQuantidadeItemPedido);
                } else if (respostaAumentarQuantidade == 'N') {
                    return false;
                } else {
                    System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
                }
            }
        }
    }

    public boolean menuPedidoRemover (CadastroProduto cadastroProduto, Pedido pedido) {
        System.out.println("Insira o código do produto que deseja remover:");
        String codigoIdentificador = scanner.next();
        System.out.println("Insira a quantidade:");
        int respostaQuantidadeItemPedido = scanner.nextInt();

        boolean achouItem = pedido.acharItem(codigoIdentificador);
        if (achouItem) {
            ItemPedido itemPedido = pedido.buscarItem(codigoIdentificador);
            return pedido.removerItem(itemPedido, respostaQuantidadeItemPedido);
        } else {
            System.out.println("Este produto não foi adicionado ao seu pedido.");
            return false;
        }
    }

    public void menuFinalizarPedido (Pedido pedido) {
        System.out.print(pedido.toString());
        Cliente clientePedido = pedido.getClientePedido();

        if (clientePedido instanceof ClienteVip) {
            char respostaAplicarPontos;
            int pontosNecessarios = pedido.verificarPontosNecessarios();
            int pontosCliente = pedido.verificarPontos(pedido.getClientePedido());
            int pontosGanhos = ((ClienteVip) clientePedido).converterPontos(pedido.getValorTotal());

            System.out.println("Quantidade total de pontos do cliente: " + pontosCliente);
            System.out.println("Pontos necessários: " + pontosNecessarios);
            System.out.println("Deseja converter seus pontos em desconto? (S/N)");

            while (true) {
                respostaAplicarPontos = scanner.next().charAt(0);

                if (respostaAplicarPontos == 'S') {
                    if (pontosCliente >= pontosNecessarios) {
                        pedido.calcularDesconto(pontosNecessarios, (ClienteVip) pedido.getClientePedido());
                        System.out.print(pedido.toString());
                        pedido.calcularDesconto(pontosNecessarios, (ClienteVip) pedido.getClientePedido());
                        System.out.println("VALOR COM DESCONTO: " + pedido.getValorTotal());
                        break;
                    } else {
                        System.out.println("O cliente não possui pontos suficientes.");
                    }
                } else if (respostaAplicarPontos == 'N') {
                    System.out.println("Quantidade final de pontos: " + pontosCliente + " + " + pontosGanhos);
                    ((ClienteVip) clientePedido).adicionarPontos(pedido.getValorTotal());
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
                }
            }
        } else if (clientePedido instanceof ClienteStandard) {
            int pontosCliente = pedido.verificarPontos(pedido.getClientePedido());
            int pontosGanhos = ((ClienteStandard) clientePedido).converterPontos(pedido.getValorTotal());

            System.out.println("Quantidade total de pontos do cliente: " + pontosCliente);
            System.out.println("Quantidade final de pontos: " + pontosCliente + " + " + pontosGanhos);
            ((ClienteStandard) clientePedido).adicionarPontos(pedido.getValorTotal());
        }
    }
}
