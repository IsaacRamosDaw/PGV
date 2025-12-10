package gamblingRedux;

import java.util.Random;

public class JugadorMartinGala extends Gambler implements Runnable {
  Random r = new Random();

  private int apuestaActual;

  public JugadorMartinGala(Banca casino, String name) {
    super(casino, name);
    this.numeroEscogido = r.nextInt(1, 37);
    this.apuestaActual = 10;
  }

  // Método que se encarga de hacer perder dinero al jugador al apostar
  public void apostar() {
    numeroEscogido = r.nextInt(1, 37);
    saldo -= apuestaActual;
  }

  
  // Método que se encarga de comprobar si el jugador ha acertado el número. Si acierta gana 360, si no pierde 10 y se duplica la apuesta
  public void comprobarResultado() {
    if (numeroEscogido == casino.getNumeroGanador()) {
      System.out.println(name + " apostó a " + numeroEscogido + " acertó, gana " + 360);
      ganarApuesta(360);
      apuestaActual = 10;
      System.out.println("Martingala de " + name + " reiniciada. Apuesta: " + apuestaActual);
    } else {
      System.out.println(name + " apostó a " + numeroEscogido + " no acertó, pierde " + apuestaActual);
      casino.bancaGano(apuestaActual);
      apuestaActual *= 2;
    }
  }

  @Override
  public void run() {
    while (true) {
      if (imBroke() || apuestaActual > saldo || casino.bancaIsBroke()) {
        System.out.println(name + " se retira " + (imBroke() ? "¡Bancarrota!"
          : (apuestaActual > saldo ? "No puede cubrir la apuesta de " + apuestaActual
          : "Banca perdió.")) + " Saldo final: " + saldo);
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
