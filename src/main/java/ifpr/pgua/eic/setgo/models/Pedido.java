package ifpr.pgua.eic.setgo.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private LocalDate data;      
    private float valorTotal;
    private List<ItensPedido> itens;

    public Pedido(int idPedido, LocalDate data, float valorTotal){
        this.idPedido = idPedido;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public Pedido (ItensPedido itensPedido){
        this.itens.add(itensPedido);
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
            total = total + (itensPedido.getProduto().getPreco()*itensPedido.getQuantidade());
        }
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
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

