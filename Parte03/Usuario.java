package Parte03;

public class Usuario {
    private final String nome;
    private final int idade;
    private final String cpf;

    public Usuario(String nome, int idade, String cpf)
            throws NomeInvalidoException, IdadeInvalidaException, CpfInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException("O nome não pode ser vazio.");
        }
        if (idade < 0) {
            throw new IdadeInvalidaException("A idade não pode ser inferior a zero.");
        }
        String digitos = cpf == null ? "" : cpf.replaceAll("[.\\-]", "");
        if (digitos.length() != 11) {
            throw new CpfInvalidoException("O CPF deve conter 11 dígitos (informado: " + digitos.length() + ").");
        }
        this.nome = nome.trim();
        this.idade = idade;
        this.cpf = digitos;
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + " | Idade: " + idade + " | CPF: " + cpf;
    }
}
    
