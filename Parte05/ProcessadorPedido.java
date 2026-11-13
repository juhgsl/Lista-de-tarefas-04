package Parte05;

import java.util.Random;
import java.util.function.Consumer;

/** Cada pedido é processado por uma thread independente (Runnable). */
public class ProcessadorPedido implements Runnable {
    private final Pedido pedido;
    private final Consumer<Pedido> aoMudarStatus;
    private final Random random = new Random();

    public ProcessadorPedido(Pedido pedido, Consumer<Pedido> aoMudarStatus) {
        this.pedido = pedido;
        this.aoMudarStatus = aoMudarStatus;
    }

    @Override
    public void run() {
        StatusPedido[] etapas = StatusPedido.values();
        try {
            // começa em PENDENTE (índice 0) e avança até ENTREGUE
            for (int i = pedido.getStatus().ordinal() + 1; i < etapas.length; i++) {
                Thread.sleep(1000 + random.nextInt(1500));
                pedido.setStatus(etapas[i]);
                aoMudarStatus.accept(pedido);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}