package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.controllers.ViewModels.JanelaPedidosViewModel;
import ifpr.pgua.eic.setgo.models.entities.Estoque;
import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaPedido implements Initializable {
    
    @FXML
    private TextField tfIdPedido;

    @FXML
    private TextField tfValorTotal;

    @FXML
    private ListView<Pedido> ltvPedidos;
    
    @FXML
    private ComboBox<Produto> cbProdutos;

    @FXML
    private TableView<Produto> tbProdutos;
    
    @FXML
    private TableColumn<ItensPedido,String> tbcProduto;

    @FXML
    private TableColumn<ItensPedido,Double> tbcQuantidade;

    @FXML
    private TableColumn<ItensPedido,Float> tbcValor;

    @FXML
    private TableColumn<ItensPedido,Float> tbcValorItem;

    private Estoque estoque;
    
    private JanelaPedidosViewModel viewModel;

    public JanelaPedido(JanelaPedidosViewModel viewModel){
        this.viewModel= viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cbProdutos.setItems(viewModel.getProdutos());;
        //ltvPedidos.getItems().addAll(estoque.getPedidos());
        
        tbcProduto.setCellValueFactory(item -> new SimpleStringProperty(
                item.getValue().getProduto().getNome()));   
        tbcQuantidade.setCellValueFactory(item -> new SimpleDoubleProperty(
                item.getValue().getQuantidade()).asObject());   
        tbcValorItem.setCellValueFactory(item -> new SimpleFloatProperty(
                item.getValue().getProduto().getPreco()).asObject());   
        tbcValor.setCellValueFactory(item -> new SimpleFloatProperty(
                item.getValue().getPreco()).asObject());
        
        tbProdutos.setItems(viewModel.getProdutos());
        
        viewModel.carregaLista();
    }

    @FXML
    private void registrarPedido(ActionEvent evento){
        int idPedido = Integer.parseInt(tfIdPedido.getText());
        float precoParse = Float.parseFloat(tfValorTotal.getText());
    

        if(estoque.adicionarPedido(idPedido, LocalDate.now(), precoParse)){
            Alert alert = new Alert(AlertType.INFORMATION,"Pedido realizado");
                alert.showAndWait();
                limpar();
        }else{
                Alert alert = new Alert(AlertType.INFORMATION,"Pedido Não Realizado");
                alert.showAndWait();  
        }
        ltvPedidos.getItems().addAll(estoque.getPedidos());
    }

    private void limpar(){
        tfIdPedido.clear();
        tfValorTotal.clear();
    }
}
