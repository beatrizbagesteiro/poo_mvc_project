package br.univali.cc.prog3.banco.exceptions;

public class ValorNegativoException extends Exception {
  public ValorNegativoException() {}
    public ValorNegativoException(String message) {
        super(message);
    }
}
