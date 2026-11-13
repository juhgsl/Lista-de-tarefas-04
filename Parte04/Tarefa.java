package Parte04;

public class Tarefa implements Runnable {
    private final String nome;
    private final int execucoes;

    public Tarefa(String nome, int execucoes) {
        this.nome = nome;
        this.execucoes = execucoes;
    }

    @Override
    public void run() {
        for (int i = 1; i <= execucoes; i++) {
            System.out.println(nome + " - execução nº " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(nome + " interrompida.");
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(nome + " finalizada.");
    }
}
