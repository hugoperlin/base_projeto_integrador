package ifpr.pgua.eic.setgo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Estoque {
    
    private ArrayList<Produto> produtos;

    public Estoque(){
        produtos = new ArrayList<>();
    }

    /**
     * Adiciona um novo contato na lista, caso não existe nenhum contato prévio com o
     * mesmo e-mail.
     *      
     *  
     * @param nome Nome do contato
     * @param telefone Telefone do contato
     * @param email Email do contato
     * @return true se conseguiu adicionar, false caso contrário
     */
    public boolean adicionarProduto(String nome, String descricao, float preco){
        
        
        if(buscar(nome) == null){
            Produto produto = new Produto(nome, descricao, preco);
            produtos.add(produto);
            return true;
        }
        return false;
    }

    public Produto buscar(String nome){
        return produtos.stream().filter((produto)->produto.getNome().equals(nome)).findFirst().orElse(null);
    }

    /**
     * Acesso a lista de produtos;
     *  
     * @return Lista de produtos
     */
    public List<Produto> getProdutos(){
        return produtos;
    }

    /**
     * Filtra a lista, buscando produtos que iniciam pela string
     * passada por parâmetro.
     * 
     * @param inicio String com o inicio do nome a ser buscado
     * @return Lista com contatos que possuem o inicio passado por parâmetro.
     */
    public List<Produto> buscaPorNome(String inicio){
        return produtos.stream().filter((produto)->produto.getNome().startsWith(inicio)).collect(Collectors.toList());
    }

}
