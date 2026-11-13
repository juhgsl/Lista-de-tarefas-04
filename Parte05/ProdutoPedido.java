package Parte05;

public class ProdutoPedido {
    private final int codigo;
    private final String descricao;
    private final double preco;
    private int estoque;

    public ProdutoPedido(int codigo, String descricao, double preco, int estoque) throws DadosInvalidosException {
        if (descricao == null || descricao.trim().isEmpty())
            throw new DadosInvalidosException("A descrição do produto não pode ser vazia.");
        if (preco <= 0)
            throw new DadosInvalidosException("O preço deve ser maior que zero.");
        if (estoque < 0)
            throw new DadosInvalidosException("O estoque não pode ser negativo.");
        this.codigo = codigo;
        this.descricao = descricao.trim();
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    public void baixarEstoque(int qtd) throws DadosInvalidosException {
        if (qtd > estoque)
            throw new DadosInvalidosException("Estoque insuficiente de '" + descricao + "' (disponível: " + estoque + ").");
        estoque -= qtd;
    }

    @Override
    public String toString() { return descricao + " - R$ " + String.format("%.2f", preco); }
}
