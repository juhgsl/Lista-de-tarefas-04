package Parte05;

import javax.swing.*;
import java.awt.*;

/** Tarefa 13 - Tela de cadastro de alunos (Swing). */
public class TelaCadastroAluno extends JFrame {

    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtIdade = new JTextField(5);
    private final JComboBox<String> cmbCurso = new JComboBox<>(new String[]{
            "Análise e Desenvolvimento de Sistemas", "Engenharia de Software",
            "Ciência da Computação", "Redes de Computadores", "Sistemas de Informação"});
    private final JTextArea areaResultado = new JTextArea(6, 30);

    public TelaCadastroAluno() {
        super("Cadastro de Alunos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        form.add(new JLabel("Nome:"));   form.add(txtNome);
        form.add(new JLabel("Idade:"));  form.add(txtIdade);
        form.add(new JLabel("Curso:"));  form.add(cmbCurso);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);

        JPanel topo = new JPanel(new BorderLayout());
        topo.add(form, BorderLayout.CENTER);
        topo.add(botoes, BorderLayout.SOUTH);

        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(BorderFactory.createTitledBorder("Dados cadastrados"));

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    private void salvar() {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int idade;
        try {
            idade = Integer.parseInt(txtIdade.getText().trim());
            if (idade < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        areaResultado.setText("Nome: " + nome + "\nIdade: " + idade + "\nCurso: " + cmbCurso.getSelectedItem());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroAluno().setVisible(true));
    }
}

