package br.univali.cc.prog3.banco.model;

/*
    Classe responsável pelo armazenamento e processamento de dados relacionados a uma instituição bancária.
 */
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
