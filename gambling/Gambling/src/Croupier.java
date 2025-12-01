public class Croupier implements Runnable {
  private final Banca casino;

  public Croupier(Banca casino) {
    this.casino = casino;
  }

  @Override
  public void run() {
    int ronda = 1;
    try {
      while (!casino.bancaBroke()) {
        System.out.println("=========================================");
        System.out.println("Inicio de la ronda: " + ronda);
        System.out.println("=========================================");
        ronda++;

        casino.cambiarNumero();
        Thread.sleep(3000);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("¡El Croupier se retira! La Banca se ha quedado sin dinero.");
  }
}