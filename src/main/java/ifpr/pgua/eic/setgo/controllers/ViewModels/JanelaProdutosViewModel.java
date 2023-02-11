package ifpr.pgua.eic.setgo.controllers.ViewModels;

import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.repositories.ProdutoRepository;
import ifpr.pgua.eic.setgo.models.results.Result;
import ifpr.pgua.eic.setgo.models.results.SuccessResult;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JanelaProdutosViewModel {
    private StringProperty idProduto = new SimpleStringProperty();    
    private StringProperty nomeProduto = new SimpleStringProperty();    
    private StringProperty descricao = new SimpleStringProperty();    
    private StringProperty precoProduto = new SimpleStringProperty("0.0");    
    private StringProperty quantProduto = new SimpleStringProperty("0.0");    
    
    private StringProperty operacao = new SimpleStringProperty("Cadastrar");
    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private boolean atualizar = false;

    /* Lista que será utilizada para povar a TableView */
    private ObservableList<ProdutoRow> obsProdutos = FXCollections.observableArrayList();

    /* Objeto que serve para indicar qual linha da tabela está selecionada. */
    private ObjectProperty<ProdutoRow> selecionado = new SimpleObjectProperty<>();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private ProdutoRepository repository;

    public JanelaProdutosViewModel(ProdutoRepository repository) {
        this.repository = repository;
        updateList();
    }

    /*
     * Atualiza a lista observável de clientes, que por consequência irá
     * atualizar o conteúdo mostrado pela TableView.
     */
    private void updateList() {
        obsProdutos.clear();
        for (Produto p : repository.getProdutos()) {
            obsProdutos.add(new ProdutoRow(p));
        }
    }

    public ObservableList<ProdutoRow> getProdutos() {
        return obsProdutos;
    }

    public ObjectProperty<Result> alertProperty() {
        return alertProperty;
    }

    /* Métodos para acesso as propriedades. */

    public StringProperty operacaoProperty() {
        return operacao;
    }

    public BooleanProperty podeEditarProperty() {
        return podeEditar;
    }

    public StringProperty nomeProperty() {
        return this.nomeProduto;
    }

    public StringProperty descriProperty() {
        return this.descricao;
    }

    public StringProperty precoProperty() {
        return this.precoProduto;
    }

    public StringProperty idProperty() {
        return this.idProduto;
    }
    
    public StringProperty quantProperty(){
        return this.quantProduto;
    }

    public ObjectProperty<ProdutoRow> selecionadoProperty() {
        return selecionado;
    }

    public Result cadastrar() {
        // acessa os valores das propriedades, que por consequência
        // contém os valores digitados nos textfields.
        String nome = nomeProduto.getValue();
        String descricao = this.descricao.getValue();
        float preco = Float.parseFloat(precoProduto.getValue());
        double quant = Double.parseDouble(quantProduto.getValue());
        Result resultado = null;

        if (atualizar) {
            //repository.atualizarProduto(nome, descricao, preco);
            System.out.println("testeFail");
        } else {
            resultado = repository.adicionarProduto(nome, descricao, preco, quant);
        }

        if(resultado instanceof SuccessResult){
            updateList();
            limpar();
        }
        return resultado;
    }

    public void atualizar() {
        operacao.setValue("Atualizar");
        podeEditar.setValue(false);
        atualizar = true;
        Produto produto = selecionado.get().getProduto();
        nomeProduto.setValue(produto.getNome());
        descricao.setValue(produto.getDescricao());
        precoProduto.setValue(String.valueOf(produto.getPreco()));

        alertProperty.setValue(Result.fail("Teste"));

    }

    public void limpar() {
        idProduto.setValue("");
        nomeProduto.setValue("");
        descricao.setValue("");
        precoProduto.setValue("");
        podeEditar.setValue(true);
        atualizar = false;
        operacao.setValue("Cadastrar");
    }
}
