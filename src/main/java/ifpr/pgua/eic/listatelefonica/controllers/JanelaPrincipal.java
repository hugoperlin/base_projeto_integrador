package ifpr.pgua.eic.listatelefonica.controllers;

import ifpr.pgua.eic.listatelefonica.App;
import ifpr.pgua.eic.listatelefonica.utils.BorderPaneRegion;
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

    
}
