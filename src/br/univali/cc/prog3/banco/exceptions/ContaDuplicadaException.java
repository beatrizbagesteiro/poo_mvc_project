package br.univali.cc.prog3.banco.exceptions;

/*
    Classe responsável por sinalizar o erro de tentar criar uma conta que já existe
 */
public class ContaDuplicadaException extends Exception {
    public ContaDuplicadaException() {}
    public ContaDuplicadaException(String message) {
        super(message);
    }
}
