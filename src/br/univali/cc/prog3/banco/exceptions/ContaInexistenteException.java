package br.univali.cc.prog3.banco.exceptions;

/*
    Classe responsável por sinalizar o erro de procurar uma conta inexistente.
 */
public class ContaInexistenteException extends Exception {
    public ContaInexistenteException() {}
    public ContaInexistenteException(String message) {
        super(message);
    }
}
