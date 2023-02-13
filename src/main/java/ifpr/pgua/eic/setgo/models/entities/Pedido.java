package ifpr.pgua.eic.setgo.models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private LocalDate data;      
    private float valorTotal;
    private ArrayList<ItensPedido> itens;

    public Pedido(int idPedido, LocalDate data, float valorTotal){
        this.idPedido = idPedido;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public Pedido(LocalDate data){
        this.data = data;
        this.itens = new ArrayList<ItensPedido>();
        this.valorTotal = (float) this.itens.stream().mapToDouble(n -> n.getPreco()).sum();
    }

    public void add(ItensPedido itemPedido){
        this.itens.add(itemPedido);
    } 

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getValorTotal() {    
        float total = 0;
        for(ItensPedido itensPedido : itens){
            total = (float) (total + (itensPedido.getProduto().getPreco()*itensPedido.getQuantidade()));
        }
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItensPedido> itens) {
        this.itens = itens;
    }

    public int size() {
        return itens.size();
    }

    /*public boolean adicionarPedido(int idPedido, Date data, float valorTotal) {
        if(itensPedido.setIdPedido(idPedido) == null){
            Pedido pedido = new Pedido(idPedido, data, valorTotal);
            pedidos.add(pedido);
            return true;
        }
        return false;
    }*/
    

}

