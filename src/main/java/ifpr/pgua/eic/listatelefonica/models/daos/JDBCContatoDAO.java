package ifpr.pgua.eic.listatelefonica.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.listatelefonica.models.Contato;
import ifpr.pgua.eic.listatelefonica.models.FabricaConexoes;
import ifpr.pgua.eic.listatelefonica.models.results.Result;

public class JDBCContatoDAO implements ContatoDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCContatoDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result inserir(Contato contato) {
        
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("INSERT INTO contatos(nome,email,telefone) VALUES (?,?,?)");

            pstm.setString(1, contato.getNome());
            pstm.setString(2, contato.getEmail());
            pstm.setString(3, contato.getTelefone());

            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Contato cadastrado!");
            

        }catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public List<Contato> buscarTodos() {
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM contatos");

            ResultSet resultSet = pstm.executeQuery();

            ArrayList<Contato> contatos = new ArrayList<>();

            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");

                Contato contato = new Contato(id, nome, email, telefone);

                contatos.add(contato);
            }
            
            resultSet.close();
            pstm.close();
            con.close();
            return contatos;

        }catch(SQLException e ){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    
}
