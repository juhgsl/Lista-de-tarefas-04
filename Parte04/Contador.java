package Parte04;

public class Contador {

    static final int INCREMENTOS = 1000;
    static final int RODADAS = 100;

    static class ContadorSemSync {
        int valor = 0;
        void incrementar() {                     
            int tmp = valor;                      
            Thread.yield();                       
            valor = tmp + 1;                     
        }
    }

    static class ContadorComSync {
        int valor = 0;
        synchronized void incrementar() {        
            int tmp = valor;
            Thread.yield();
            valor = tmp + 1;
        }
    }

    interface Contador{ void incrementar(); int valor(); }

    static int executar(Contador c) throws InterruptedException {
        Runnable tarefa = () -> {
            for (int i = 0; i < INCREMENTOS; i++) c.incrementar();
        };
        Thread t1 = new Thread(tarefa), t2 = new Thread(tarefa);
        t1.start(); t2.start();
        t1.join(); t2.join();
        return c.valor();
    }

    private void incrementar() {
		// TODO Auto-generated method stub
		
	}

	private int valor() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) throws InterruptedException {
        int esperado = 2 * INCREMENTOS;

    
        ContadorSemSync a = new ContadorSemSync();
        int r1 = executar(wrap(a));
        ContadorComSync b = new ContadorComSync();
        int r2 = executar(wrap(b));
        System.out.println("Esperado: " + esperado);
        System.out.println("Sem sincronização (1 execução): " + r1);
        System.out.println("Com synchronized  (1 execução): " + r2);

        
        int errosSem = 0, errosCom = 0;
        for (int i = 0; i < RODADAS; i++) {
            if (executar(wrap(new ContadorSemSync())) != esperado) errosSem++;
            if (executar(wrap(new ContadorComSync())) != esperado) errosCom++;
        }
        System.out.println("\nEm " + RODADAS + " execuções:");
        System.out.println("  Sem sincronização: resultado errado em " + errosSem + " execuções");
        System.out.println("  Com synchronized : resultado errado em " + errosCom + " execuções");

        System.out.println("\nExplicação:");
        System.out.println("'valor++' não é uma operação única: são três passos (ler, somar, gravar).");
        System.out.println("Se duas threads leem o mesmo valor antes de qualquer uma gravar, uma das");
        System.out.println("atualizações é perdida. Isso é a CONDIÇÃO DE CORRIDA (Race Condition): o");
        System.out.println("resultado depende da ordem imprevisível em que as threads são escalonadas.");
        System.out.println("Com synchronized, só uma thread por vez executa incrementar(), então nenhuma");
        System.out.println("atualização se perde e o resultado é sempre " + esperado + ".");
    }

    static Contador wrap(ContadorSemSync c) {
        return new Contador() {
            public void incrementar() { c.incrementar(); }
            public int valor() { return c.valor; }
        };
    }

    static Contador wrap(ContadorComSync c) {
        return new Contador() {
            public void incrementar() { c.incrementar(); }
            public int valor() { return c.valor; }
        };
    }
}
