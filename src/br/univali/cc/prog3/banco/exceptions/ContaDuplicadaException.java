package br.univali.cc.prog3.banco.exceptions;

public class ContaDuplicadaException extends Exception {
    public ContaDuplicadaException() {}
    public ContaDuplicadaException(String message) {
        super(message);
    }
}
