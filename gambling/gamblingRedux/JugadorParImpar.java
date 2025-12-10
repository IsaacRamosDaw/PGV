package gamblingRedux;

import java.util.Random;

public class JugadorParImpar extends Gambler implements Runnable {
  Random r = new Random();
  private boolean apuestaPar;

  public JugadorParImpar(Banca casino, String name) { super(casino, name); }

  // Método que se encarga de hacer perder dinero al jugador al apostar
  public synchronized void apostar() {
    apuestaPar = r.nextBoolean();
    saldo -= 10;
  }

  public synchronized void comprobarResultado() {
    boolean esPar = (casino.getNumeroGanador() % 2 == 0);

    if (apuestaPar == esPar) {
      System.out.println(name + " apostó a " + (apuestaPar ? "PAR" : "IMPAR") + " acertó gana 20");
      ganarApuesta(20);
    } else {
      System.out.println(name + " apostó a " + (apuestaPar ? "PAR" : "IMPAR") + " no acertó pierde 10");
      casino.bancaGano(10);
    }
  }

  @Override
  public void run() {
    while (true) {
      if (imBroke() || casino.bancaIsBroke()) {
        System.out.println(name + " se retira. Saldo final: " + saldo);
        break;
      }

      apostar();
      comprobarResultado();

      // Esperar a la siguiente ronda
      int rondaActual = casino.getNumeroRonda();
      while (rondaActual == casino.getNumeroRonda() && !casino.bancaIsBroke() && !imBroke()) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          return;
        }
      }
    }
  }
}
