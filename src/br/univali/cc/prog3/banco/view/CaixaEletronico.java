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
    // Referência ao controlador do banco, que gerencia as operações bancárias.
    private BancoController controller;

    /*
     Construtor da classe CaixaEletronico.
     Recebe como parâmetro uma instância de BancoController para permitir a interação com os serviços do banco.
    */
    public CaixaEletronico(BancoController banco) {
        this.controller = banco;
    }

    /*
     Exibe o menu principal do caixa eletrônico e gerencia as opções do usuário.
     O menu é exibido repetidamente até que o usuário escolha a opção de sair ('7').
     */
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

    /*
     Método auxiliar para ler um valor do console.
     Recebe como parâmetro um rótulo (String) para exibir ao usuário.
     Retorna a String lida do console.
     */
    private String lerValor(String rotulo) {
        Scanner leitor = new Scanner(System.in);
        System.out.print(rotulo+": ");
        return leitor.nextLine();
    }

    /*
     Cria uma nova conta corrente normal.
     Solicita o saldo inicial ao usuário e tenta criar a conta usando o BancoController.
     Exibe mensagens de sucesso ou de erro, caso ocorram exceções.
    */
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
    /*
     Cria uma nova conta corrente especial.
     Solicita o saldo inicial ao usuário e tenta criar a conta usando o BancoController.
     Exibe mensagens de sucesso ou de erro, caso ocorram exceções.
    */
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
    /*
    Realiza um depósito em uma conta.
    Solicita o número da conta e o valor do depósito ao usuário.
    Tenta realizar o depósito usando o BancoController e exibe mensagens de sucesso ou de erro, caso ocorram exceções.
    */
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
    /*
     Realiza um saque de uma conta.
     Solicita o número da conta e o valor do saque ao usuário.
     Tenta realizar o saque usando o BancoController e exibe mensagens de sucesso ou de erro, caso ocorram exceções.
     */
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

    /*
     Realiza uma transferência entre duas contas.
     Solicita os números das contas de origem e destino, e o valor da transferência ao usuário.
     Tenta realizar a transferência usando o BancoController e exibe mensagens de sucesso ou de erro, caso ocorram exceções.
    */
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

    /*
     Emite o extrato de uma conta específica.
     Solicita o número da conta ao usuário.
     Exibe as informações do banco e tenta emitir o extrato usando o BancoController
     Exibe mensagens de erro caso a conta não exista.
    */
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
