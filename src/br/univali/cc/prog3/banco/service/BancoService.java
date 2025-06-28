package br.univali.cc.prog3.banco.service;
import br.univali.cc.prog3.banco.exceptions.ContaDuplicadaException;
import br.univali.cc.prog3.banco.exceptions.ContaInexistenteException;
import br.univali.cc.prog3.banco.exceptions.SaldoInsuficienteException;
import br.univali.cc.prog3.banco.exceptions.ValorNegativoException;
import br.univali.cc.prog3.banco.model.Banco;
import br.univali.cc.prog3.banco.model.ContaCorrente;


public class BancoService {
    // A instância da instituição bancária associada a este serviço.
    private Banco instituicao;

    /*
     Construtor da classe BancoService.
     Recebe como parâmetro a instância do Banco para inicializar o serviço.
    */
    public BancoService(Banco instituicaoBanco) {
        this.instituicao = instituicaoBanco;
    }

    public int criarConta(double saldoInicial) throws ContaDuplicadaException, ValorNegativoException {
        return instituicao.criarConta(saldoInicial); // Delega para o Banco
    }

    public int criarConta(double saldoInicial, double limite) throws ContaDuplicadaException, ValorNegativoException {
        return instituicao.criarConta(saldoInicial, limite); // Delega para o Banco
    }

    /*
     Realiza um depósito em uma conta específica.
     Recebe como parâmetro o número da conta onde o depósito será feito e o valor a ser depositado.
     Lança as exceções: ValorNegativoException se o valor do depósito for negativo.
                        ContaInexistenteException se a conta não for encontrada.
    */
    public void depositar(int numeroConta, double valor) throws ValorNegativoException, ContaInexistenteException {
        ContaCorrente conta = instituicao.localizarConta(numeroConta);
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
        ContaCorrente conta = instituicao.localizarConta(numeroConta);
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
        ContaCorrente contaOrigem = instituicao.localizarConta(numeroContaOrigem);
        ContaCorrente contaDestino = instituicao.localizarConta(numeroContaDestino);
        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);
    }

    /*
     Emite o extrato de uma conta específica.
     Recebe como parâmetro o número da conta para a qual o extrato será emitido e retorna uma String contendo o extrato da conta.
     Lança a exceção: ContaInexistenteException se a conta não for encontrada.
    */
    public String emitirExtrato(int numeroConta) throws ContaInexistenteException{
        ContaCorrente conta = instituicao.localizarConta(numeroConta);
        return conta.emitirExtrato();
    }

    /*
     Retorna a instância do objeto Banco associada a este serviço.
    */
    public Banco getInstituicao() {
        return instituicao;
    }
}
