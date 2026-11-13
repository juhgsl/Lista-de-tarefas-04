package Parte04;

public class PrincipalTarefa {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Tarefa("Tarefa A", 5));
        Thread t2 = new Thread(new Tarefa("Tarefa B", 5));
        Thread t3 = new Thread(new Tarefa("Tarefa C", 5));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        System.out.println("Todas as tarefas terminaram.");
    }
}
