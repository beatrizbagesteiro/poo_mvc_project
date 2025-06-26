/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.banco.model;

/*
    Classe responsável pelo armazenamento e processamento de dados relacionados as movimentações de uma conta.
 */

public class Movimentacao {
    private final String descricao;
    private final char tipo;
    private final double valor;   

    public Movimentacao(String descricao, char tipo, double valor) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getMovimentacao(){
        return this.descricao+" R$"+(this.tipo=='D'?"-":"")+this.valor;
    }

}
