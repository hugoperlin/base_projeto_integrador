package ifpr.pgua.eic.setgo.models.entities;

public class ItensPedido {
    private int id;
    private Produto produto;
    private double quantidade;       
    private float preco;
    
    public ItensPedido(int id, Produto produto, double quantidade) {       
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = (float) ((float) produto.getPreco()*quantidade);
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Produto: " +produto.getNome()+ " Quantidade: " +quantidade+ " Preço: " +preco;
    }
    
}

