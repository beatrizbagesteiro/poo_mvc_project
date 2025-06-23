/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.banco.view;

import br.univali.cc.prog3.banco.controller.BancoController;
import br.univali.cc.prog3.banco.exceptions.ContaDuplicadaException;
import br.univali.cc.prog3.banco.exceptions.ContaInexistenteException;
import br.univali.cc.prog3.banco.exceptions.SaldoInsuficienteException;
import br.univali.cc.prog3.banco.exceptions.ValorNegativoException;

import java.util.Scanner;

public class CaixaEletronico {
    private BancoController controller;

    public CaixaEletronico(BancoController banco) {
        this.controller = banco;
    }
    
    public void menu() {
        char opcao;
        do {
            System.out.println("Caixa eletronico");
            System.out.println("1 - criar conta normal");
            System.out.println("2 - criar conta especial");
            System.out.println("3 - depositar");
            System.out.println("4 - sacar");
            System.out.println("5 - transferir");
            System.out.println("6 - emitir extrato");
            System.out.println("7 - sair");
            opcao = lerValor("Digite uma opcao").charAt(0);
            
            switch(opcao) {
                case '1': criarContaNormal(); break;
                case '2': criarContaEspecial(); break;
                case '3': depositar(); break;
                case '4': sacar(); break;
                case '5': transferir(); break;
                case '6': emitirExtrato(); break;
                case '7': System.out.println("adeus"); break;
                default: System.out.println("Opcao invalida"); break;
            }
            
        } while (opcao != '7');
    }
    
    private String lerValor(String rotulo) {
        Scanner leitor = new Scanner(System.in);
        System.out.print(rotulo+": ");
        return leitor.nextLine();
    }

    private void criarContaNormal() {
        double saldo = Double.parseDouble(lerValor("Informe o saldo inicial"));
        try{
            int numero = controller.criarConta(saldo);
            System.out.println("Conta de número " + numero + " criada com sucesso!");
        } catch (ContaDuplicadaException e) {
            System.out.println("Número de conta em uso!");
        } catch (ValorNegativoException e) {
            System.out.println("Não é possivel fazer depositos com números negativos!");
        }

    }

    private void criarContaEspecial() {
        double saldo = Double.parseDouble(lerValor("Informe o saldo inicial"));
        double limite = Double.parseDouble(lerValor("Informe o limite"));
        try{
            int numero = controller.criarConta(saldo,limite);
            System.out.println("Conta de número " + numero + " criada com sucesso!");
        } catch (ContaDuplicadaException e) {
            System.out.println("Número de conta em uso!");
        } catch (ValorNegativoException e) {
            System.out.println("Não é possivel fazer depositos com números negativos!");
        }
    }

    private void depositar() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(lerValor("Informe o valor"));
        try {
            controller.depositar(numero, valor);
        } catch (ValorNegativoException e) {
            System.out.println("Não é possivel fazer depositos com números negativos!");
        } catch (ContaInexistenteException e) {
            System.out.println("O número de conta informado não existe!");
        }

    }

    private void sacar() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(lerValor("Informe o valor"));
        try{
            controller.sacar(numero, valor);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Saldo insuficiente!");
        } catch (ContaInexistenteException e) {
            System.out.println("O número de conta informado não existe!");
        } catch (ValorNegativoException e) {
            System.out.println("Não é possivel fazer depositos com números negativos!");
        }

    }

    private void transferir() {
        int numeroOrigem = Integer.parseInt(lerValor("Digite o numero da conta de origem"));
        int numeroDestino = Integer.parseInt(lerValor("Digite o numero da conta de destino"));
        double valor = Double.parseDouble(lerValor("Informe o valor"));
        try{
            controller.transferencia(numeroOrigem, numeroDestino, valor);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Saldo insuficiente!");
        } catch (ContaInexistenteException e) {
            System.out.println("O número de conta informado não existe!");
        } catch (ValorNegativoException e) {
            System.out.println("Não é possivel fazer depositos com números negativos!");
        }

    }

    private void emitirExtrato() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        System.out.println("Numero da conta: " + numero);
        System.out.println(controller.getBanco().getNome() + " - " + controller.getBanco().getNumero());
        try {
            System.out.println(controller.emitirExtrato(numero));
        } catch (ContaInexistenteException e) {
            System.out.println("O número de conta informado não existe!");
        }
    }
    
}
