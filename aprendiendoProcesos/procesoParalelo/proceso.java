public class proceso {
    public static void main(String[] args) {
        // Hilo paralelo
        HiloParalelo hilo = new HiloParalelo();
        hilo.start(); // inicia el hilo paralelo

        // Hilo principal
        for (int i = 1; i <= 5; i++) {
            System.out.println("Principal: " + i);
        }
    }
}

class HiloParalelo extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Paralelo: " + i);
        }
    }
}

