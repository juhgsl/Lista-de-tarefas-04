package Parte01;

import java.util.Scanner;


public class CadastroAluno {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Altura (m): ");
        double altura = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));

        System.out.print("Matrícula ativa? (s/n): ");
        boolean matriculado = sc.nextLine().trim().equalsIgnoreCase("s");

        System.out.print("Nota final: ");
        double notaFinal = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));

        System.out.print("Curso: ");
        String curso = sc.nextLine();

        System.out.println("\n===== DADOS CADASTRADOS =====");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.printf("Altura: %.2f m%n", altura);
        System.out.println("Matrícula ativa: " + (matriculado ? "Sim" : "Não"));
        System.out.printf("Nota final: %.1f%n", notaFinal);
        System.out.println("Curso: " + curso);
        System.out.println("Situação: " + (notaFinal >= 6 ? "APROVADO" : "REPROVADO"));
        sc.close();
    }
}