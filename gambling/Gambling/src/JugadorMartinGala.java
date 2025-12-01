import java.util.Random;

public class JugadorMartinGala extends Gambler implements Runnable {
  Random r = new Random();

  private static final int APUESTA_INICIAL = 10;
  private int apuestaActual;

  public JugadorMartinGala(Banca casino, String name) {
    super(casino, name);

    this.numeroEscogido = r.nextInt(1, 37);

    this.apuestaActual = APUESTA_INICIAL;

    System.out.println(getName() + " juega la Martingala al número " + this.numeroEscogido + " con apuesta inicial de " + APUESTA_INICIAL);
  }

  @Override
  public void run() {
    while (true) {
      if (imBroke() || apuestaActual > saldo || casino.bancaBroke()) {
        System.out.println("El jugador " + name + " se retira " + (
          imBroke() ? "¡Bancarrota!" : 
          (apuestaActual > saldo ? "No puede cubrir la apuesta de " + apuestaActual : 
          "Banca perdió.")) + " Saldo final: " + saldo);
        break;
      }

      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }

      apostar();
      comprobarResultado();

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public void comprobarResultado() {
    if (acertoNumero()) {
      System.out.println("🥳 " + name + " acertó! Número: " + casino.getNumeroGanador());
      ganar();
    } else {
      System.out.println("📉 " + name + " no acertó. Número: " + casino.getNumeroGanador());
      this.apuestaActual *= 2;
      System.out.println("   Martingala de " + name + " doblada. Próxima apuesta: " + apuestaActual);
    }
  }


  public void ganar() {
    synchronized (casino) {
      casino.perderDinero(name, (apuestaActual * 36));
      saldo += (apuestaActual * 36);
    }

    reiniciarApuesta();
  }

  public void reiniciarApuesta() {
    this.apuestaActual = APUESTA_INICIAL;
    System.out.println("   Martingala de " + name + " reiniciada. Apuesta: " + apuestaActual);
  }
  public boolean acertoNumero() {
    return numeroEscogido == casino.getNumeroGanador();
  }

  public void apostar() {
    saldo -= apuestaActual;
  }
}