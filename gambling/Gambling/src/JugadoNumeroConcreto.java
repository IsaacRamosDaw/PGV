import java.util.Random;

public class JugadoNumeroConcreto extends Gambler implements Runnable {
  Random r = new Random();

  public JugadoNumeroConcreto(Banca casino, String name){
    super(casino, name);
    this.numeroEscogido = r.nextInt(1, 37);
  }

  public void comprobarNumero(){
    if (acertoNumero()){
      System.out.println("El jugador: " + name + " acertó");
      ganar();
    } else {
      System.out.println("El jugador: " + name + " no acertó");
      casino.bancaGano(10);
    }
  }



  @Override
  public void run() {
    while (true) {
      if (imBroke() || casino.bancaBroke()) {
        System.out.println("El jugador " + name + " se retira. Saldo final: " + saldo);
        break;
      }

      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }

      apostar();
      comprobarNumero();

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public void apostar(){
    saldo -= 10;
  }

  public boolean acertoNumero(){
    return numeroEscogido == casino.getNumeroGanador();
  }

  public void ganar(){
    synchronized (casino) {
      casino.perderDinero(name, 360);
      saldo += 360;
    }
  }

  public int getNumeroEscogido(){
    return numeroEscogido;
  }
}
