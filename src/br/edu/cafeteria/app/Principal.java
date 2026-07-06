package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVip;
import br.edu.cafeteria.modelo.produto.Bebida;
import br.edu.cafeteria.modelo.produto.Comida;
import br.edu.cafeteria.modelo.produto.Produto;
import br.edu.cafeteria.servico.cadastro.CadastroCliente;
import br.edu.cafeteria.servico.cadastro.CadastroPedido;
import br.edu.cafeteria.servico.cadastro.CadastroProduto;
import br.edu.cafeteria.servico.promocao.DiaOrgulhoNerd;
import br.edu.cafeteria.servico.promocao.DiaSextaGeek;
import br.edu.cafeteria.servico.promocao.Promocao;
import br.edu.cafeteria.view.MenuCadastroCliente;
import br.edu.cafeteria.view.MenuCadastroProduto;
import br.edu.cafeteria.view.MenuPedido;
import br.edu.cafeteria.view.MenuPrincipal;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        MenuPedido menuPedido = new MenuPedido();
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        MenuCadastroCliente menuCadastroCliente = new MenuCadastroCliente();
        MenuCadastroProduto menuCadastroProduto = new MenuCadastroProduto();
        ArrayList<Promocao> listaPromocoes = new ArrayList<>();
        listaPromocoes.add(new DiaOrgulhoNerd());
        listaPromocoes.add(new DiaSextaGeek());

        CadastroCliente cadastroCliente = new CadastroCliente();
        CadastroProduto cadastroProduto = new CadastroProduto();
        CadastroPedido  cadastroPedido = new CadastroPedido();

        ClienteVip clienteTeste1 = new ClienteVip("Andris Castro", "10987654321", 10);
        cadastroCliente.cadastrarCliente(clienteTeste1);
        ClienteStandard clienteTeste2 = new ClienteStandard("Alex Kurokawa", "12345678910");
        cadastroCliente.cadastrarCliente(clienteTeste2);

        Produto produtoTeste1 = new Produto("1010", "Produto Genérico", 10, 10.00F);
        cadastroProduto.cadastrarProduto(produtoTeste1);
        Comida produtoTeste2 = new Comida("1011", "Lembas Bread", 10, 8.00F, 10, "CONTÉM GLÚTEM");
        cadastroProduto.cadastrarProduto(produtoTeste2);
        Comida produtoTeste3 = new Comida("1012", "Portal Cake", 10, 10.00F, 1, "CONTÉM LACTOSE");
        cadastroProduto.cadastrarProduto(produtoTeste3);
        Bebida produtoTeste4 = new Bebida("1013", "Café do Programador", 10, 5.00F, 80, 'P');
        cadastroProduto.cadastrarProduto(produtoTeste4);
        Bebida produtoTeste5 = new Bebida("1014", "Poção de Mana", 10, 8.00F, 0, 'P');
        cadastroProduto.cadastrarProduto(produtoTeste5);

        menuPrincipal.menuPrincipal(menuPedido, menuCadastroCliente, menuCadastroProduto, cadastroCliente, cadastroProduto, cadastroPedido, listaPromocoes);

    }
}
