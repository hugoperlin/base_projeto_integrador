package ifpr.pgua.eic.setgo.controllers.ViewModels;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.repositories.ItensPedidoRepository;
import ifpr.pgua.eic.setgo.models.repositories.PedidosRepository;
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
    private ObservableList<Pedido> obsPedidos = FXCollections.observableArrayList();
    private ObservableList<ItensPedido> itens = FXCollections.observableArrayList();
    
    private Pedido pedido;
    
    private ProdutoRepository produtosRepository;
    private ItensPedidoRepository itensRepository;
    private PedidosRepository pedidosRepository;

    public JanelaPedidosViewModel(ProdutoRepository produtosRepository, 
            ItensPedidoRepository itensRepository, PedidosRepository pedidosRepository){
        this.produtosRepository = produtosRepository;
        this.itensRepository = itensRepository;
        this.pedidosRepository = pedidosRepository;

        dataHora = LocalDateTime.now();
        dataProperty.set(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(dataHora));
        updateList();
    }

    private void updateList() {
        obsPedidos.clear();
        for (Pedido p : pedidosRepository.getPedidos()) {
            obsPedidos.add(p);
        }
    }

    public ObservableList<ItensPedido> getItens(){
        return itens;
    }
    
    public ObservableList<Produto> getProdutos(){
        return produtos;
    }

    public ObservableList<Pedido> getPedidos(){
        return obsPedidos;
    }


    public void carregaLista(){
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
        
        if(pedido.getItens() == null){
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

        Produto produto = produtoProperty.get();
        ItensPedido item = new ItensPedido(produto, quantidade);

        pedido.add(item);

        quantidadeProperty.set("");

        double valor=0;

        valor = pedido.getItens().stream().map(it->it.getQuantidade()*it.getProduto().getPreco()).reduce(0.0,Double::sum);

        valorTotalProperty.setValue("R$"+valor);
        
        return Result.success("Adicionado!");

    }

    public Result registrarPedido(Pedido pedido){
        if(pedido.size() == 0){
            return Result.fail("Não foram inseridos produtos!");
        }
        pedidosRepository.adicionarPedido(pedido);

        return Result.success("Finalizada!");

    }
}
