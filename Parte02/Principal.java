package Parte02;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Exercício 1 - DiaSemana
        System.out.println("=== Exercício 1: DiaSemana ===");
        DiaSemana dia = DiaSemana.QUARTA;
        System.out.println("Dia armazenado: " + dia);
        System.out.println("Todos os dias: ");
        for (DiaSemana d : DiaSemana.values()) System.out.print(d + " ");
        System.out.println("\n");

        // Exercício 2 - CategoriaProduto
        System.out.println("=== Exercício 2: CategoriaProduto ===");
        System.out.println(new Produto("Notebook", CategoriaProduto.INFORMATICA));
        System.out.println(new Produto("Sofá", CategoriaProduto.MOVEIS));
        System.out.println();

        // Exercício 3 - StatusPedido
        System.out.println("=== Exercício 3: StatusPedido ===");
        Pedido pedido = new Pedido("Notebook");
        System.out.println(pedido);
        pedido.setStatus(StatusPedido.APROVADO);
        System.out.println(pedido);
        pedido.setStatus(StatusPedido.ENVIADO);
        System.out.println("Status consultado: " + pedido.getStatus());
        System.out.println();

        // Exercício 4 - NivelAcesso
        System.out.println("=== Exercício 4: NivelAcesso ===");
        System.out.print("Informe o nível (ADMINISTRADOR, GERENTE ou USUARIO): ");
        try {
            NivelAcesso nivel = NivelAcesso.valueOf(sc.nextLine().trim().toUpperCase());
            System.out.println("Permissões de " + nivel + ": " + nivel.getPermissoes());
        } catch (IllegalArgumentException e) {
            System.out.println("Nível de acesso inválido!");
        }
        System.out.println();

        // Exercício 5 - Prioridade
        System.out.println("=== Exercício 5: Prioridade ===");
        System.out.print("Título do chamado: ");
        String titulo = sc.nextLine();
        System.out.print("Prioridade (BAIXA, MEDIA, ALTA, URGENTE): ");
        try {
            Prioridade p = Prioridade.valueOf(sc.nextLine().trim().toUpperCase());
            Chamado chamado = new Chamado(titulo, p);
            System.out.println(chamado);
            System.out.println(chamado.mensagem());
        } catch (IllegalArgumentException e) {
            System.out.println("Prioridade inválida!");
        }
        sc.close();
    }
}
