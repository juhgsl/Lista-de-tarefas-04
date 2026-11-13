package Parte04;

import java.util.Random;

public class ProcessadorPedido implements Runnable {
    private final int idPedido;
    private final Random random = new Random();

    public ProcessadorPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public void run() {
        long inicio = System.currentTimeMillis();
        System.out.println("Pedido #" + idPedido + ": início do processamento ("
                + Thread.currentThread().getName() + ")");
        try {
            Thread.sleep(1000 + random.nextInt(3000)); // 1 a 4 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Pedido #" + idPedido + ": processamento interrompido.");
            return;
        }
        long tempo = System.currentTimeMillis() - inicio;
        System.out.printf("Pedido #%d: finalizado. Tempo de processamento: %.1f s%n", idPedido, tempo / 1000.0);
    }
}
