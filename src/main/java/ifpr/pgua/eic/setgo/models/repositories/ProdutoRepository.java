package ifpr.pgua.eic.setgo.models.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.setgo.models.Produto;
import ifpr.pgua.eic.setgo.models.daos.ProdutoDAO;
import ifpr.pgua.eic.setgo.models.results.Result;

public class ProdutoRepository {
    
    private List<Produto> produtos;
    private ProdutoDAO dao;

    public ProdutoRepository(ProdutoDAO dao){
        this.dao = dao;
    }

    public Result adicionarProduto(String nome, String descricao, float preco){

        Optional<Produto> busca = produtos.stream()
                .filter((p)->p.getNome().equals(nome)).findFirst();
        
        if(busca.isPresent()){
            return Result.fail("Produto já cadastrado!");
        }

        Produto produto = new Produto(nome,descricao, preco);
        
        return dao.inserir(produto);
            
    }

    public Result atualizarCliente(int id, String nome, String descricaoNova, 
            float precoNovo){
        Optional<Produto> busca = produtos.stream()
                .filter((p)->p.getId() == id)
                .findFirst();
        
        if(busca.isPresent()){
            Produto produto = busca.get();
            produto.setDescricao(descricaoNova);
            produto.setPreco(precoNovo);

            return Result.success("Cliente atualizado com sucesso!");
        }
        return Result.fail("Cliente não encontrado!");
    }

    public List<Produto> getProdutos(){
        produtos = dao.buscarTodos();
        return Collections.unmodifiableList(produtos);
    }


}
