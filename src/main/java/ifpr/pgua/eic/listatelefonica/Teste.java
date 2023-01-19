package ifpr.pgua.eic.listatelefonica;

import java.util.List;

import ifpr.pgua.eic.listatelefonica.models.Contato;
import ifpr.pgua.eic.listatelefonica.models.FabricaConexoes;
import ifpr.pgua.eic.listatelefonica.models.daos.ContatoDAO;
import ifpr.pgua.eic.listatelefonica.models.daos.JDBCContatoDAO;
import ifpr.pgua.eic.listatelefonica.models.results.Result;

public class Teste {
    
    public static void main(String[] args) {
        
        FabricaConexoes fabrica = FabricaConexoes.getInstance();

        ContatoDAO contatoDAO = new JDBCContatoDAO(fabrica);

        List<Contato> lista = contatoDAO.buscarTodos();

        for(Contato c:lista){
            System.out.println(c.getNome());
        }
        


    }


}
