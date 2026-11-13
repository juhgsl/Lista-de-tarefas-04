package Parte03;

import java.util.Scanner;

public class PrincipalConta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine().trim());
            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            Usuario u = new Usuario(nome, idade, cpf);
            System.out.println("Cadastro realizado com sucesso!");
            System.out.println(u);
        } catch (NomeInvalidoException | IdadeInvalidaException | CpfInvalidoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: a idade deve ser um número inteiro.");
        }
        sc.close();
    }
}
