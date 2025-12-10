package gamblingRedux;

// Clase que representa a un jugador
public class Gambler {
  // Variables para generar un jugador
  int saldo = 1000;
  int numeroEscogido;
  String name;
  Banca casino;

  // Constructor
  public Gambler(Banca casino, String name) {
    this.casino = casino;
    this.name = name;
  }

  // Método que se encarga de verificar si el jugador se ha quedado sin dinero
  public boolean imBroke() { return 0 >= saldo;}

  // Método que se encarga de hacer ganar dinero al jugador
  public void ganarApuesta(int cantidad) { saldo += cantidad; }
}
