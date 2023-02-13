package ifpr.pgua.eic.setgo.models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.print.attribute.IntegerSyntax;

import ifpr.pgua.eic.setgo.models.FabricaConexoes;
import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
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
            JDBCItensPedidoDAO itens = new JDBCItensPedidoDAO(fabricaConexoes);
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement prep = con.prepareStatement("SELECT * FROM pedidos");

            ResultSet result = prep.executeQuery();

            ArrayList<Pedido> pedidos = new ArrayList<>();

            while (result.next()) {
                // nomes das colunas na tabela do bd
                Integer id = result.getInt("id");
                LocalDate data = result.getDate("data").toLocalDate();
                float valor = result.getFloat("valorTotal");

                Pedido pedido = new Pedido(id, data, valor);

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
            
            PreparedStatement prep = con.prepareStatement(
                    "INSERT INTO pedidos (data, valorTotal) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            prep.setDate(1, Date.valueOf(pedido.getData()));
            prep.setDouble(2, pedido.getValorTotal());
            
            prep.executeUpdate();
            ResultSet keys = prep.getGeneratedKeys();
            keys.next();
            int auto_id = keys.getInt(1);
            
            prep.close();
            con.close();

            pedido.getItens().forEach(gt -> inserirItem(auto_id, gt));
            
            return Result.success("Tudo certo!!!! TOme!!");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
        
    public Result inserirItem(int pedidoId, ItensPedido itens){
        try {
            Connection con = fabricaConexoes.getConnection();
            
            PreparedStatement prep = con.prepareStatement(
                    "INSERT INTO itensPedido (idProduto,quantidade,valor,idPedido) VALUES (?,?,?,?)");
            
            prep.setInt(1, itens.getProduto().getId());
            prep.setDouble(2, itens.getQuantidade());
            prep.setFloat(3, itens.getPreco());
            prep.setInt(4, pedidoId);
            
            prep.executeUpdate();
            
            prep.close();
            con.close();
            
            return Result.success("Itens Pedido Atualizado");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        
    }
    public Result atualizarItem(int PedidoId, int itensId){
        try {
            Connection con = fabricaConexoes.getConnection();
            
            PreparedStatement prep = con.prepareStatement(
                    "UPDATE itensPedido SET idPedido = ? WHERE id = ?");
            
            prep.setInt(1, PedidoId);
            prep.setInt(2, itensId);
            
            prep.executeUpdate();
            
            prep.close();
            con.close();
            
            return Result.success("Itens Pedido Atualizado");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        
    }
}
    
