package br.univali.cc.prog3.banco.model;

import br.univali.cc.prog3.banco.exceptions.ContaDuplicadaException;
import br.univali.cc.prog3.banco.exceptions.ContaInexistenteException;
import br.univali.cc.prog3.banco.exceptions.ValorNegativoException;

import java.util.HashMap;
import java.util.Map;

/*
    Classe responsável pelo armazenamento e processamento de dados relacionados a uma instituição bancária.
 */
public class Banco {
    private String nome;
    private int numero;
    private Map<Integer, ContaCorrente> contas = new HashMap<>();
    private int proximaConta = 0;

    public Banco(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public int getProximaConta() {
        return proximaConta;
    }

    public void incrementarProximaConta() {
        this.proximaConta++;
    }

    public ContaCorrente localizarConta(int numero) throws ContaInexistenteException{
        ContaCorrente conta = contas.get(numero);
        if( conta != null ){
            return conta;
        }
        throw new ContaInexistenteException();
    }

    public void adicionarConta(ContaCorrente conta) {
        contas.put(conta.getNumeroConta(), conta);
    }

    public int criarConta(double saldoInicial) throws ContaDuplicadaException, ValorNegativoException {
        int novoNumeroConta = this.proximaConta + 1;
        if (contas.containsKey(novoNumeroConta)) {
            throw new ContaDuplicadaException("Conta com número " + novoNumeroConta + " já existe no banco.");
        }
        ContaCorrente novaConta = new ContaCorrente(novoNumeroConta, saldoInicial);
        this.adicionarConta(novaConta);
        this.incrementarProximaConta();
        return novoNumeroConta;
    }

    public int criarConta(double saldoInicial, double limite) throws ContaDuplicadaException, ValorNegativoException {
        int novoNumeroConta = this.proximaConta + 1;
        if (contas.containsKey(novoNumeroConta)) {
            throw new ContaDuplicadaException("Conta com número " + novoNumeroConta + " já existe no banco.");
        }
        ContaCorrente novaConta = new ContaCorrente(novoNumeroConta, saldoInicial, limite);
        this.adicionarConta(novaConta);
        this.incrementarProximaConta();
        return novoNumeroConta;
    }
}
