package Parte04;

public class ThreadLetras extends Thread {
    @Override
    public void run() {
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.println("[Letras ] " + c);
            try { Thread.sleep(100); } catch (InterruptedException e) { return; }
        }
    }
}
