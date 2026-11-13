package Parte05;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** Tarefa 15 - Sistema de controle de estoque (Swing + JOptionPane). */
public class ControleEstoque extends JFrame {

    private final List<Produto> produtos = new ArrayList<>();
    private final JTextField txtNome = new JTextField(15);
    private final JTextField txtPreco = new JTextField(8);
    private final JTextField txtQtd = new JTextField(8);
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"Produto", "Preço (R$)", "Quantidade", "Valor total (R$)"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabela = new JTable(modelo);

    public ControleEstoque() {
        super("Controle de Estoque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Nome:"));       form.add(txtNome);
        form.add(new JLabel("Preço:"));      form.add(txtPreco);
        form.add(new JLabel("Quantidade:")); form.add(txtQtd);
        JButton btnInserir = new JButton("Inserir produto");
        btnInserir.addActionListener(e -> inserir());
        form.add(btnInserir);

        JPanel acoes = new JPanel();
        JButton btnConsultar = new JButton("Consultar preço e quantidade");
        btnConsultar.addActionListener(e -> consultar());
        JButton btnTotal = new JButton("Calcular valor total do estoque");
        btnTotal.addActionListener(e -> calcularTotal());
        acoes.add(btnConsultar);
        acoes.add(btnTotal);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);   // listagem de produtos
        add(acoes, BorderLayout.SOUTH);
        setSize(780, 380);
        setLocationRelativeTo(null);
    }

    private void inserir() {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double preco;
        int qtd;
        try {
            preco = Double.parseDouble(txtPreco.getText().trim().replace(',', '.'));
            qtd = Integer.parseInt(txtQtd.getText().trim());
            if (preco < 0 || qtd < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser números não negativos.",
                    "Dados inválidos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Produto p = new Produto(nome, preco, qtd);
        produtos.add(p);
        modelo.addRow(new Object[]{p.getNome(), String.format("%.2f", p.getPreco()),
                p.getQuantidade(), String.format("%.2f", p.getValorTotal())});
        txtNome.setText(""); txtPreco.setText(""); txtQtd.setText("");
        JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
    }

    private void consultar() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um produto na lista.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Produto p = produtos.get(linha);
        JOptionPane.showMessageDialog(this, String.format("Produto: %s%nPreço: R$ %.2f%nQuantidade: %d",
                p.getNome(), p.getPreco(), p.getQuantidade()), "Consulta", JOptionPane.INFORMATION_MESSAGE);
    }

    private void calcularTotal() {
        double total = 0;
        for (Produto p : produtos) total += p.getValorTotal();
        JOptionPane.showMessageDialog(this, String.format("Valor total do estoque: R$ %.2f", total),
                "Valor do estoque", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ControleEstoque().setVisible(true));
    }
}
