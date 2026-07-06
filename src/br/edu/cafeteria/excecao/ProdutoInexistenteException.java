package br.edu.cafeteria.excecao;

public class ProdutoInexistenteException extends Exception {
    public ProdutoInexistenteException(String message) {
        super(message);
    }
}
