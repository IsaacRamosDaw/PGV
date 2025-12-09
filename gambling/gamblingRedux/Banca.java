package gamblingRedux;

import java.util.Random;

// Modela la banca del casino
public class Banca {
  private int SALDO = 50000;
  Random numeroRandom = new Random();
  int numeroGanador;

  // Método que se encarga de obtener el número ganador
  public synchronized int getNumeroGanador() {
    return numeroGanador;
  }

  // Método que se encarga de obtener el saldo de la banca
  public synchronized int getSaldo() {
    return SALDO;
  }

  // Método que cambia el número ganador
  public synchronized void cambiarNumeroGanador() {
    numeroGanador = numeroRandom.nextInt(0, 37);
    System.out.println("\n--- Nuevo Número Ganador: " + numeroGanador + " ---\n");
  }

  // Método que se encarga de hacer ganar dinero a la banca
  public synchronized void bancaGano(int cantidad) {
    SALDO += cantidad;
  }

  // Método que se encarga de hacer perder dinero a la banca
  public synchronized void bancaPerdio(String jugadorNombre, int cantidad) {
    if (bancaIsBroke()) {
      bancaPerdio();
      return;
    }

    if ((SALDO - cantidad) <= 0) {
      bancaSinDineroSuficiente(jugadorNombre);
      bancaPerdio();
      return;
    }
    SALDO -= cantidad;
    System.out.println("\n--- Banca perdió " + cantidad + " Ahora tiene: " + SALDO + " ---\n");
  }

  // Método boolean que informa de si la banca se ha quedado a cero
  public boolean bancaIsBroke() {
    return 0 >= SALDO;
  }

  // Método que informa de si la banca no puede pagar a un jugador
  public void bancaSinDineroSuficiente(String jugadorNombre) {
    System.out.println("La banca no tiene dinero suficiente para pagar a " + jugadorNombre);
  }

  // Variable para controlar la ronda
  private int numeroRonda = 1;

  public synchronized int getNumeroRonda() {
    return numeroRonda;
  }

  public synchronized void siguienteRonda() {
    numeroRonda++;
  }

  // Método que termina el juego
  public void bancaPerdio() {
    System.out.println("La banca se ha quedado sin dinero, la banca perdió");
  }
}
