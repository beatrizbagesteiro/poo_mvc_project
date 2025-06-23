package br.univali.cc.prog3.banco.model;

public class Banco {
    private String nome;
    private int numero;

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
}
