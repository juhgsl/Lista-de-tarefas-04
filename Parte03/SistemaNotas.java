package Parte03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Tarefa 07 - Sistema de notas acadêmicas. */
public class SistemaNotas {

    static void validar(double nota) throws NotaInvalidaException {
        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException("Nota " + nota + " fora do intervalo (0 a 10).");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantas notas deseja informar? ");
        int qtd;
        try {
            qtd = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida.");
            return;
        }

        List<Double> notas = new ArrayList<>();
        while (notas.size() < qtd) {
            System.out.print("Nota " + (notas.size() + 1) + ": ");
            try {
                double nota = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                validar(nota);
                notas.add(nota);
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido! Digite um número.");
            } catch (NotaInvalidaException e) {
                System.out.println("  " + e.getMessage() + " Tente novamente.");
            }
        }

        double soma = 0;
        for (double n : notas) soma += n;
        double media = notas.isEmpty() ? 0 : soma / notas.size();

        System.out.printf("Média: %.2f%n", media);
        System.out.println(media >= 6 ? "Situação: APROVADO" : "Situação: REPROVADO");
        sc.close();
    }
}
