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
    private boolean especial;
    private double limite;
    private int numero;
    private double saldo = 0;
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public ContaCorrente(int numero, double depositoInicial,  double limite) throws ValorNegativoException {
        this.especial = true;
        this.limite = limite;
        this.numero = numero;
        this.depositar(depositoInicial);
    }
    
    public ContaCorrente(int numero, double depositoInicial) throws ValorNegativoException {
        this.especial = false;
        this.limite = 0;
        this.numero = numero;
        this.depositar(depositoInicial);
    }

    public double getSaldo() {
        return this.especial ? this.saldo+this.limite : this.saldo;
    } 

    public int getNumeroConta() {
        return numero;
    }
    
    public boolean depositar(double valor) throws ValorNegativoException {
        if (valor > 0) {
            this.saldo += valor;
            this.criarMovimentacao("Deposito", 'C', valor);
            return true;
        }
        throw new ValorNegativoException();
    }
    
    public boolean sacar(double valor) throws SaldoInsuficienteException, ValorNegativoException {
        if (this.getSaldo() >= valor && valor > 0) {
            this.saldo -= valor;
            this.criarMovimentacao("Saque", 'D', valor);
            return true;
        }else if(valor < 0){
            throw new ValorNegativoException();
        }else{
            throw new SaldoInsuficienteException();
        }
    }
    
    public String emitirExtrato() {
        String extrato = "";
        for (Movimentacao movimentacao:movimentacoes) {
            extrato += "\n"+movimentacao.getMovimentacao();
        }
        extrato += "\n Saldo final: R$"+this.getSaldo();
        return extrato;
    }
    
    private void criarMovimentacao(String descricao, char tipo, double valor) {
        Movimentacao movimentacao = new Movimentacao(descricao, tipo, valor);
        movimentacoes.add(movimentacao);
    }
    
}
