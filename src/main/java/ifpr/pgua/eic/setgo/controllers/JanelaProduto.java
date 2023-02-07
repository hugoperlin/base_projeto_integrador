package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.controllers.ViewModels.JanelaProdutosViewModel;
import ifpr.pgua.eic.setgo.controllers.ViewModels.ProdutoRow;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class JanelaProduto implements Initializable {
    
    @FXML
    private ListView<Produto> ltvProdutos;

    @FXML
    private TableView<ProdutoRow> tblProdutos;
    
    @FXML 
    private TableColumn<ProdutoRow, String> idProduto; 

    @FXML 
    private TableColumn<ProdutoRow, String> nomeProduto; 

    @FXML 
    private TableColumn<ProdutoRow, String> descriProduto; 

    @FXML 
    private TableColumn<ProdutoRow, String> precoProduto; 
    
    @FXML 
    private Button btCadastrar;

    private JanelaProdutosViewModel viewModel;

    public JanelaProduto(JanelaProdutosViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listarProdutos();
        viewModel.selecionadoProperty().bind(tblProdutos.getSelectionModel().selectedItemProperty());

        viewModel.alertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            BaseController.showMessage(newVal);
        });

        //liga a propriedade texto do textfield nome com a propriedade do viewmodel
        nomeProduto.textProperty().bindBidirectional(viewModel.nomeProperty());
        //liga a propriedade editavel do textfield com a propriedade do viewmodel
        nomeProduto.editableProperty().bind(viewModel.podeEditarProperty());
        
        
        precoProduto.textProperty().bindBidirectional(viewModel.precoProperty());
        precoProduto.editableProperty().bind(viewModel.podeEditarProperty());

        descriProduto.textProperty().bindBidirectional(viewModel.descriProperty());
        idProduto.textProperty().bindBidirectional(viewModel.idProperty());

        btCadastrar.textProperty().bind(viewModel.operacaoProperty());
    }

    @FXML
    private void cadastrar(ActionEvent evento){
        viewModel.cadastrar();
    }
    
    public void listarProdutos(){
        idProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        nomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        precoProduto.setCellValueFactory(new PropertyValueFactory<>("precoProduto"));
        tblProdutos.setItems(viewModel.getProdutos());
    }

    @FXML
    private void cadastrar(){
        viewModel.cadastrar();
    }
    
    @FXML
    private void limpar(){
        viewModel.limpar();
    }
    
    @FXML
    private void atualizar(MouseEvent event){
        if(event.getClickCount() == 2){
            viewModel.atualizar();
        }
    }

}

