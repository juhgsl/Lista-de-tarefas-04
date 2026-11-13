package Parte04;

public class PrincipalPedido {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ProcessadorPedido(i + 1), "Thread-Pedido-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("Todos os pedidos foram processados.");
    }
}