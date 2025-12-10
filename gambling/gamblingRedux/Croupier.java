package gamblingRedux;

public class Croupier implements Runnable {
  // Variables necesarias para que el croupier funcione, la banca a la que pertenecen todos
  private final Banca casino;

  // Constructor
  public Croupier(Banca casino) { this.casino = casino; }

  // Método princopal que se encarga de generar rondas y cambiar el número ganador
  @Override
  public void run() {
    // Mientras la banca no se quede sin dinero se jugará, en el momento en que la banca se quede sin dinero el croupier se retirará
    try {
      while (!casino.bancaIsBroke()) {

        System.out.println("\n =========================================");
        System.out.println("Saldo de la banca: " + casino.getSaldo());
        System.out.println("Inicio de la ronda: " + casino.getNumeroRonda());
        System.out.println("=========================================");

        casino.siguienteRonda();
        casino.cambiarNumeroGanador();
        Thread.sleep(3000);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("¡El Croupier se retira! La Banca se ha quedado sin dinero.");
  }
}
