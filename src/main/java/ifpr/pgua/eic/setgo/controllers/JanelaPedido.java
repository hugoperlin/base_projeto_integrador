package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.models.entities.Estoque;
import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaPedido implements Initializable {
    
    @FXML
    private TextField tfIdPedido;

    @FXML
    private DatePicker tfData;

    @FXML
    private TextField tfValorTotal;

    @FXML
    private ListView<Pedido> ltvPedidos;

    private Estoque estoque;

    public JanelaPedido(Estoque estoque){
        this.estoque= estoque;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ltvPedidos.getItems().addAll(estoque.getPedidos());
    }

    @FXML
    private void registrarPedido(ActionEvent evento){
        int idPedido = Integer.parseInt(tfIdPedido.getText());
        LocalDate data = tfData.getValue();
        float precoParse = Float.parseFloat(tfValorTotal.getText());
    

        if(estoque.adicionarPedido(idPedido, data, precoParse)){
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
        tfData.setValue(null);;
        tfValorTotal.clear();
    }
}
