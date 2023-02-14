package ifpr.pgua.eic.setgo.models.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Venda {
    
    private int id;
    private Comprador comprador;
    private List<ItensPedido> itensPedidos;
    private double total;
    
    
    public Venda(int id, Comprador comprador, List<ItensPedido>itensPedidos, LocalDateTime dataHora, double total){
        this.id = id;
        this.comprador = comprador;
        this.itensPedidos = itensPedidos;
        this.total = total;
        
    }

    public Venda(Comprador comprador, LocalDateTime dataHora){
        this.itensPedidos = new ArrayList<>();
        this.comprador = comprador;
    }

    public void adicionarProduto(Produto p, double quantidade){

        //busca um item na lista de itens do pedido que contenha o produto
        Optional<ItensPedido> item = itensPedidos.stream().filter((it)->it.getProduto().getId()==p.getId()).findFirst();

        
        if(item.isPresent()){
            //se achou, acrescenta a quantidade
            ItensPedido it = item.get();
            it.setQuantidade(it.getQuantidade()+quantidade);
        }else{
            //se não, cria um novo item de pedidos e adiciona na lista de itens
            ItensPedido it = new ItensPedido(id, p, quantidade);
            it.setProduto(p);
            it.setQuantidade(quantidade);
            itensPedidos.add(it);
        }
    }

    public boolean removerProduto(Produto p, double quantidade){
        
        //busca um item na lista de itens do pedidos que contenha o produto
        Optional<ItensPedido> item = itensPedidos.stream().filter((it)->it.getProduto().getId()==p.getId()).findFirst();

        if(item.isPresent()){
            ItensPedido it = item.get();
            it.setQuantidade(it.getQuantidade() - quantidade);
            if(it.getQuantidade() <=0 ){
                itensPedidos.remove(it);
            }
            return true;
        }

        return false;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString(){
        return "Comprador: " +comprador.getNome()+ " Itens: " +itensPedidos+ " Total " +total;
    }
}
