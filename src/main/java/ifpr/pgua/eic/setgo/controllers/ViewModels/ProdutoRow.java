package ifpr.pgua.eic.setgo.controllers.ViewModels;

import ifpr.pgua.eic.setgo.models.entities.Produto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProdutoRow {
    private Produto produto;
            
    public ProdutoRow(Produto produto){
        this.produto = produto;
    }

    public Produto getProduto(){
        return produto;
    }

    /**
    * Propriedade para representar o atributo id do produto.
    * 
    * @return SimpleStringProperty com o valor do id do produto.
    */
    public IntegerProperty idProperty(){
        return new SimpleIntegerProperty(produto.getId());
    }

    /**
    * Propriedade para representar o atributo nome do produto.
    * 
    * @return SimpleStringProperty com o valor do nome do produto.
    */

    public StringProperty nomeProperty(){
        return new SimpleStringProperty(produto.getNome());
    }

    /**
    * Propriedade para representar o atributo telefone do produto.
    * 
    * @return SimpleStringProperty com o valor do telefone do produto.
    */
    public StringProperty descricaoProperty(){
        return new SimpleStringProperty(produto.getDescricao());
    }

    /**
    * Propriedade para representar o atributo email do produto.
    * 
    * @return SimpleStringProperty com o valor do email do produto.
    */
    public SimpleFloatProperty precoProperty(){
        return new SimpleFloatProperty(produto.getPreco());
    }

    public SimpleDoubleProperty quantidadeProperty(){
        return new SimpleDoubleProperty(produto.getPreco());
    }
}
