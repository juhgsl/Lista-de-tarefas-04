package Parte03;

import java.util.Scanner;

public class Calculadora {

    static double calcular(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("divisão por zero");
                return a / b;
            default:
                throw new IllegalArgumentException("operação inválida: " + op);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String continuar;
        do {
            try {
                System.out.print("Primeiro número: ");
                double a = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                System.out.print("Operação (+, -, *, /): ");
                String op = sc.nextLine().trim();
                System.out.print("Segundo número: ");
                double b = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));

                System.out.println("Resultado: " + calcular(a, b, op));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números (ex.: 10 ou 3,5).");
            } catch (ArithmeticException e) {
                System.out.println("Não é possível realizar a operação: " + e.getMessage() + ".");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Use +, -, * ou /.");
            } finally {
                System.out.println("--- Fim do cálculo ---");
            }
            System.out.print("Deseja calcular novamente? (s/n): ");
            continuar = sc.hasNextLine() ? sc.nextLine().trim() : "n";
        } while (continuar.equalsIgnoreCase("s"));
        System.out.println("Calculadora encerrada. Até logo!");
        sc.close();
    }
}
