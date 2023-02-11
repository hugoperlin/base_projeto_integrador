package ifpr.pgua.eic.setgo.models.entities;

public class ItensPedido {
    private Produto produto;
    private double quantidade;       
    private float preco;
    
    public ItensPedido(Produto produto, double quantidade) {       
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = (float) ((float) produto.getPreco()*quantidade);
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
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

