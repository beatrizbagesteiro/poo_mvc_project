package br.univali.cc.prog3.banco.exceptions;

public class ContaInexistenteException extends Exception {
    public ContaInexistenteException() {}
    public ContaInexistenteException(String message) {
        super(message);
    }
}
