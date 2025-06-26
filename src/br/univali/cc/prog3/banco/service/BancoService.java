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
    // Um mapa para armazenar as contas, onde a chave é o número da conta e o valor é o objeto ContaCorrente.
    private Map<Integer, ContaCorrente> contas = new HashMap<>();
    // Um contador para gerar o próximo número de conta disponível.
    private int proximaConta = 0;
    // A instância da instituição bancária associada a este serviço.
    private Banco instituicao;

    /*
     Construtor da classe BancoService.
     Recebe como parâmetro a instância do Banco para inicializar o serviço.
    */
    public BancoService(Banco instituicaoBanco) {
        this.instituicao = instituicaoBanco;
    }

    /*
     Cria uma nova conta corrente com um saldo inicial.
     Recebe como parâmetro o saldo inicial da nova conta.
     Retorna o número da conta recém-criada.
     Lança as exceções: ContaDuplicadaException se uma conta com o número gerado já existir.
                        ValorNegativoException se o saldo inicial for negativo.
    */
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

    /*
      Cria uma nova conta corrente com um saldo inicial e um limite.
      Recebe como parâmetro o saldo inicial da nova conta e o limite.
      Retorna o número da conta recém-criada.
      Lança as exceções: ContaDuplicadaException se uma conta com o número gerado já existir.
                         ValorNegativoException se o saldo inicial ou o limite for negativo.
    */
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

    /*
     Realiza um depósito em uma conta específica.
     Recebe como parâmetro o número da conta onde o depósito será feito e o valor a ser depositado.
     Lança as exceções: ValorNegativoException se o valor do depósito for negativo.
                        ContaInexistenteException se a conta não for encontrada.
    */
    public void depositar(int numeroConta, double valor) throws ValorNegativoException, ContaInexistenteException {
        ContaCorrente conta = localizarConta(numeroConta);
        conta.depositar(valor);
    }


    /*
     Realiza um saque de uma conta específica.
     Recebe como parâmetro o número da conta de onde o saque será feito e o valor a ser sacado.
     Lança as exceções: SaldoInsuficienteException se não houver saldo suficiente na conta.
                        ContaInexistenteException se a conta não for encontrada.
                        ValorNegativoException se o valor do saque for negativo.
    */
    public void sacar(int numeroConta, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException {
        ContaCorrente conta = localizarConta(numeroConta);
        conta.sacar(valor);
    }

    /*
     Realiza uma transferência de fundos entre duas contas.
     Recebe como parâmetro o número da conta de origem, o número da conta de destino e o valor a ser transferido.
     Lança as exceções: SaldoInsuficienteException se a conta de origem não tiver saldo suficiente.
                        ContaInexistenteException se uma das contas (origem ou destino) não for encontrada.
                        ValorNegativoException se o valor da transferência for negativo.
    */
    public void transferencia(int numeroContaOrigem, int numeroContaDestino, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorNegativoException{
        ContaCorrente contaOrigem = localizarConta(numeroContaOrigem);
        ContaCorrente contaDestino = localizarConta(numeroContaDestino);
        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);
    }

    /*
     Emite o extrato de uma conta específica.
     Recebe como parâmetro o número da conta para a qual o extrato será emitido e retorna uma String contendo o extrato da conta.
     Lança a exceção: ContaInexistenteException se a conta não for encontrada.
    */
    public String emitirExtrato(int numeroConta) throws ContaInexistenteException{
        ContaCorrente conta = localizarConta(numeroConta);
        return conta.emitirExtrato();
    }

    /*
     Localiza e retorna uma conta corrente pelo seu número. É um método auxiliar privado.
     Recebe como parâmetro o número da conta a ser localizada e retorna o objeto ContaCorrente se encontrado.
     Lança a exceção: ContaInexistenteException se a conta não for encontrada.
    */
    private ContaCorrente localizarConta(int numero) throws ContaInexistenteException{
        if(contas.containsKey(numero)){
            return contas.get(numero);
        }
        throw new ContaInexistenteException();
    }

    /*
     Retorna a instância do objeto Banco associada a este serviço.
    */
    public Banco getInstituicao() {
        return instituicao;
    }
}
