package br.edu.cafeteria.excecao;

public class ClienteInexistenteException extends Exception {
    public ClienteInexistenteException(String message) {
        super(message);
    }
}
