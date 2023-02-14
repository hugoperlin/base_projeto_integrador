package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.controllers.ViewModels.JanelaPedidosViewModel;
import ifpr.pgua.eic.setgo.models.entities.Estoque;
import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.results.Result;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaPedido extends BaseController implements Initializable {
    
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

    @FXML
    private TableView<Pedido> tbPedidos;
    
    @FXML
    private TableColumn<Pedido,Integer> ptbcId;

    @FXML
    private TableColumn<Pedido,String> ptbcData;

    @FXML
    private TableColumn<Pedido,Float> ptbcValor;

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

        ptbcId.setCellValueFactory(item -> new SimpleIntegerProperty(
                item.getValue().getIdPedido()).asObject());
        ptbcData.setCellValueFactory(item -> new SimpleStringProperty(
                item.getValue().getData().toString()));
        ptbcValor.setCellValueFactory(item -> new SimpleFloatProperty(
                item.getValue().getValorTotal()).asObject());
        
        tbPedidos.setItems(viewModel.getPedidos());

        tfQuantidade.setText("0.0");
        tfValor.setText("0.0");;
        
        viewModel.carregaLista();
    }

    @FXML
    private void registrarItens(ActionEvent evento){
        Produto produto = cbProdutos.getValue();
        double quantidade = Double.parseDouble(tfQuantidade.getText());

        ItensPedido itens = new ItensPedido(produto, quantidade);

        pedido.add(itens);        

        ltvPedidos.getItems().addAll(pedido.getItens());
        limpar();
    }

    @FXML
    private void registrarPedido(ActionEvent evento){
        Pedido novoPedido = new Pedido(LocalDate.now());
        Result resultado = viewModel.registrarPedido(pedido);
        showMessage(resultado);
        pedido = novoPedido;
        limpar();
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
