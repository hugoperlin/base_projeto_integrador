package ifpr.pgua.eic.setgo.models.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.setgo.models.daos.PedidoDAO;
import ifpr.pgua.eic.setgo.models.daos.ProdutoDAO;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.results.Result;

public class PedidosRepository {
    
    private List<Pedido> pedidos;
    private PedidoDAO dao;

    public PedidosRepository(PedidoDAO dao){
        this.dao = dao;
    }

    public Result adicionarPedido(Pedido pedido){
        return dao.inserir(pedido);
    }

    public List<Pedido> getPedidos(){
        pedidos = dao.buscarTodos();
        return Collections.unmodifiableList(pedidos);
    }
}
