package gamblingRedux;

import java.util.Random;

public class JugadorNumeroConcreto extends Gambler implements Runnable {
  Random r = new Random();

  public JugadorNumeroConcreto(Banca casino, String name) {
    super(casino, name);
    this.numeroEscogido = r.nextInt(1, 37);
  }

  // Método que se encarga de hacer perder dinero al jugador al apostar
  public void apostar() {
    numeroEscogido = r.nextInt(1, 37);
    saldo -= 10;
  }

  // Método que se encarga de comprobar si el jugador ha acertado el número,
  // si acierta gana 360 si no pierde 10
  public void comprobarNumero() {
    if (numeroEscogido == casino.getNumeroGanador()) {
      System.out.println(name + " apostó a " + numeroEscogido + " acertó gana 360");
      ganarApuesta(360);
    } else {
      System.out.println(name + " apostó a " + numeroEscogido + " no acertó pierde 10");
      casino.bancaGano(10);
    }
  }

  // Método principal que se encarga de jugar
  @Override
  public void run() {
    while (true) {
      if (imBroke() || casino.bancaIsBroke()) {
        System.out.println(name + " se retira. Saldo final: " + saldo);
        break;
      }

      apostar();
      comprobarNumero();

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
