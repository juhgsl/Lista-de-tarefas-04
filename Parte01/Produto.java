package Parte01;

public class Produto {
    private int codigo;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;
    private String categoria;

    public Produto(int codigo, String descricao, double preco, int quantidadeEstoque, String categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public double calcularValorTotalEstoque() {
        return preco * quantidadeEstoque;
    }

    public void exibirDados() {
        System.out.println("Código: " + codigo);
        System.out.println("Descrição: " + descricao);
        System.out.printf("Preço: R$ %.2f%n", preco);
        System.out.println("Quantidade em estoque: " + quantidadeEstoque);
        System.out.println("Categoria: " + categoria);
        System.out.printf("Valor total em estoque: R$ %.2f%n", calcularValorTotalEstoque());
        System.out.println("-----------------------------");
    }

    public int getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public String getCategoria() { return categoria; }
}
