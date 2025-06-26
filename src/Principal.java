
import br.univali.cc.prog3.banco.controller.BancoController;
import br.univali.cc.prog3.banco.model.Banco;
import br.univali.cc.prog3.banco.view.CaixaEletronico;


public class Principal {

    public static void main(String[] args) {
        //Cria uma instância de banco
        Banco instituicao = new Banco("Banco do Brasil",1);
        //Cria uma instância de controller
        BancoController controller = new BancoController(instituicao);
        //Cria uma instância do caixa eletrônico
        CaixaEletronico caixa = new CaixaEletronico(controller);
        //chama o menu de interação com o usuário
        caixa.menu();
    }
    
}
