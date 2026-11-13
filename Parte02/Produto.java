package Parte02;

public class Produto {
    private String nome;
    private CategoriaProduto categoria;

    public Produto(String nome, CategoriaProduto categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nome + " (categoria: " + categoria + ")";
    }
}
