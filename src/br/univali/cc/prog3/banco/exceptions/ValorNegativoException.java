package br.univali.cc.prog3.banco.exceptions;

/*
    Classe responsável por sinalizar o erro de valor negativo para alguma operação (saque, deposito, transferencia)
 */
public class ValorNegativoException extends Exception {
  public ValorNegativoException() {}
    public ValorNegativoException(String message) {
        super(message);
    }
}
