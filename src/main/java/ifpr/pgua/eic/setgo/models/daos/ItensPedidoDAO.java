package ifpr.pgua.eic.setgo.models.daos;

import java.util.ArrayList;

import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.results.Result;

public interface ItensPedidoDAO {
    ArrayList<ItensPedido> buscarTodos();
    Result inserir(ItensPedido itens);
}
