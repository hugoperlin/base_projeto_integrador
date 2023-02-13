package ifpr.pgua.eic.setgo.models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.setgo.models.FabricaConexoes;
import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.results.Result;

public class JDBCItensPedidoDAO implements ItensPedidoDAO {
    private FabricaConexoes fabricaConexoes;
    
    public JDBCItensPedidoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }
    
    @Override
    public ArrayList<ItensPedido> buscarTodos(){
        try{
            JDBCProdutoDAO repoProduto = new JDBCProdutoDAO(fabricaConexoes);
            Connection con = fabricaConexoes.getConnection();
        
            PreparedStatement prep = con.prepareStatement("SELECT * FROM pedidos");
        
            ResultSet result = prep.executeQuery();
        
            ArrayList<ItensPedido> pedidos = new ArrayList<>();
        
            while(result.next()){
                //nomes das colunas na tabela do bd
                Integer id = result.getInt("id");
                Integer produtoId = result.getInt("idProduto");
                Produto produto = repoProduto.findById(produtoId);
                double quant = result.getDouble("quantidade");
                
                ItensPedido itens = new ItensPedido(id, produto, quant);
                
                pedidos.add(itens);
            }
            
            result.close();
            prep.close();
            con.close();
            
            return pedidos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    @Override
    public Result inserir(ItensPedido itens){
        try {
            Connection con = fabricaConexoes.getConnection();
            
            PreparedStatement prep = con
                    .prepareStatement("INSERT INTO itensPedido (idProduto,valor,quantidade,idPedido) VALUES (?,?,?,?)");
            
            prep.setInt(1, itens.getProduto().getId());
            prep.setFloat(2, itens.getPreco());
            prep.setDouble(3, itens.getQuantidade());
            
            prep.executeUpdate();
            
            prep.close();
            con.close();
            
            return Result.success("Tudo certo!!!! TOme!!");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
        
}
    
