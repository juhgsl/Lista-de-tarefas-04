package Parte05;

import javax.swing.*;
import java.awt.*;


public class CalculadoraGrafica extends JFrame {

    private final JTextField txtA = new JTextField(8);
    private final JTextField txtB = new JTextField(8);
    private final JLabel lblResultado = new JLabel("Resultado: ");

    public CalculadoraGrafica() {
        super("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel entradas = new JPanel(new GridLayout(2, 2, 8, 8));
        entradas.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        entradas.add(new JLabel("Número 1:")); entradas.add(txtA);
        entradas.add(new JLabel("Número 2:")); entradas.add(txtB);

        JPanel botoes = new JPanel(new GridLayout(1, 4, 6, 6));
        for (String op : new String[]{"+", "-", "×", "÷"}) {
            JButton b = new JButton(op);
            b.setFont(b.getFont().deriveFont(Font.BOLD, 18f));
            b.addActionListener(e -> calcular(op));
            botoes.add(b);
        }
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        centro.add(botoes, BorderLayout.CENTER);

        lblResultado.setFont(lblResultado.getFont().deriveFont(Font.BOLD, 16f));
        lblResultado.setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));

        add(entradas, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(lblResultado, BorderLayout.SOUTH);
        pack();
        setMinimumSize(new Dimension(300, getHeight()));
        setLocationRelativeTo(null);
    }

    private void calcular(String op) {
        double a, b;
        try {
            a = Double.parseDouble(txtA.getText().trim().replace(',', '.'));
            b = Double.parseDouble(txtB.getText().trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite números válidos nos dois campos.",
                    "Entrada inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double r;
        switch (op) {
            case "+": r = a + b; break;
            case "-": r = a - b; break;
            case "×": r = a * b; break;
            default:
                if (b == 0) {
                    JOptionPane.showMessageDialog(this, "Não é possível dividir por zero.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    lblResultado.setText("Resultado: erro");
                    return;
                }
                r = a / b;
        }
        lblResultado.setText("Resultado: " + r);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGrafica().setVisible(true));
    }
}
