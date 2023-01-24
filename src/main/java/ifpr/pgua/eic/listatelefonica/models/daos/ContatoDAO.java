package ifpr.pgua.eic.listatelefonica.models.daos;

import java.util.List;

import ifpr.pgua.eic.listatelefonica.models.Contato;
import ifpr.pgua.eic.listatelefonica.models.results.Result;

public interface ContatoDAO {
    List<Contato> buscarTodos();
    Result inserir(Contato contato);
}
