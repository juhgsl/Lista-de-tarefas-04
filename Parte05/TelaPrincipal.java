package Parte05;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Interface gráfica do sistema de gerenciamento de pedidos. */
public class TelaPrincipal extends JFrame {

    private final SistemaPedidos sistema = new SistemaPedidos();

    // Clientes
    private final JTextField txtNomeCli = new JTextField(14);
    private final JTextField txtCpf = new JTextField(11);
    private final JTextField txtEmail = new JTextField(16);
    private final DefaultTableModel modeloClientes = modelo("Nome", "CPF", "E-mail");

    // Produtos
    private final JTextField txtDescricao = new JTextField(14);
    private final JTextField txtPreco = new JTextField(7);
    private final JTextField txtEstoque = new JTextField(6);
    private final DefaultTableModel modeloProdutos = modelo("Código", "Descrição", "Preço (R$)", "Estoque");

    // Pedidos
    private final JComboBox<Cliente> cmbCliente = new JComboBox<>();
    private final JComboBox<Produto> cmbProduto = new JComboBox<>();
    private final JTextField txtQtd = new JTextField(4);
    private final DefaultListModel<ItemPedido> carrinho = new DefaultListModel<>();
    private final DefaultTableModel modeloPedidos = modelo("Nº", "Cliente", "Itens", "Total (R$)", "Status");

    public TelaPrincipal() {
        super("Sistema de Gerenciamento de Pedidos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Clientes", painelClientes());
        abas.addTab("Produtos", painelProdutos());
        abas.addTab("Pedidos", painelPedidos());
        add(abas);
        setSize(860, 520);
        setLocationRelativeTo(null);
    }

    private static DefaultTableModel modelo(String... colunas) {
        return new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
    }

