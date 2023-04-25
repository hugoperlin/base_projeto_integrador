package ifpr.pgua.eic.setgo.models.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.setgo.models.daos.ItensPedidoDAO;
import ifpr.pgua.eic.setgo.models.entities.ItensPedido;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.results.Result;

public class ItensPedidoRepository {
    
    private List<ItensPedido> itens;
    private ItensPedidoDAO dao;

    public ItensPedidoRepository(ItensPedidoDAO dao){
        this.dao = dao;
    }

    public Result adicionarItensPedido(ItensPedido itens, int pedidoId){
        return dao.inserir(itens, pedidoId);
    }

    public Result atualizarItens(int id, Produto produto, double quantidade){
        Optional<ItensPedido> busca = itens.stream()
                .filter((p)->p.getId() == id)
                .findFirst();
        
        if(busca.isPresent()){
            ItensPedido itens = busca.get();
            itens.setProduto(produto);;
            itens.setQuantidade(quantidade);

            return Result.success("Pedido atualizado com sucesso!");
        }
        return Result.fail("Pedido não encontrado!");
    }

    public List<ItensPedido> getProdutos(){
        itens = dao.buscarTodos();
        return Collections.unmodifiableList(itens);
    }
}
