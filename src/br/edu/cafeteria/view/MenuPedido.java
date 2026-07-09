package br.edu.cafeteria.view;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.excecao.ProdutoInexistenteException;
import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVip;
import br.edu.cafeteria.modelo.cliente.Conversao;
import br.edu.cafeteria.modelo.pedido.ItemPedido;
import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.modelo.produto.Produto;
import br.edu.cafeteria.servico.cadastro.CadastroPedido;
import br.edu.cafeteria.servico.cadastro.CadastroProduto;
import br.edu.cafeteria.servico.promocao.Promocao;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPedido {
    Scanner scanner = new Scanner(System.in);

    public void menuPedido(CadastroProduto cadastroProduto, CadastroPedido cadastroPedido, Pedido pedido, ArrayList<Promocao> listaPromocoes) {
        int opcao;

        do {
            MenuPrincipal.limparTela();
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
                    menuPedidoRemover(pedido);
                    break;
                case (3):
                    System.out.println(cadastroProduto);
                    System.out.println("Aperte X para retornar ao menu:");
                    MenuPrincipal.retornarMenu();
                    break;
                case (4):
                    menuFinalizarPedido(pedido, cadastroPedido, listaPromocoes);
                    break;
                case (5):
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente: ");
                    break;
            }

        } while (opcao != 5 && opcao != 4);
    }

    public void menuPedidoAdicionar(CadastroProduto cadastroProduto, Pedido pedido) {
        String codigoIdentificador;
        System.out.println("Insira o código do produto que deseja adicionar:");
        codigoIdentificador = scanner.next();
        Produto buscaProduto;

        try {
            buscaProduto = cadastroProduto.buscarProduto(codigoIdentificador);

            int respostaQuantidadeItemPedido;
            boolean achouItem = pedido.acharItem(codigoIdentificador);
            if (!achouItem) {
                System.out.println("Insira a quantidade:");
                respostaQuantidadeItemPedido = scanner.nextInt();
                try {
                    ItemPedido itemPedido = new ItemPedido(buscaProduto);
                    pedido.adicionarItem(itemPedido, respostaQuantidadeItemPedido);
                } catch (EstoqueInsuficienteException estoqueInsuficienteException) {
                    System.out.println(estoqueInsuficienteException.getMessage());
                    MenuPrincipal.retornarMenu();
                }
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

                        try {
                            itemPedido.adicionarQuantidadeItemPedido(respostaQuantidadeItemPedido);
                        } catch (EstoqueInsuficienteException estoqueInsuficienteException) {
                            System.out.println(estoqueInsuficienteException.getMessage());
                            MenuPrincipal.retornarMenu();
                        }
                        break;
                    } else if (respostaAumentarQuantidade == 'N') {
                        break;
                    } else {
                        System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
                    }
                }
            }
        } catch (ProdutoInexistenteException produtoInexistenteException) {
            System.out.println(produtoInexistenteException.getMessage());
            MenuPrincipal.retornarMenu();
        }
    }

    public void menuPedidoRemover(Pedido pedido) {
        System.out.println("Insira o código do produto que deseja remover:");
        String codigoIdentificador = scanner.next();
        System.out.println("Insira a quantidade:");
        int respostaQuantidadeItemPedido = scanner.nextInt();

        boolean achouItem = pedido.acharItem(codigoIdentificador);
        if (achouItem) {
            ItemPedido itemPedido = pedido.buscarItem(codigoIdentificador);
            pedido.removerItem(itemPedido, respostaQuantidadeItemPedido);
        } else {
            System.out.println("Este produto não foi adicionado ao seu pedido.");
            MenuPrincipal.retornarMenu();
        }
    }

    public void menuFinalizarPedido(Pedido pedido, CadastroPedido cadastroPedido, ArrayList<Promocao> listaPromocoes) {
        MenuPrincipal.limparTela();
        System.out.print(pedido.toString());
        Cliente clientePedido = pedido.getClientePedido();
        operacaoPontos operacao = operacaoPontos.adicionar;

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
                    try {
                        pedido.calcularPontos(pontosNecessarios);
                        System.out.print(pedido);
                        System.out.println("VALOR COM DESCONTO: " + pedido.getValorTotal());
                        operacao = operacaoPontos.remover;
                    } catch (PontosInsuficientesException pontosInsuficientesException) {
                        System.out.println(pontosInsuficientesException.getMessage());
                        System.out.println("Quantidade de pontos a serem ganhos: " + pontosGanhos);
                        System.out.println("Quantidade final de pontos do cliente: " + (pontosGanhos + pontosCliente));
                    }
                    break;
                } else if (respostaAplicarPontos == 'N') {
                    System.out.println("Quantidade de pontos a serem ganhos: " + pontosGanhos);
                    System.out.println("Quantidade final de pontos do cliente: " + (pontosGanhos + pontosCliente));
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
        }

        menuDescontoPedido(pedido, listaPromocoes);
        menuPagamentoPedido(pedido, cadastroPedido, clientePedido, operacao);
    }

    public void menuDescontoPedido(Pedido pedido, ArrayList<Promocao> listaPromocoes) {
        char respostaRealizarDesconto;

        System.out.println("Deseja aplicar desconto? (S/N)");
        while (true) {
            respostaRealizarDesconto = scanner.next().charAt(0);

            if (respostaRealizarDesconto == 'S') {
                int opcao;

                System.out.println("Qual desconto deseja aplicar?");
                for (int i = 0; i < listaPromocoes.size(); i++) {
                    System.out.println((i + 1) + " - " + listaPromocoes.get(i));
                }

                while (true) {
                    opcao = scanner.nextInt();
                    if (opcao >= 1 && opcao <= listaPromocoes.size()) {
                        Promocao promocao = listaPromocoes.get(opcao - 1);
                        promocao.aplicarDesconto(pedido);

                        MenuPrincipal.limparTela();
                        System.out.println("Pedido com desconto: ");
                        System.out.println(pedido.toStringDesconto());
                        break;
                    } else {
                        System.out.println("Opção inválida. Por favor, digite novamente: ");
                    }
                }
                break;
            } else if (respostaRealizarDesconto == 'N') {
                break;
            } else {
                System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
            }
        }
    }

    public enum operacaoPontos {
        adicionar, remover
    }

    public void menuPagamentoPedido(Pedido pedido, CadastroPedido cadastroPedido, Cliente clientePedido, operacaoPontos operacao) {
        char respostaRealizarPagamento;

        System.out.println("Deseja realizar o pagamento? (S/N)");
        while (true) {
            respostaRealizarPagamento = scanner.next().charAt(0);

            if (respostaRealizarPagamento == 'S') {
                cadastroPedido.adicionarPedido(pedido);

                for (ItemPedido itemPedido : pedido.getItensPedido()) {
                    int quantidadeItemPedido = itemPedido.getQuantidadeItemPedido();
                    Produto produtoItemPedido = itemPedido.getProdutoItemPedido();
                    produtoItemPedido.subtrairEstoque(quantidadeItemPedido);
                }

                if (clientePedido instanceof Conversao) {
                    if (operacao == operacaoPontos.adicionar) {
                        ((Conversao) clientePedido).adicionarPontos(pedido.getValorTotal());
                    } else {
                        pedido.calcularValor();
                        ((ClienteVip) clientePedido).removerPontos(pedido.verificarPontosNecessarios());
                    }
                }

                System.out.println("PAGAMENTO EFETUADO COM SUCESSO");
                MenuPrincipal.retornarMenu();
                break;
            } else if (respostaRealizarPagamento == 'N') {
                System.out.println("PAGAMENTO CANCELADO");
                MenuPrincipal.retornarMenu();
                break;
            } else {
                System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
            }
        }
    }

    public void menuListaPedidos (CadastroPedido cadastroPedido) {
        MenuPrincipal.limparTela();
        System.out.println(cadastroPedido);
        MenuPrincipal.retornarMenu();
    }
}
