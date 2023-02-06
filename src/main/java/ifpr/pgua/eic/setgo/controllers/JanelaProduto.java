package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.controllers.ViewModels.ProdutoRow;
import ifpr.pgua.eic.setgo.models.Estoque;
import ifpr.pgua.eic.setgo.models.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private final TableView<ProdutoRow> tblProdutos;
    
    @FXML 
    private TableColumn<ProdutoRow, String> idProduto; 

    @FXML 
    private TableColumn<ProdutoRow, String> nomeProduto; 

    @FXML 
    private TableColumn<ProdutoRow, String> precoProduto; 

    private Estoque estoque;

    public JanelaProduto(Estoque estoque){
        this.estoque = estoque;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ltvProdutos.getItems().addAll(estoque.getProdutos());
        listarProdutos();
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
        listarProdutos();
    }
    
    public void listarProdutos(){
        ObservableList<Produto> data = FXCollections
                .observableArrayList(estoque.getProdutos());
        idProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        nomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        precoProduto.setCellValueFactory(new PropertyValueFactory<>("precoProduto"));
        tblProdutos.setItems(data);
        tblProdutos.getColumns().addAll(idProduto, nomeProduto, precoProduto);
    }

    private void limpar(){
        tfNome.clear();
        tfDescri.clear();
        tfPreco.clear();
    }

}

