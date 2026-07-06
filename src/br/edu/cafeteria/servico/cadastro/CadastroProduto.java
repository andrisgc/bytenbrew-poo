package br.edu.cafeteria.servico.cadastro;

import br.edu.cafeteria.excecao.ProdutoInexistenteException;
import br.edu.cafeteria.modelo.produto.Bebida;
import br.edu.cafeteria.modelo.produto.Comida;
import br.edu.cafeteria.modelo.produto.Produto;

import java.util.ArrayList;

public class CadastroProduto {
    private final ArrayList<Produto> cadastroProduto;
    private int numeroProdutos;

    public CadastroProduto() {
        cadastroProduto = new ArrayList<>();
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

    public void cadastrarProduto(String nomeProduto, String codigoIdentificador, int estoque, float preco, char tamanhoBebida, int cafeinaBebida) {
        Produto produtoCadastro = new Bebida(nomeProduto, codigoIdentificador, estoque, preco, cafeinaBebida, tamanhoBebida);
        cadastroProduto.add(produtoCadastro);
        numeroProdutos = cadastroProduto.size();
    }

    public void cadastrarProduto(String nomeProduto, String codigoIdentificador, int estoque, float preco, int tempoPreparo, String indicadorRestricao) {
        Produto produtoCadastro = new Comida(nomeProduto, codigoIdentificador, estoque, preco, tempoPreparo, indicadorRestricao);
        cadastroProduto.add(produtoCadastro);
        numeroProdutos = cadastroProduto.size();
    }

    public Produto buscarProduto(String codigoIdentificador) throws ProdutoInexistenteException {
        for (Produto produtoCadastro : cadastroProduto) {
            if (codigoIdentificador.equals(produtoCadastro.getCodigoIdentificador()))
                return produtoCadastro;
        }
        throw new ProdutoInexistenteException("O produto não existe.");
    }

    public void removerProduto(Produto produtoCadastro) {
        cadastroProduto.remove(produtoCadastro);
        numeroProdutos = cadastroProduto.size();
    }

    public void removerProduto(String codigoIdentificador) {
        cadastroProduto.removeIf(produtoCadastro -> codigoIdentificador.equals(produtoCadastro.getCodigoIdentificador()));
        numeroProdutos = cadastroProduto.size();
    }

    public void atualizarProduto(Produto produtoCadastroNovo, Produto produtoCadastroAntigo) {
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
