package ifpr.pgua.eic.setgo.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Date data;       
    private float valor;
    private List<ItensPedido> ItensPedidoList;

    public Pedido(int idPedido, Date data, float valor){
        this.idPedido = idPedido;
        this.data = data;
        this.valor = valor;
        ItensPedidoList = new ArrayList<>();
    }

    public void inserirPedido(){
        
    }

    public void consultarPedido(){

    }
}

