public class Lanzador {
  public static void main(String[] args) {
    Banca casino = new Banca();

    Thread croupierThread = new Thread(new Croupier(casino), "Croupier");
    croupierThread.start();

    for (int i = 1; i <= 4; i++) {
      JugadoNumeroConcreto jugador = new JugadoNumeroConcreto(casino, "Jugador-Concreto" + i);
      System.out.println("Jugador-Concreto_" + i + " tiene el numero: " + jugador.getNumeroEscogido());
      Thread jugadorThread = new Thread(jugador, "Hilo_Jugador_" + i);
      jugadorThread.start();
    }

    for (int i = 1; i <= 4; i++) {
      JugadorParImpar jugador = new JugadorParImpar(casino, "Jugador-ParImpar_" + i);
      System.out.println("Jugador-ParImpar_" + i + " apuesta a: " + jugador.getParImparApuesta());
      Thread jugadorThread = new Thread(jugador, "Hilo_JPI_" + i);
      jugadorThread.start();
    }

    for (int i = 1; i <= 4; i++) {
      JugadorMartinGala jugador = new JugadorMartinGala(casino, "Jugador-MartinGala_" + i);
      System.out.println("Jugador-MartinGala_" + i + " apuesta a: ");
      Thread jugadorThread = new Thread(jugador, "Hilo_Martingala_" + i);
      jugadorThread.start();
    }
  }
}