package Parte05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final int numero;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private volatile StatusPedido status = StatusPedido.PENDENTE;   // acessado por várias threads

    public Pedido(int numero, Cliente cliente, List<ItemPedido> itens) throws DadosInvalidosException {
        if (cliente == null) throw new DadosInvalidosException("Selecione um cliente.");
        if (itens == null || itens.isEmpty()) throw new DadosInvalidosException("O pedido precisa ter ao menos um item.");
        this.numero = numero;
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens);
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return Collections.unmodifiableList(itens); }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public double getTotal() {
        double total = 0;
        for (ItemPedido i : itens) total += i.getSubtotal();
        return total;
    }
}