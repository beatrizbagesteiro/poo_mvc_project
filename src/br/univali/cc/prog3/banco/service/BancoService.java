package br.univali.cc.prog3.banco.service;

import br.univali.cc.prog3.banco.exceptions.ContaDuplicadaException;
import br.univali.cc.prog3.banco.exceptions.ContaInexistenteException;
import br.univali.cc.prog3.banco.exceptions.SaldoInsuficienteException;
import br.univali.cc.prog3.banco.exceptions.ValorNegativoException;
import br.univali.cc.prog3.banco.model.Banco;
import br.univali.cc.prog3.banco.model.ContaCorrente;


import java.util.HashMap;
import java.util.Map;

public class BancoService {
    private Map<Integer, ContaCorrente> contas = new HashMap<>();
    private int proximaConta = 0;
    private Banco instituicao;

    public BancoService(Banco instituicaoBanco) {
        this.instituicao = instituicaoBanco;
    }

    public int criarConta(double saldoInicial) throws ContaDuplicadaException, ValorNegativoException {
        int numeroConta = proximaConta +1;
        try{
            localizarConta(numeroConta);
            throw new ContaDuplicadaException();
        } catch (ContaInexistenteException e) {
            ContaCorrente cc = new ContaCorrente(numeroConta, saldoInicial);
            contas.put(numeroConta, cc);
            return numeroConta;
        }
    }

    public int criarConta(double saldoInicial, double limite) throws ContaDuplicadaException, ValorNegativoException{
        int numeroConta = proximaConta +1;
        try{
            localizarConta(numeroConta);
            throw new ContaDuplicadaException();
        } catch (ContaInexistenteException e) {
            ContaCorrente cc = new ContaCorrente(numeroConta, saldoInicial, limite);
            contas.put(numeroConta, cc);
            return numeroConta;
        }
    }

    public void depositar(int numeroConta, double valor) throws ValorNegativoException, ContaInexistenteException {
        ContaCorrente conta = localizarConta(numeroConta);
        conta.depositar(valor);
    }

    public void sacar(int numeroConta, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException {
        ContaCorrente conta = localizarConta(numeroConta);
        conta.sacar(valor);
    }


    public void transferencia(int numeroContaOrigem, int numeroContaDestino, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException{
        ContaCorrente contaOrigem = localizarConta(numeroContaOrigem);
        ContaCorrente contaDestino = localizarConta(numeroContaDestino);
        if (contaOrigem.sacar(valor)) {
            contaDestino.depositar(valor);
        }
    }

    public String emitirExtrato(int numeroConta) throws ContaInexistenteException{
        ContaCorrente conta = localizarConta(numeroConta);
        return conta.emitirExtrato();
    }

    private ContaCorrente localizarConta(int numero) throws ContaInexistenteException{
        if(contas.containsKey(numero)){
            return contas.get(numero);
        }
        throw new ContaInexistenteException();
    }

    public Banco getInstituicao() {
        return instituicao;
    }
}
