package gamblingRedux;

public class Lanzador {
  public static void main(String[] args) {
    Banca casino = new Banca();

    Thread croupierThread = new Thread(new Croupier(casino), "Croupier");
    croupierThread.start();

    for (int i = 1; i <= 4; i++) {
      JugadorNumeroConcreto jugador = new JugadorNumeroConcreto(casino, "Jugador-Concreto-" + i);
      Thread jugadorThread = new Thread(jugador, "Hilo-Jugador-" + i);
      jugadorThread.start();
    }

    for (int i = 1; i <= 4; i++) {
      JugadorMartinGala jugador = new JugadorMartinGala(casino, "Jugador-MartinGala-" +
          i);
      Thread jugadorThread = new Thread(jugador, "Hilo-Martingala-" + i);
      jugadorThread.start();
    }

    for (int i = 1; i <= 4; i++) {
      JugadorParImpar jugador = new JugadorParImpar(casino, "Jugador-ParImpar-" + i);
      Thread jugadorThread = new Thread(jugador, "Hilo-ParImpar-" + i);
      jugadorThread.start();
    }
  }
}