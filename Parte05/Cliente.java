package Parte05;

public class Cliente {
    private final String nome;
    private final String cpf;
    private final String email;

    public Cliente(String nome, String cpf, String email) throws DadosInvalidosException {
        if (nome == null || nome.trim().isEmpty())
            throw new DadosInvalidosException("O nome do cliente não pode ser vazio.");
        String digitos = cpf == null ? "" : cpf.replaceAll("[.\\-]", "");
        if (!digitos.matches("\\d{11}"))
            throw new DadosInvalidosException("O CPF deve conter exatamente 11 dígitos numéricos.");
        if (email == null || !email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"))
            throw new DadosInvalidosException("E-mail inválido.");
        this.nome = nome.trim();
        this.cpf = digitos;
        this.email = email.trim();
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }

    @Override
    public String toString() { return nome; }
}
