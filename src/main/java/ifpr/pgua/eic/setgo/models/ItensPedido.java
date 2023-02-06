package ifpr.pgua.eic.setgo.models;

public class ItensPedido {
    private Produto produto;
    private int quantidade;       
    private float preco;
    
    public ItensPedido(Produto produto, int quantidade) {       
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = produto.getPreco()*quantidade;
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
        return "Produto: " +produto.getDescricao()+ "Quantidade" +quantidade+ "Preço: " +preco;
    }
    
}

