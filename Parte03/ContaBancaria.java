package Parte03;

public class ContaBancaria {
    private double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() { return saldo; }

    /**
     * @throws IllegalArgumentException   se o valor for zero ou negativo
     * @throws SaldoInsuficienteException se o valor for maior que o saldo
     */
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                String.format("Saldo insuficiente. Saldo: R$ %.2f | Saque: R$ %.2f", saldo, valor));
        }
        saldo -= valor;
    }
}
