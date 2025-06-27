package br.univali.cc.prog3.banco.model;

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

    public ContaCorrente getConta(int numeroConta) {
        return contas.get(numeroConta);
    }

    public void adicionarConta(ContaCorrente conta) {
        contas.put(conta.getNumeroConta(), conta);
    }
}
