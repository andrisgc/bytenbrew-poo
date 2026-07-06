package br.edu.cafeteria.view;

import br.edu.cafeteria.excecao.ProdutoInexistenteException;
import br.edu.cafeteria.modelo.produto.Bebida;
import br.edu.cafeteria.modelo.produto.Comida;
import br.edu.cafeteria.modelo.produto.Produto;
import br.edu.cafeteria.servico.cadastro.CadastroProduto;

import java.util.Scanner;

public class MenuCadastroProduto {
    Scanner scanner = new Scanner(System.in);

    public void menuCadastroProduto (CadastroProduto cadastroProduto) {
        int opcao;

        do {
            MenuPrincipal.limparTela();
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Produtos cadastrados");
            System.out.println("3 - Buscar produto");
            System.out.println("4 - Remover produto");
            System.out.println("5 - Atualizar dados do produto");
            System.out.println("6 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case (1):
                    menuCadastrarProduto(cadastroProduto);
                    break;
                case (2):
                    menuProdutosCadastrados(cadastroProduto);
                    break;
                case (3):
                    menuBuscarProduto(cadastroProduto);
                    break;
                case (4):
                    menuRemoverProduto(cadastroProduto);
                    break;
                case (5):
                    menuAtualizarProduto(cadastroProduto);
                    break;
                case (6):
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente: ");
            }
        } while (opcao != 6);
    }

    public void menuCadastrarProduto(CadastroProduto cadastroProduto) {
        int opcao;

        do {
            MenuPrincipal.limparTela();
            System.out.println("Qual tipo de produto você deseja cadastrar?");
            System.out.println("1 - Comida");
            System.out.println("2 - Bebida");
            System.out.println("3 - Genérico");
            System.out.println("4 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao >= 1 && opcao <= 3) {
                String nomeProduto;
                String codigoIdentificador;
                int estoque;
                float preco;

                System.out.println("Insira o nome do produto:");
                nomeProduto = scanner.nextLine();
                System.out.println("Insira o código identificador:"); //Verificar se o produto já existe
                codigoIdentificador = scanner.nextLine();
                System.out.println("Insira o estoque:");
                estoque = scanner.nextInt();
                System.out.println("Insira o preço:");
                preco = scanner.nextFloat();
                scanner.nextLine();

                switch (opcao) {
                    case (1):
                        System.out.println("Insira o tempo de preparo:");
                        int tempoPreparo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Insira o indicador de restrição alimentar: (VEGANO/SEM GLUTEN/NENHUM)");
                        String indicadorRestricao = scanner.nextLine();

                        cadastroProduto.cadastrarProduto(codigoIdentificador, nomeProduto, estoque, preco, tempoPreparo, indicadorRestricao);
                        break;
                    case (2):
                        System.out.println("Insira o tamanho da bebida (P/M/G):");
                        char tamanhoBebida = scanner.next().charAt(0);
                        System.out.println("Insira a dosagem de cafeína em mg:");
                        int cafeinaBebida = scanner.nextInt();

                        cadastroProduto.cadastrarProduto(codigoIdentificador, nomeProduto, estoque, preco, tamanhoBebida, cafeinaBebida);
                        break;
                    case (3):
                        cadastroProduto.cadastrarProduto(codigoIdentificador, nomeProduto, estoque, preco);
                        break;
                }
            } else if (opcao != 4) {
                System.out.println("Opção inválida! Digite novamente:");
            }

        } while (opcao != 4);
    }

    public void menuProdutosCadastrados (CadastroProduto cadastroProduto) {
        MenuPrincipal.limparTela();
        System.out.println(cadastroProduto);

        MenuPrincipal.retornarMenu();
    }

    public void menuBuscarProduto (CadastroProduto cadastroProduto) {
        String codigoIdentificador;

        System.out.println("Insira o código identificador do produto que deseja buscar: ");
        codigoIdentificador = scanner.nextLine();
        Produto buscaProduto;

        try {
            buscaProduto = cadastroProduto.buscarProduto(codigoIdentificador);
            System.out.println(buscaProduto);
        } catch (ProdutoInexistenteException produtoInexistenteException) {
            System.out.println(produtoInexistenteException.getMessage());
        }

        MenuPrincipal.retornarMenu();
    }

    public void menuRemoverProduto (CadastroProduto cadastroProduto) {
        String codigoIdentificador;
        int respostaRemoverProduto;

        System.out.println("Insira o código identificador do produto que deseja remover:");
        codigoIdentificador = scanner.nextLine();

        Produto produtoRemover;
        try {
            produtoRemover = cadastroProduto.buscarProduto(codigoIdentificador);
            System.out.println(produtoRemover);
            System.out.println("Deseja remover o produto? (S/N)");

            while (true) {
                respostaRemoverProduto = scanner.next().charAt(0);
                if (respostaRemoverProduto == 'S') {
                    cadastroProduto.removerProduto(produtoRemover);
                    break;
                } else if (respostaRemoverProduto == 'N') {
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, digite novamente. (S/N)");
                }
            }
        } catch (ProdutoInexistenteException produtoInexistenteException) {
            System.out.println(produtoInexistenteException.getMessage());
            MenuPrincipal.retornarMenu();
        }
    }

    public void menuAtualizarProduto (CadastroProduto cadastroProduto) {
        String codigoIdentificador;
        String nomeProduto;
        int estoque;
        float preco;

        System.out.println("Insira o código identificador do produto que deseja atualizar: ");
        codigoIdentificador = scanner.nextLine();

        try {
            Produto produtoCadastroAntigo = cadastroProduto.buscarProduto(codigoIdentificador);

            System.out.println(produtoCadastroAntigo);

            Produto produtoCadastroNovo;

            System.out.println("Insira o novo nome do produto:");
            nomeProduto = scanner.nextLine();
            System.out.println("Insira o novo estoque do produto:");
            estoque = scanner.nextInt();
            System.out.println("Insira o novo preço do produto:");
            preco = scanner.nextFloat();
            scanner.nextLine();

            if (produtoCadastroAntigo instanceof Comida) {
                int tempoPreparo;
                String indicadorRestricao;

                System.out.println("Insira o novo tempo de preparo: ");
                tempoPreparo = scanner.nextInt();
                System.out.println("Insira o novo indicador de restrição alimentar: ");
                scanner.nextLine();
                indicadorRestricao = scanner.nextLine();

                produtoCadastroNovo = new Comida(codigoIdentificador, nomeProduto, estoque, preco, tempoPreparo, indicadorRestricao);
            } else if (produtoCadastroAntigo instanceof Bebida) {
                char tamanhoBebida;
                int cafeinaBebida;

                System.out.println("Insira o novo tamanho da bebida: ");
                tamanhoBebida = scanner.next().charAt(0);
                System.out.println("Insira o novo indicador de dosagem de cafeína: ");
                cafeinaBebida = scanner.nextInt();

                produtoCadastroNovo = new Bebida(codigoIdentificador, nomeProduto, estoque, preco, cafeinaBebida, tamanhoBebida);
            } else {
                produtoCadastroNovo = new Produto(codigoIdentificador, nomeProduto, estoque, preco);
            }

            cadastroProduto.atualizarProduto(produtoCadastroNovo, produtoCadastroAntigo);
        } catch (ProdutoInexistenteException produtoInexistenteException) {
            System.out.println(produtoInexistenteException.getMessage());
            MenuPrincipal.retornarMenu();
        }
    }
}
