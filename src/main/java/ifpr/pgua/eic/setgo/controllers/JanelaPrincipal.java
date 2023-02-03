package ifpr.pgua.eic.setgo.controllers;

import ifpr.pgua.eic.setgo.App;
import ifpr.pgua.eic.setgo.utils.BorderPaneRegion;
import javafx.fxml.FXML;

public class JanelaPrincipal {

    @FXML
    private void carregaTelaCadastro(){
        App.changeScreenRegion("CADASTRO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaLista(){
        App.changeScreenRegion("LISTA", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaProdutos(){
        App.changeScreenRegion("PRODUTOS", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaPedidos(){
        App.changeScreenRegion("PEDIDOS", BorderPaneRegion.CENTER);
    }
    
    
}
