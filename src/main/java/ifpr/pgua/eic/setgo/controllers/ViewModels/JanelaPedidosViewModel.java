package ifpr.pgua.eic.setgo.controllers.ViewModels;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ifpr.pgua.eic.setgo.models.entities.ItemsPedido;
import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.repositories.ProdutoRepository;
import ifpr.pgua.eic.setgo.models.results.Result;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JanelaPedidosViewModel {
    
    private LocalDateTime dataHora;
    private StringProperty dataProperty = new SimpleStringProperty();
    private StringProperty quantidadeProperty = new SimpleStringProperty();
    private StringProperty valorTotalProperty = new SimpleStringProperty();

    private ObjectProperty<Produto> produtoProperty = new SimpleObjectProperty<>();

    private ObservableList<Produto> produtos = FXCollections.observableArrayList();

    private ObservableList<ItensPedido> itensPedido = FXCollections.observableArrayList();
    
    private ProdutoRepository produtosRepository;

    public JanelaPedidosViewModel(ProdutoRepository produtosRepository){
        this.produtosRepository = produtosRepository;

        dataHora = LocalDateTime.now();
        dataProperty.set(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(dataHora));
    }

    public ObservableList<Produto> getProdutos(){
        return produtos;
    }

    public ObservableList<ItensPedido> getItensVenda(){
        return itensPedido;
    }

    public void carregaListas(){
        produtos.clear();
        produtos.addAll(produtosRepository.getProdutos());
    }

    public StringProperty getDataProperty(){
        return dataProperty;
    }

    public ObjectProperty<Produto> getProdutoProperty(){
        return produtoProperty;
    }

    public StringProperty getQuantidadeProperty(){
        return quantidadeProperty;
    }

    public StringProperty getValorTotalProperty(){
        return valorTotalProperty;
    }

    public Result adicionaItem(){
        
        if(produtoProperty.get() == null){
            return Result.fail("Nenhum produto adicionado!");
        }

        double quantidade = 0;
        try{
            quantidade = Double.parseDouble(quantidadeProperty.getValue());
        }catch(NumberFormatException e){
            return Result.fail("Quantidade inválida!");
        }

        if(quantidade == 0){
            return Result.fail("Quantidade deve ser maior que 0!");
        }

        // ItensPedido item = new ItemVenda();
        // Produto produto = produtoProperty.get();
        // item.setProduto(produto);
        // item.setQuantidade(quantidade);
        // item.setValorTotal(produto.getPreco());

        // itensPedido.add(item);

        // quantidadeProperty.set("");

        // double valor=0;

        // valor = itensVenda.stream().map(it->it.getQuantidade()*it.getProduto().getValor()).reduce(0.0,Double::sum);

        // valorTotalProperty.setValue("R$ "+valor);
        
         return Result.success("Adicionado!");

    }

    public Result finalizarVenda(){
        if(itensPedido.size() == 0){
            return Result.fail("Não foram inseridos produtos!");
        }


        // Venda venda = new Venda(cliente, dataHora);
        // venda.setItens(itensVenda);

        return Result.success("Finalizada!");

    }
}