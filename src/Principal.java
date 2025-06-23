
import br.univali.cc.prog3.banco.controller.BancoController;
import br.univali.cc.prog3.banco.model.Banco;
import br.univali.cc.prog3.banco.view.CaixaEletronico;


public class Principal {

    public static void main(String[] args) {
        Banco instituicao = new Banco("Banco do Brasil",1);
        BancoController controller = new BancoController(instituicao);
        CaixaEletronico caixa = new CaixaEletronico(controller);
        caixa.menu();
    }
    
}
