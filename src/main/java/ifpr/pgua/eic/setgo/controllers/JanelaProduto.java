package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.controllers.ViewModels.JanelaProdutosViewModel;
import ifpr.pgua.eic.setgo.controllers.ViewModels.ProdutoRow;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.results.Result;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class JanelaProduto extends BaseController implements Initializable {
    @FXML
    private ListView<Produto> ltvProdutos;

    @FXML
    private TableView<ProdutoRow> tblProdutos;
    
    @FXML 
    private Button btCadastrar;

    private JanelaProdutosViewModel viewModel;

    public JanelaProduto(JanelaProdutosViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TableColumn<ProdutoRow, Integer> idProdutoCol = new TableColumn<>("Id");
        idProdutoCol.setCellValueFactory(
                data -> new SimpleIntegerProperty(data.getValue()
                    .getProduto().getId()).asObject());

        TableColumn<ProdutoRow, String> nomeProdutoCol = new TableColumn<>("Produto");
        nomeProdutoCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue()
                    .getProduto().getNome()));

        TableColumn<ProdutoRow, String> descriProdutoCol = new TableColumn<>("Descrição");
        descriProdutoCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue()
                    .getProduto().getNome()));

        TableColumn<ProdutoRow, Float> precoProdutoCol = new TableColumn<>("Preço");
        precoProdutoCol.setCellValueFactory(
                data -> new SimpleFloatProperty(data.getValue()
                    .getProduto().getPreco()).asObject());

        tblProdutos.getColumns().addAll(
                idProdutoCol,nomeProdutoCol,descriProdutoCol,precoProdutoCol);

        //ligando a lista de ProdutoRow com a tabela
        tblProdutos.setItems(viewModel.getProdutos());

        viewModel.selecionadoProperty().bind(tblProdutos.getSelectionModel().selectedItemProperty());

        viewModel.alertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            BaseController.showMessage(newVal);
        });

        btCadastrar.textProperty().bind(viewModel.operacaoProperty());
    }

    @FXML
    void cadastrar(ActionEvent evento){
        Result resultado = viewModel.cadastrar();
        showMessage(resultado);
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

