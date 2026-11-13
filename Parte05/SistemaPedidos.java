package Parte05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public class SistemaPedidos {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Produto> produtos = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private int proximoPedido = 1;
    private int proximoProduto = 1;
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public Cliente cadastrarCliente(String nome, String cpf, String email) throws DadosInvalidosException {
        Cliente c = new Cliente(nome, cpf, email);
        for (Cliente existente : clientes)
            if (existente.getCpf().equals(c.getCpf()))
                throw new DadosInvalidosException("Já existe um cliente com esse CPF.");
        clientes.add(c);
        return c;
    }

    public Produto cadastrarProduto(String descricao, double preco, int estoque) throws DadosInvalidosException {
        ProdutoPedido p = new ProdutoPedido(proximoProduto, descricao, preco, estoque);
        proximoProduto++;
        produtos.add(p);
        return p;
    }

    public Pedido criarPedido(Cliente cliente, List<ItemPedido> itens) throws DadosInvalidosException {
        Pedido p = new Pedido(proximoPedido, cliente, itens);
        for (ItemPedido i : itens) i.getProduto().baixarEstoque(i.getQuantidade());
        proximoPedido++;
        pedidos.add(p);
        return p;
    }

   
    public int processarPendentes(Consumer<Pedido> aoMudarStatus) {
        int enviados = 0;
        for (Pedido p : pedidos) {
            if (p.getStatus() == StatusPedido.PENDENTE) {
                executor.submit(new ProcessadorPedido(p, aoMudarStatus));
                enviados++;
            }
        }
        return enviados;
    }

    public void encerrar() { executor.shutdownNow(); }

    public List<Cliente> getClientes() { return clientes; }
    public List<Produto> getProdutos() { return produtos; }
    public List<Pedido> getPedidos() { return pedidos; }
}