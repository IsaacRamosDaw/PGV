import java.util.Random;

public class Banca {
  private int saldo = 50000;
  Random r = new Random();
  int numeroGanador;

  public synchronized void perderDinero(String jugadorNombre, int perdida) {
    if (bancaBroke()) {
      bancaPerdio();
      return;
    }

    if ((saldo - perdida) <= 0) {
      bancaSinDineroSuficiente(jugadorNombre);
      return;
    }

    saldo -= perdida;
    System.out.println("Banca pagó a " + jugadorNombre + ". Nuevo Saldo Banca: " + saldo);
  }

  public synchronized int getNumeroGanador() {
    return numeroGanador;
  }

  public synchronized void bancaGano(int cantidad) {
    saldo += cantidad;
    System.out.println("Banca ganó " + cantidad);
    System.out.println("Banca tiene " + saldo);
  }

  public synchronized void cambiarNumero() {
    numeroGanador = r.nextInt(0, 37);
    System.out.println("\n--- Nuevo Número Ganador: " + numeroGanador + " ---\n");
  }

  public boolean bancaBroke() {
    return 0 >= saldo;
  }

  public void bancaPerdio() {
    System.out.println("La banca se ha quedado sin dinero, la banca perdió");
  }

  public void bancaSinDineroSuficiente(String jugadorNombre) {
    System.out.println("La banca no tiene el dinero suficiente para pagar al jugador: " + jugadorNombre);
  }
}