    // ---------------- Aba Clientes ----------------
    private JPanel painelClientes() {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Nome:"));   form.add(txtNomeCli);
        form.add(new JLabel("CPF:"));    form.add(txtCpf);
        form.add(new JLabel("E-mail:")); form.add(txtEmail);
        JButton btn = new JButton("Cadastrar cliente");
        btn.addActionListener(e -> cadastrarCliente());
        form.add(btn);

        JPanel p = new JPanel(new BorderLayout());
        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(new JTable(modeloClientes)), BorderLayout.CENTER);
        return p;
    }

    private void cadastrarCliente() {
        try {
            Cliente c = sistema.cadastrarCliente(txtNomeCli.getText(), txtCpf.getText(), txtEmail.getText());
            modeloClientes.addRow(new Object[]{c.getNome(), c.getCpf(), c.getEmail()});
            cmbCliente.addItem(c);
            txtNomeCli.setText(""); txtCpf.setText(""); txtEmail.setText("");
        } catch (DadosInvalidosException ex) {
            erro(ex.getMessage());
        }
    }

    // ---------------- Aba Produtos ----------------
    private JPanel painelProdutos() {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Descrição:")); form.add(txtDescricao);
        form.add(new JLabel("Preço:"));     form.add(txtPreco);
        form.add(new JLabel("Estoque:"));   form.add(txtEstoque);
        JButton btn = new JButton("Cadastrar produto");
        btn.addActionListener(e -> cadastrarProduto());
        form.add(btn);

        JPanel p = new JPanel(new BorderLayout());
        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(new JTable(modeloProdutos)), BorderLayout.CENTER);
        return p;
    }

    private void cadastrarProduto() {
        try {
            double preco = Double.parseDouble(txtPreco.getText().trim().replace(',', '.'));
            int estoque = Integer.parseInt(txtEstoque.getText().trim());
            sistema.cadastrarProduto(txtDescricao.getText(), preco, estoque);
            txtDescricao.setText(""); txtPreco.setText(""); txtEstoque.setText("");
            atualizarProdutos();
        } catch (NumberFormatException ex) {
            erro("Preço e estoque devem ser numéricos.");
        } catch (DadosInvalidosException ex) {
            erro(ex.getMessage());
        }
    }

    private void atualizarProdutos() {
        modeloProdutos.setRowCount(0);
        cmbProduto.removeAllItems();
        for (Produto p : sistema.getProdutos()) {
            modeloProdutos.addRow(new Object[]{p.getCodigo(), p.getDescricao(),
                    String.format("%.2f", p.getPreco()), p.getEstoque()});
            cmbProduto.addItem(p);
        }
    }

    // ---------------- Aba Pedidos ----------------
    private JPanel painelPedidos() {
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha1.add(new JLabel("Cliente:")); linha1.add(cmbCliente);
        linha1.add(new JLabel("Produto:")); linha1.add(cmbProduto);
        linha1.add(new JLabel("Qtd:"));     linha1.add(txtQtd);
        JButton btnItem = new JButton("Adicionar item");
        btnItem.addActionListener(e -> adicionarItem());
        linha1.add(btnItem);

        JList<ItemPedido> lista = new JList<>(carrinho);
        JScrollPane scrollCarrinho = new JScrollPane(lista);
        scrollCarrinho.setBorder(BorderFactory.createTitledBorder("Itens do pedido em montagem"));
        scrollCarrinho.setPreferredSize(new Dimension(200, 90));

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCriar = new JButton("Criar pedido");
        btnCriar.addActionListener(e -> criarPedido());
        JButton btnProcessar = new JButton("Processar pedidos pendentes (threads)");
        btnProcessar.addActionListener(e -> processar());
        linha2.add(btnCriar);
        linha2.add(btnProcessar);

        JPanel topo = new JPanel(new BorderLayout());
        topo.add(linha1, BorderLayout.NORTH);
        topo.add(scrollCarrinho, BorderLayout.CENTER);
        topo.add(linha2, BorderLayout.SOUTH);

        JPanel p = new JPanel(new BorderLayout());
        p.add(topo, BorderLayout.NORTH);
        p.add(new JScrollPane(new JTable(modeloPedidos)), BorderLayout.CENTER);
        return p;
    }

    private void adicionarItem() {
        Produto prod = (Produto) cmbProduto.getSelectedItem();
        if (prod == null) { erro("Cadastre e selecione um produto."); return; }
        try {
            int qtd = Integer.parseInt(txtQtd.getText().trim());
            if (qtd <= 0) throw new NumberFormatException();
            carrinho.addElement(new ItemPedido(prod, qtd));
            txtQtd.setText("");
        } catch (NumberFormatException ex) {
            erro("A quantidade deve ser um inteiro maior que zero.");
        }
    }

    private void criarPedido() {
        List<ItemPedido> itens = new ArrayList<>();
        for (int i = 0; i < carrinho.size(); i++) itens.add(carrinho.get(i));
        try {
            Pedido p = sistema.criarPedido((Cliente) cmbCliente.getSelectedItem(), itens);
            carrinho.clear();
            atualizarProdutos();   
            atualizarPedidos();
            JOptionPane.showMessageDialog(this, "Pedido #" + p.getNumero() + " criado com sucesso!");
        } catch (DadosInvalidosException ex) {
            erro(ex.getMessage());
        }
    }

    private void processar() {
        
        int n = sistema.processarPendentes(p -> SwingUtilities.invokeLater(this::atualizarPedidos));
        if (n == 0) erro("Não há pedidos pendentes para processar.");
        else atualizarPedidos();
    }

    private void atualizarPedidos() {
        modeloPedidos.setRowCount(0);
        for (Pedido p : sistema.getPedidos()) {
            String itens = p.getItens().stream().map(ItemPedido::toString).collect(Collectors.joining(", "));
            modeloPedidos.addRow(new Object[]{p.getNumero(), p.getCliente().getNome(), itens,
                    String.format("%.2f", p.getTotal()), p.getStatus()});
        }
    }

    private void erro(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void dispose() {
        sistema.encerrar();
        super.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}

