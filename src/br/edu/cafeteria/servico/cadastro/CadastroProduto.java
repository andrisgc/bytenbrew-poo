package br.edu.cafeteria.servico.cadastro;

import br.edu.cafeteria.modelo.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class CadastroProduto {
    private final List<Produto> cadastroProduto;
    private int numeroProdutos;

    public CadastroProduto() {
        cadastroProduto = new ArrayList<Produto>();
        numeroProdutos = 0;
    }

    public void cadastrarProduto(Produto produtoCadastro) {
        cadastroProduto.add(produtoCadastro);
        numeroProdutos = cadastroProduto.size();
    }

    public void cadastrarProduto(String nomeProduto, String codigoIdentificador, int estoque, float preco) {
        Produto produtoCadastro = new Produto(nomeProduto, codigoIdentificador, estoque, preco);
        cadastroProduto.add(produtoCadastro);
        numeroProdutos = cadastroProduto.size();
    }

    public Produto buscarProduto(String codigoIdentificador) {
        for (Produto produtoCadastro : cadastroProduto) {
            if (codigoIdentificador.equals(produtoCadastro.getCodigoIdentificador()))
                return produtoCadastro;
        }
        return null;
    }

    public void removerProduto(Produto produtoCadastro) {
        cadastroProduto.remove(produtoCadastro);
    }

    public void removerProduto(String codigoIdentificador) {
        cadastroProduto.removeIf(produtoCadastro -> codigoIdentificador.equals(produtoCadastro.getCodigoIdentificador()));
    }

    public void atualizarProduto(Produto produtoCadastroNovo, String codigoIdentificador) {
        Produto produtoCadastroAntigo = buscarProduto(codigoIdentificador);
        if (produtoCadastroAntigo != null) {
            cadastroProduto.remove(produtoCadastroAntigo);
            cadastroProduto.add(produtoCadastroNovo);
        }
    }

    public String toString() {
        String resposta = "";
        resposta += "NÚMERO DE PRODUTOS: " + numeroProdutos + '\n';
        resposta += "PRODUTOS:\n";
        for (Produto produtoCadastro : cadastroProduto) {
            resposta += produtoCadastro.toString();
            resposta += " ---------------------------- \n";
        }

        return resposta;
    }
}
