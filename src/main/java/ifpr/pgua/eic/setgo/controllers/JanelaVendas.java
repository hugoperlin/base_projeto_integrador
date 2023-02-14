package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class JanelaVendas implements Initializable { 

    @FXML
    private ListView<ItensPedido> ltvItensPedidos;

    @FXML
    private TextField tfTotal;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      
        
    }
    
}

