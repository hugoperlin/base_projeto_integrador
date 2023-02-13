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
    private TextField tfQuantidade;

    @FXML
    private TextField tfValor;

    @FXML
    private ListView<ItensPedido> ltvPedidos;
    
    @FXML
    private ComboBox<Produto> cbProdutos;

    @FXML
    private TableView<Produto> tbProdutos;
    
    @FXML
    private TableColumn<Produto,String> tbcProduto;

    @FXML
    private TableColumn<Produto,Double> tbcQuantidade;

    @FXML
    private TableColumn<Produto,Float> tbcValor;

    @FXML
    private TableColumn<Produto,String> tbcDescricao;

    Pedido pedido;

    private Estoque estoque;
    
    private JanelaPedidosViewModel viewModel;

    public JanelaPedido(JanelaPedidosViewModel viewModel, Estoque estoque){
        this.viewModel= viewModel;
        this.estoque = estoque;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.pedido = new Pedido(LocalDate.now());
        cbProdutos.setItems(viewModel.getProdutos());;
        //ltvPedidos.getItems().addAll(estoque.getPedidos());
        
        tbcProduto.setCellValueFactory(item -> new SimpleStringProperty(
                item.getValue().getNome()));   
        tbcDescricao.setCellValueFactory(item -> new SimpleStringProperty(
                item.getValue().getDescricao()));   
        tbcQuantidade.setCellValueFactory(item -> new SimpleDoubleProperty(
                item.getValue().getQuantidade()).asObject());   
        tbcValor.setCellValueFactory(item -> new SimpleFloatProperty(
                item.getValue().getPreco()).asObject());
        
        tbProdutos.setItems(viewModel.getProdutos());

        tfQuantidade.setText("0.0");
        tfValor.setText("0.0");;
        
        viewModel.carregaLista();
    }

    @FXML
    private void registrarItens(ActionEvent evento){
        Produto produto = cbProdutos.getValue();
        double quantidade = Double.parseDouble(tfQuantidade.getText());
        //float precoParse = (float) (quantidade*produto.getPreco());

        ItensPedido itens = new ItensPedido(produto, quantidade);

        //tfValor.setText(String.valueOf(itens.getPreco()));

        // if(estoque.adicionarPedido(idPedido, LocalDate.now(), precoParse)){
        //     Alert alert = new Alert(AlertType.INFORMATION,"Pedido realizado");
        //         alert.showAndWait();
        //         limpar();
        // }else{
        //         Alert alert = new Alert(AlertType.INFORMATION,"Pedido Não Realizado");
        //         alert.showAndWait();  
        // }

        pedido.add(itens);        

        ltvPedidos.getItems().addAll(pedido.getItens());
        limpar();
    }

    @FXML
    private void registrarPedido(ActionEvent evento){
        Pedido novoPedido = new Pedido(LocalDate.now());
        viewModel.registrarPedido(pedido);
        limpar();
        pedido = novoPedido;
    }

    private void limpar(){
        tfQuantidade.setText("0.0");
        cbProdutos.setValue(null);
        ltvPedidos.getItems().clear();
        update();
    }

    private void update(){
        float valor = 0;
        if(pedido.getItens() != null){
            valor = pedido.getValorTotal();
        }
        tfValor.setText(String.valueOf(valor));
        ltvPedidos.getItems().addAll(pedido.getItens());
    }
}
