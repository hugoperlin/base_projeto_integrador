package ifpr.pgua.eic.setgo.models.entities;

public class ItemPedido {
    private int id;
    private Produto produto;
    private double quantidade;
    private double valorPedido;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public double getValorPedido() {
        return valorPedido;
    }
    public void setValorPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }
    public double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
    
