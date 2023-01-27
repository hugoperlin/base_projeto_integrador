package ifpr.pgua.eic.setgo.models.daos;

import java.util.List;

import ifpr.pgua.eic.setgo.models.Produto;
import ifpr.pgua.eic.setgo.models.results.Result;

public interface ProdutoDAO {
    List<Produto> buscarTodos();
    Result inserir(Produto Produto);
}
