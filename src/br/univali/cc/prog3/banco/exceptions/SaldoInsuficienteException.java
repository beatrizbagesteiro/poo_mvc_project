package br.univali.cc.prog3.banco.exceptions;

/*
    Classe responsável por sinalizar o erro de saldo insuficiente para alguma operação (saque ou transferência)
 */
public class SaldoInsuficienteException extends Exception {
  public SaldoInsuficienteException() {}
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
