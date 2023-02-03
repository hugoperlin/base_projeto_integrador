package ifpr.pgua.eic.setgo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.setgo.models.Contato;
import ifpr.pgua.eic.setgo.models.ListaTelefonica;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class JanelaLista implements Initializable {

    @FXML
    private ListView<Contato> ltvContatos;

    @FXML
    private TextField tfBusca;

    @FXML
    private TextArea taDetalhes;

    private ListaTelefonica listaTelefonica;

    public JanelaLista(ListaTelefonica listaTelefonica){
        this.listaTelefonica = listaTelefonica;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println(listaTelefonica.getContatos());
        ltvContatos.getItems().addAll(listaTelefonica.getContatos());
    }

    @FXML
    private void mostraDetalhes(MouseEvent evento){
        taDetalhes.clear();
        
        Contato contato = ltvContatos.getSelectionModel().getSelectedItem();

        if(contato != null){
            taDetalhes.appendText("Nome: "+contato.getNome()+"\n");
            taDetalhes.appendText("Telefone: "+contato.getNome()+"\n");
            taDetalhes.appendText("E-mail: "+contato.getEmail()+"\n");
            
        }
    }

    @FXML
    private void buscar(){

        String inicio = tfBusca.getText();

        ltvContatos.getItems().clear();
        ltvContatos.getItems().addAll(listaTelefonica.buscaPorNome(inicio));
    }

    
}
