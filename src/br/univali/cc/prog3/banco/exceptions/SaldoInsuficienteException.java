package br.univali.cc.prog3.banco.exceptions;

public class SaldoInsuficienteException extends Exception {
  public SaldoInsuficienteException() {}
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
