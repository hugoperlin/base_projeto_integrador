package ifpr.pgua.eic.setgo.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.setgo.models.Contato;
import ifpr.pgua.eic.setgo.models.FabricaConexoes;
import ifpr.pgua.eic.setgo.models.results.Result;

public class JDBCContatoDAO implements ContatoDAO{
    private FabricaConexoes fabricaConexoes;
    
    public JDBCContatoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public List<Contato> buscarTodos() {
         try {
            Connection con = fabricaConexoes.getConnection();
            
            PreparedStatement prep = con.prepareStatement("SELECT * FROM contatos");
            
            ResultSet result = prep.executeQuery();
            
            ArrayList<Contato> contatos = new ArrayList<>();
            
            while(result.next()){
                Integer id = result.getInt("id");
                String nome = result.getString("nome");
                String email = result.getString("email");
                String telefone = result.getString("telefone");
                
                Contato contato = new Contato(id, nome, email, telefone);
                
                contatos.add(contato);
            }
            
            result.close();
            prep.close();
            con.close();
            
            return contatos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Result inserir(Contato contato) {
        try {
            Connection con = fabricaConexoes.getConnection();
            
            PreparedStatement prep = con
                    .prepareStatement("INSERT INTO contatos (nome,email,telefone) VALUES (?,?,?)");
            
            prep.setString(1, contato.getNome());
            prep.setString(2, contato.getEmail());
            prep.setString(3, contato.getTelefone());
            
            prep.executeUpdate();
            
            prep.close();
            con.close();
            
            return Result.success("Tudo certo!!!! TOme!!");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
    
}
