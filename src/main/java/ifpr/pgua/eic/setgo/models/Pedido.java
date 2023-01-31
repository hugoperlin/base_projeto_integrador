package ifpr.pgua.eic.setgo.models;

import java.sql.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Date data;      
    private float valorTotal;
    private List<ItensPedido> itens;

    public Pedido(int idPedido, Date data, float valorTotal){
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValorTotal() {    
    //calcular valor total (soma de cada item pedido)
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
    

}

