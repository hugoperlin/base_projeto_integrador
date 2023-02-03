package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.models.Estoque;
import ifpr.pgua.eic.setgo.models.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaProduto implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescri;

    @FXML
    private TextField tfPreco;
    
    @FXML
    private ListView<Produto> ltvProdutos;

    @FXML
    private TableView<Produto> tblProdutos;
    
    @FXML 
    private TableColumn<Produto, String> idProduto; 

    @FXML 
    private TableColumn<Produto, String> nomeProduto; 

    @FXML 
    private TableColumn<Produto, String> precoProduto; 

    private Estoque estoque;

    public JanelaProduto(Estoque estoque){
        this.estoque = estoque;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ltvProdutos.getItems().addAll(estoque.getProdutos());
        tblProdutos.getItems().addAll(estoque.getProdutos());
    }

    @FXML
    private void cadastrar(ActionEvent evento){
        String nome = tfNome.getText();
        String descricao = tfDescri.getText();
        String preco = tfPreco.getText();
        float precoParse = Float.parseFloat(preco);

        String msg = "Cadastro realizado!";
        if(!estoque.adicionarProduto(nome, descricao, precoParse)){
            msg = "Cadastro não realizado!";
        }else{
            limpar();
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();
        ltvProdutos.getItems().clear();
        ltvProdutos.getItems().addAll(estoque.getProdutos());
        tblProdutos.getItems().clear();
        tblProdutos.getItems().addAll(estoque.getProdutos());
    }

    private void limpar(){
        tfNome.clear();
        tfDescri.clear();
        tfPreco.clear();
    }

}

