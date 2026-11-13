package Parte04;

public class ThreadNumeros extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("[Números] " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) { return; }
        }
    }
}