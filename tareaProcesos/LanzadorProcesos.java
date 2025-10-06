package PGV.tareaProcesos;
import java.util.Arrays;

public class LanzadorProcesos {

  public static void main(String[] args) {
    String[] ficheros = {"datos1.txt", "datos2.txt", "datos3.txt", "datos4.txt", "datos5.txt"};
    int ficheroNumero = 1;

    for (String ficheroLectura : ficheros) {
      String fVocales = "vocales-" + ficheroNumero + ".res";
      String fMinusculas = "minusculas-" + ficheroNumero + ".res";

      System.out.println("\nLlamando a ProcesoFichero para: " + ficheroLectura);
      System.out.println("  Guardando en: " + fVocales + " y " + fMinusculas);

      ProcesoFichero.ejecutar(ficheroLectura, fVocales, fMinusculas);
      ficheroNumero++;
    }
  }
}
