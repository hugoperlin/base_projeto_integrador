package ifpr.pgua.eic.setgo.models.entities;

public class Produto {
    private int idProduto;    
    private String nomeProduto;    
    private String descricao;    
    private float precoProduto;    
    private double quantidade;
    
    public Produto(int id, String nome, String descricao, float preco, double quant){
        this.idProduto = id;
        this.nomeProduto = nome;
        this.descricao = descricao;
        this.precoProduto = preco;
        this.quantidade = quant;
    }

    public Produto(int id, String nome, float preco, double quant){
        this.idProduto = id;
        this.nomeProduto = nome;
        this.precoProduto = preco;
        this.quantidade = quant;
    }
    
    public Produto(String nome, String descricao, float preco, double quant){
        this.nomeProduto = nome;
        this.descricao = descricao;
        this.precoProduto = preco;
        this.quantidade = quant;
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
    
    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

}
