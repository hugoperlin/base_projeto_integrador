package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.models.Estoque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaProduto implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescri;

    @FXML
    private TextField tfPreco;


    private Estoque estoque;

    public JanelaProduto(Estoque estoque){
        this.estoque = estoque;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

    @FXML
    private void cadastrar(ActionEvent evento){
        String nome = tfNome.getText();
        String descricao = tfDescri.getText();
        String preco = tfPreco.getText();
        float precoParse = Float.parseFloat(preco);

        String msg = "Cadstro realizado!";
        if(!estoque.adicionarProduto(nome, descricao, precoParse)){
            msg = "Cadastro não realizado!";
        }else{
            limpar();
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();


    }

    private void limpar(){
        tfNome.clear();
        tfDescri.clear();
        tfPreco.clear();
    }


}

