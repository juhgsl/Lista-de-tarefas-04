package Parte01;

public class Principal {
    public static void main(String[] args) {
        Produto p1 = new Produto(1, "Notebook", 3500.00, 10, "Informática");
        Produto p2 = new Produto(2, "Arroz 5kg", 28.90, 120, "Alimentos");
        p1.exibirDados();
        p2.exibirDados();
    }
}
