package Parte03;

public class Principal {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(500.00);
        double[] tentativas = {200.00, -50.00, 400.00, 300.00};

        for (double valor : tentativas) {
            try {
                conta.sacar(valor);
                System.out.printf("Saque de R$ %.2f realizado. Saldo atual: R$ %.2f%n", valor, conta.getSaldo());
            } catch (SaldoInsuficienteException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Valor inválido: " + e.getMessage());
            }
        }
    }
}
