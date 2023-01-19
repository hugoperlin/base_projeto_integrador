package ifpr.pgua.eic.listatelefonica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.listatelefonica.models.Contato;
import ifpr.pgua.eic.listatelefonica.models.repositories.ContatoRepository;
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

    private ContatoRepository repositorio;

    public JanelaLista(ContatoRepository repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ltvContatos.getItems().addAll(repositorio.listar());
        
    }



    @FXML
    private void mostraDetalhes(MouseEvent evento){
        taDetalhes.clear();
        
        Contato contato = ltvContatos.getSelectionModel().getSelectedItem();

        if(contato != null){
            taDetalhes.appendText("Nome: "+contato.getNome()+"\n");
            taDetalhes.appendText("Telefone: "+contato.getTelefone()+"\n");
            taDetalhes.appendText("E-mail: "+contato.getEmail()+"\n");
            
        }
    }

    @FXML
    private void buscar(){

        String inicio = tfBusca.getText();

        ltvContatos.getItems().clear();
        ltvContatos.getItems().addAll(repositorio.filtrarPorNome(inicio));
    }

    
}
