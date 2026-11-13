package Parte04;

public class ContaBancaria {
    private double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized double getSaldo() { return saldo; }

    /**
     * synchronized: apenas uma thread por vez executa este método na mesma conta.
     * Assim, a verificação do saldo e o débito acontecem como uma operação indivisível.
     */
    public synchronized boolean sacar(double valor) {
        String nome = Thread.currentThread().getName();
        System.out.printf("%s tenta sacar R$ %.2f (saldo: R$ %.2f)%n", nome, valor, saldo);
        if (saldo >= valor) {
            try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            saldo -= valor;
            System.out.printf("%s: saque realizado. Saldo: R$ %.2f%n", nome, saldo);
            return true;
        }
        System.out.printf("%s: saque NEGADO. Saldo insuficiente (R$ %.2f)%n", nome, saldo);
        return false;
    }
}