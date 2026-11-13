package Parte04;

public class PrincipalConta {
    public static void main(String[] args) throws InterruptedException {
        ContaBancaria conta = new ContaBancaria(1000.00);

        Runnable saque = () -> conta.sacar(700.00);
        Thread t1 = new Thread(saque, "Thread-1");
        Thread t2 = new Thread(saque, "Thread-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.printf("%nSaldo final: R$ %.2f%n", conta.getSaldo());
        System.out.println("\nPor que a sincronização é necessária?");
        System.out.println("Sem synchronized, as duas threads poderiam verificar 'saldo >= 700' ao mesmo");
        System.out.println("tempo (ambas veriam R$ 1000), e as duas debitariam R$ 700, deixando o saldo em");
        System.out.println("-R$ 400. Com synchronized, a verificação e o débito formam uma seção crítica:");
        System.out.println("a segunda thread só entra depois que a primeira terminou, vê o saldo de R$ 300");
        System.out.println("e tem o saque negado.");
    }
}