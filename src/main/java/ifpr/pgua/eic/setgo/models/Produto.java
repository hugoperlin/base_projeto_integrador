package ifpr.pgua.eic.setgo.models;

public class Produto {
    private int idProduto;    
    private String nomeProduto;    
    private String descricao;    
    private float precoProduto;    
    
    public Produto(int id, String nome, String descricao, float preco){
        this.idProduto = id;
        this.nomeProduto = nome;
        this.descricao = descricao;
        this.precoProduto = preco;
    }

    public Produto(int id, String nome, float preco){
        this.idProduto = id;
        this.nomeProduto = nome;
        this.precoProduto = preco;
    }
    
    public String getNome(){
        return this.nomeProduto;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public float getPreco(){
        return this.precoProduto;
    }
    
    public void inserirProduto(){
        
    }

    public void alterarProduto(){
        
    }

    public void removerProduto(){
        
    }

    public void consultarProduto(){
        
    }
}
