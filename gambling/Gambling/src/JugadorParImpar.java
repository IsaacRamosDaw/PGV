import java.util.Random;

public class JugadorParImpar extends Gambler implements Runnable {
  Random r = new Random();

  public JugadorParImpar(Banca casino, String name) {
    super(casino, name);

    this.numeroEscogido = r.nextInt(2);
  }

  public String getParImparApuesta(){
    return getName() + " apuesta a que saldrá " + ((this.numeroEscogido == 0) ? "PAR" : "IMPAR");
  }

  @Override
  public void run() {
    while (true) {
      if (imBroke() || casino.bancaBroke()) {
        System.out.println("El jugador " + name + " se retira. Saldo final: " + saldo);
        break;
      }

      try {
        // El jugador espera los 3000ms que dura la ronda de la ruleta
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }

      apostar();
      comprobarResultado();

      try {
        Thread.sleep(100); // Pequeña pausa
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }


  public void comprobarResultado() {
    if (acertoParImpar()) {
      System.out.println(
        "El jugador: " + name + " acertó a " + 
        ((numeroEscogido == 0) ? "PAR" : "IMPAR") + "!"
      );
      ganar();
    } else {
      System.out.println("El jugador: " + name + " no acertó. Número: " + casino.getNumeroGanador());
    }
  }


  public boolean acertoParImpar() {
    int numeroGanador = casino.getNumeroGanador();

    if (0 == numeroGanador) { return false; }

    boolean esPar = (numeroGanador % 2 == 0);

    if (0 == numeroEscogido) return esPar;
    else return !esPar;
  }

  public void ganar() {
    synchronized (casino) {
      casino.perderDinero(name, 20);
      saldo += 20;
    }
  }

  public void apostar() {
    saldo -= 10;
  }
}