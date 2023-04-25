package ifpr.pgua.eic.setgo.models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Estoque {
    
    private ArrayList<Produto> produtos;
    private ArrayList<Pedido> pedidos;

    public Estoque(){
        produtos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public boolean adicionarProduto(String nome, String descricao,
            float preco, double quant){
        if(buscar(nome) == null){
            Produto produto = new Produto(nome, descricao, preco, quant);
            produtos.add(produto);
            return true;
        }
        return false;
    }

    public boolean adicionarPedido(Pedido pedido){
        pedidos.add(pedido);
        return true;
    }

    public Produto buscar(String nome){
        return produtos.stream().filter((produto)->produto.getNome().equals(nome)).findFirst().orElse(null);
    }

    public Pedido buscarPedido(int id){
        return pedidos.stream().filter((pedido)->pedido.getIdPedido() == id).findFirst().orElse(null);
    }

    /**
     * Acesso a lista de produtos;
     *  
     * @return Lista de produtos
     */
    public List<Produto> getProdutos(){
        return produtos;
    }

    public List<Pedido> getPedidos(){
        return pedidos;
    }

    /**
     * Filtra a lista, buscando produtos que iniciam pela string
     * passada por parâmetro.
     * 
     * @param inicio String com o inicio do nome a ser buscado
     * @return Lista com contatos que possuem o inicio passado por parâmetro.
     */
    public List<Produto> buscaPorNome(String inicio){
        return produtos.stream().filter((produto)->produto.getNome().startsWith(inicio)).collect(Collectors.toList());
    }

}
