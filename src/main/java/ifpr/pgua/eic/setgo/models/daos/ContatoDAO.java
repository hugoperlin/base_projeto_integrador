package ifpr.pgua.eic.setgo.models.daos;

import java.util.List;

import ifpr.pgua.eic.setgo.models.Contato;
import ifpr.pgua.eic.setgo.models.results.Result;

public interface ContatoDAO {
    List<Contato> buscarTodos();
    Result inserir(Contato contato);
}
