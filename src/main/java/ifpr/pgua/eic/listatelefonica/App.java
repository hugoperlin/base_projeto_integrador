package ifpr.pgua.eic.listatelefonica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

<<<<<<< HEAD
import ifpr.pgua.eic.listatelefonica.controllers.JanelaCadastro;
import ifpr.pgua.eic.listatelefonica.controllers.JanelaLista;
=======
>>>>>>> parent of 8692235 (corrigindo utils)
import ifpr.pgua.eic.listatelefonica.controllers.JanelaPrincipal;
import ifpr.pgua.eic.listatelefonica.models.FabricaConexoes;
import ifpr.pgua.eic.listatelefonica.models.daos.ContatoDAO;
import ifpr.pgua.eic.listatelefonica.models.daos.JDBCContatoDAO;
import ifpr.pgua.eic.listatelefonica.models.repositories.ContatoRepository;
import ifpr.pgua.eic.listatelefonica.utils.BaseAppNavigator;
import ifpr.pgua.eic.listatelefonica.utils.ScreenRegistry;
=======
>>>>>>> parent of 8692235 (corrigindo utils)

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    public static final ScreenRegistry PRINCIPAL=new ScreenRegistry(App.class, "principal.fxml", o->new JanelaPrincipal());
    
    private FabricaConexoes fabricaConexoes = FabricaConexoes.getInstance();
    private ContatoDAO contatoDAO;
    private ContatoRepository contatoRepository;

    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();

        
        contatoDAO = new JDBCContatoDAO(fabricaConexoes);
        contatoRepository = new ContatoRepository(contatoDAO);
    }

    @Override
<<<<<<< HEAD
    public String getHome() {
        return "PRINCIPAL";
    }

=======
    public ScreenRegistry getHomeFXML() {
        // TODO Auto-generated method stub
        return PRINCIPAL;
    }

    @Override
    public Parent getHomeParent() {
        // TODO Auto-generated method stub
        return null;
    }

>>>>>>> parent of 8692235 (corrigindo utils)
    @Override
    public String getAppTitle() {
        return "Lista Telefônica";
    }

<<<<<<< HEAD
    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "fxml/principal.fxml", o->new JanelaPrincipal()));
        registraTela("CADASTRO", new ScreenRegistryFXML(App.class, "fxml/cadastro.fxml", o->new JanelaCadastro(contatoRepository)));
        registraTela("LISTA", new ScreenRegistryFXML(App.class, "fxml/listar.fxml", o->new JanelaLista(contatoRepository)));
        
    }

    @Override
    public void atualizaEstilo() {
        adicionarArquivoEstilo(getClass().getResource("css/estilo.css").toExternalForm());
    }
    


    

=======
>>>>>>> parent of 8692235 (corrigindo utils)
}