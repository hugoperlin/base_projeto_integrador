package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.models.ItensPedido;
import ifpr.pgua.eic.setgo.models.Pedido;
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
    private ListView<ItensPedido> ltvItens;

    private Pedido pedido;

    public JanelaPedido(Pedido pedido){
        this.pedido= pedido;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

    @FXML
    private void realizarPedido(ActionEvent evento){
        LocalDate data = tfData.getValue();
        String valorTotal = tfValorTotal.getText();
        float precoParse = Float.parseFloat(valorTotal);
    

        /*if(Pedido.adicionarPedido()){
            Alert alert = new Alert(AlertType.INFORMATION,"Pedido realizado");
                alert.showAndWait();
                limpar();
        }else{
                Alert alert = new Alert(AlertType.INFORMATION,"Pedido Não Realizado");
                alert.showAndWait();  
        }*/
    }

    private void limpar(){
        tfData.setValue((LocalDate.now()));
        tfValorTotal.clear();
    }
}
