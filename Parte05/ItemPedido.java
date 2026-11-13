package Parte05;

public class ItemPedido {
    private final ProdutoPedido produto;
    private final int quantidade;

    public ItemPedido(ProdutoPedido produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoPedido getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getSubtotal() { return produto.getPreco() * quantidade; }

    @Override
    public String toString() { return quantidade + "x " + produto.getDescricao(); }
}
