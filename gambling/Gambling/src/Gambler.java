
public class Gambler {
  int saldo = 1000;
  int numeroEscogido;
  String name;
  Banca casino;
  boolean imBroke = false;


  public Gambler(Banca casino, String name){
    this.casino = casino;
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public boolean imBroke(){
    return 0 >= saldo;
  }

  public void ganarApuesta(int cantidad){
    saldo += cantidad;
  }
}
