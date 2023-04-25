package ifpr.pgua.eic.setgo.models.daos;

import java.util.List;

import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.results.Result;

public interface PedidoDAO {
    List<Pedido> buscarTodos();
    Result inserir(Pedido pedido);
}
