package Parte04;

public class PrincipalThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadNumeros();
        Thread t2 = new ThreadLetras();

        t1.start();   // start() cria uma nova thread e chama run() nela
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Fim. Observe que a ordem de saída pode variar a cada execução,");
        System.out.println("pois o escalonador do sistema decide qual thread executa em cada momento.");
    }
}