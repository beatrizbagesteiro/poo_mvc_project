/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.banco.controller;

import br.univali.cc.prog3.banco.exceptions.ContaDuplicadaException;
import br.univali.cc.prog3.banco.exceptions.ContaInexistenteException;
import br.univali.cc.prog3.banco.exceptions.SaldoInsuficienteException;
import br.univali.cc.prog3.banco.exceptions.ValorNegativoException;
import br.univali.cc.prog3.banco.model.Banco;
import br.univali.cc.prog3.banco.service.BancoService;

public class BancoController {
    private BancoService service;

    public BancoController(Banco instituicao){
        this.service = new BancoService(instituicao);
    }

    public int criarConta(double saldoInicial) throws ContaDuplicadaException, ValorNegativoException {
        return service.criarConta(saldoInicial);
    }

    public int criarConta(double saldoInicial, double limite) throws ContaDuplicadaException,ValorNegativoException{
        return service.criarConta(saldoInicial,limite);
    }

    public void depositar(int numeroConta, double valor) throws ValorNegativoException, ContaInexistenteException {
        service.depositar(numeroConta,valor);
    }

    public void sacar(int numeroConta, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException {
        service.sacar(numeroConta, valor);
    }

    public void transferencia(int numeroContaOrigem, int numeroContaDestino, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException {
        service.transferencia(numeroContaOrigem, numeroContaDestino, valor);
    }

    public String emitirExtrato(int numeroConta) throws ContaInexistenteException {
        return service.emitirExtrato(numeroConta);
    }

    public Banco getBanco(){
        return service.getInstituicao();
    }
}
