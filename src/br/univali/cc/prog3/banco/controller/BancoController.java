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


/*
    A camada Controller interage com a camada Service para executar as operações de negócio.
    Ele atua como uma ponte entre a interface do usuário e a lógica de negócios.
 */
public class BancoController {
    private BancoService service;

    /*
     Construtor da classe BancoController.
     Inicializa o service do banco com uma instância de Banco (para quem esses serviços estão sendo realizados? Para x Banco).
     */
    public BancoController(Banco instituicao){
        this.service = new BancoService(instituicao);
    }

    /*
     Cria uma nova conta bancária com um saldo inicial. Delega a operação para o BancoService.
     Recebe como parâmetro o saldo inicial da nova conta e retorna o número da conta criada.
     Lança as exceções: ContaDuplicadaException se uma conta com o mesmo número já existir.
                        ValorNegativoException se o saldo inicial for negativo.
     */
    public int criarConta(double saldoInicial) throws ContaDuplicadaException, ValorNegativoException {
        return service.criarConta(saldoInicial);
    }

    /*
     Cria uma nova conta bancária especial, com um saldo inicial e um limite. Delega a operação para o BancoService.
     Recebe como parâmetro o saldo inicial da nova conta, limite de credito associado a conta e retorna o número da conta criada.
     Lança as exceções: ContaDuplicadaException se uma conta com o mesmo número já existir.
                        ValorNegativoException se o saldo inicial for negativo.
     */
    public int criarConta(double saldoInicial, double limite) throws ContaDuplicadaException,ValorNegativoException{
        return service.criarConta(saldoInicial,limite);
    }

    /*
     Realiza um depósito em uma conta específica. Delega a operação para o BancoService.
     Recebe como parâmetro o número da conta onde o depósito será feito e o valor a ser depositado.
     Lança as exceções: ValorNegativoException se o valor do depósito for negativo.
                        ContaInexistenteException se a conta não for encontrada.
     */
    public void depositar(int numeroConta, double valor) throws ValorNegativoException, ContaInexistenteException {
        service.depositar(numeroConta,valor);
    }

    /*
     Realiza um saque de uma conta específica. Delega a operação para o BancoService.
     Recebe como parâmetro o número da conta de onde o saque será feito e valor a ser sacado.
     Lança as exceções: SaldoInsuficienteException se não houver saldo suficiente na conta.
                        ContaInexistenteException se a conta não for encontrada.
                        ValorNegativoException se o valor do saque for negativo.
     */
    public void sacar(int numeroConta, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException {
        service.sacar(numeroConta, valor);
    }

    /*
     Realiza uma transferência entre duas contas. Delega a operação para o BancoService.
     Recebe como parâmetro o número da conta de origem, o número da conta de destino e o valor a ser transferido.
     Lança as exceções: SaldoInsuficienteException se a conta de origem não tiver saldo suficiente.
                        ContaInexistenteException se uma das contas (origem ou destino) não for encontrada.
                        ValorNegativoException se o valor da transferência for negativo.
    */
    public void transferencia(int numeroContaOrigem, int numeroContaDestino, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException {
        service.transferencia(numeroContaOrigem, numeroContaDestino, valor);
    }

    /*
     Emite o extrato de uma conta específica. Delega a operação para o BancoService.
     Recebe como parâmetro o número da conta para a qual o extrato será emitido e retorna uma String contendo o extrato da conta.
     Lança a exceção: ContaInexistenteException se a conta não for encontrada.
     */
    public String emitirExtrato(int numeroConta) throws ContaInexistenteException {
        return service.emitirExtrato(numeroConta);
    }

    /*
     Retorna a instância do objeto Banco que está sendo gerenciada pelo serviço.
     */
    public Banco getBanco(){
        return service.getInstituicao();
    }
}
