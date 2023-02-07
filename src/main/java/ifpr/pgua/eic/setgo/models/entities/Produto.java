package ifpr.pgua.eic.setgo.models.entities;

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
    
    public Produto(String nome, String descricao, float preco){
        this.nomeProduto = nome;
        this.descricao = descricao;
        this.precoProduto = preco;
    }

    public int getId(){
        return this.idProduto;
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
    
    public void setId(int id){
        this.idProduto = id;
    }
    
    public void setNome(String nome){
        this.nomeProduto = nome;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setPreco(float preco){
        this.precoProduto = preco;
    }
    
}
