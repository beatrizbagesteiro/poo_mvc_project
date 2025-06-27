/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.banco.model;

import br.univali.cc.prog3.banco.exceptions.SaldoInsuficienteException;
import br.univali.cc.prog3.banco.exceptions.ValorNegativoException;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
    // Indica se a conta é uma conta especial (com limite).
    private boolean especial;
    // O limite de crédito disponível para contas especiais.
    private double limite;
    // O número único da conta corrente.
    private int numero;
    private double saldo = 0;
    // Uma lista para armazenar todas as movimentações da conta.
    private List<Movimentacao> movimentacoes = new ArrayList<>();


    /*
     Construtor da classe ContaCorrente para contas especiais.
     Recebe como parâmetro o número da conta, o valor do depósito inicial e o limite da conta.
     Lança a exceção: ValorNegativoException se o depósito inicial for negativo.
     */
    public ContaCorrente(int numero, double depositoInicial,  double limite) throws ValorNegativoException {
        this.especial = true;
        this.limite = limite;
        this.numero = numero;
        this.depositar(depositoInicial);
    }
    /*
      Construtor da classe ContaCorrente para contas normais.
      Recebe como parâmetro o número da conta e o valor do depósito inicial.
      Lança a exceção: ValorNegativoException se o depósito inicial for negativo.
    */
    public ContaCorrente(int numero, double depositoInicial) throws ValorNegativoException {
        this.especial = false;
        this.limite = 0;
        this.numero = numero;
        this.depositar(depositoInicial);
    }

    /*
     Retorna o saldo total disponível na conta, incluindo o limite se for uma conta especial.
     Não recebe parâmetros.
     Retorna o saldo da conta (saldo + limite, se especial, ou apenas saldo, se normal).
    */
    public double getSaldo() {
        return this.especial ? this.saldo+this.limite : this.saldo;
    }

    /*
     Retorna o número da conta corrente.
    */
    public int getNumeroConta() {
        return numero;
    }
    /*
    Realiza um depósito na conta.
    Recebe como parâmetro o valor a ser depositado.
    Lança a exceção: ValorNegativoException se o valor do depósito for negativo.
    */
    public void depositar(double valor) throws ValorNegativoException {
        if (valor > 0) {
            this.saldo += valor;
            this.criarMovimentacao("Deposito", 'C', valor);
        }else{
            throw new ValorNegativoException();
        }
    }

    /*
     Realiza um saque da conta.
     Recebe como parâmetro o valor a ser sacado.
     Lança as exceções: SaldoInsuficienteException se não houver saldo suficiente na conta (incluindo o limite).
                        ValorNegativoException se o valor do saque for negativo.
     */
    public void sacar(double valor) throws SaldoInsuficienteException, ValorNegativoException {
        if (this.getSaldo() >= valor && valor > 0) {
            this.saldo -= valor;
            this.criarMovimentacao("Saque", 'D', valor);
        }else if(valor < 0){
            throw new ValorNegativoException();
        }else{
            throw new SaldoInsuficienteException();
        }
    }

    /*
     Emite o extrato de todas as movimentações da conta, incluindo o saldo final.
     Retorna uma String formatada contendo o extrato da conta.
    */
    public String emitirExtrato() {
        String extrato = "";
        for (Movimentacao movimentacao:movimentacoes) {
            extrato += "\n"+movimentacao.getMovimentacao();
        }
        extrato += "\n Saldo final: R$"+this.getSaldo();
        return extrato;
    }

    /*
     Cria e adiciona uma nova movimentação à lista de movimentações da conta. É um método auxiliar privado.
     Recebe como parâmetro a descrição da movimentação, o tipo (Débito 'D' ou Crédito 'C') e o valor.
    */
    private void criarMovimentacao(String descricao, char tipo, double valor) {
        Movimentacao movimentacao = new Movimentacao(descricao, tipo, valor);
        movimentacoes.add(movimentacao);
    }
    
}
