package ifpr.pgua.eic.setgo.models;

public class ItensPedido {
    private int idItensPedido;
    private int quantidade;       
    private float preco;
    //private status;
    private Produto produto;
    
    public ItensPedido(int idItensPedido, int quantidade, float preco, Produto produto) {
        this.idItensPedido = idItensPedido;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
    }

    public int getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(int idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString(){
        return "Produto: " +produto.getDescricao()+ "Valor: " +preco+ "Quantidade" +quantidade;
    }
    

    
}

