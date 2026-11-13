package Parte02;

public class Pedido {
    private String descricao;
    private StatusPedido status;

    public Pedido(String descricao) {
        this.descricao = descricao;
        this.status = StatusPedido.PENDENTE;
    }

    public StatusPedido getStatus() { return status; }

    public void setStatus(StatusPedido status) { this.status = status; }

    @Override
    public String toString() {
        return "Pedido '" + descricao + "' - status: " + status;
    }
}
