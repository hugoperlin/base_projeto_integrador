package ifpr.pgua.eic.setgo.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.setgo.models.FabricaConexoes;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.results.Result;

public class JDBCPedidoDAO implements PedidoDAO {
    private FabricaConexoes fabricaConexoes;
    
    public JDBCPedidoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }
    
    @Override
    public List<Pedido> buscarTodos(){
        try {
           Connection con = fabricaConexoes.getConnection();
           
           PreparedStatement prep = con.prepareStatement("SELECT * FROM pedidos");
           
           ResultSet result = prep.executeQuery();
           
           ArrayList<Pedido> pedidos = new ArrayList<>();
           
           while(result.next()){
               //nomes das colunas na tabela do bd
               Integer id = result.getInt("id");
               String nome = result.getString("nome");
               String descricao = result.getString("descricao");
               float preco = result.getFloat("valor");
               double quant = result.getDouble("quantidade");
               
               Pedido pedido = new Pedido(LocalDate.now());
               
               pedidos.add(pedido);
           }
           
           result.close();
           prep.close();
           con.close();
           
           return pedidos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
        
    }

    @Override
    public Result inserir(Pedido pedido){
        try {
            Connection con = fabricaConexoes.getConnection();
            
            PreparedStatement prep = con
                    .prepareStatement("INSERT INTO pedidos (nome,descricao,valor,quantidade) VALUES (?,?,?,?)");
            
            prep.setNString(1, pedido.getData());
            prep.setString(2, pedido.getDescricao());
            prep.setFloat(3, pedido.getPreco());
            prep.setDouble(4, pedido.getQuantidade());
            
            prep.executeUpdate();
            
            prep.close();
            con.close();
            
            return Result.success("Tudo certo!!!! TOme!!");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
        
}
    
