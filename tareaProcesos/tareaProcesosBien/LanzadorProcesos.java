package PGV.tareaProcesos.tareaProcesosBien;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzadorProcesos {

  // Lista de nombres de ficheros de entrada
  // private static final String[] FICHEROS_ENTRADA = { "datos1.txt", "datos2.txt", "datos3.txt" };
  private static final String[] FICHEROS_ENTRADA = { "datos1.txt" };

  public static void main(String[] args) {
    ArrayList<String> palabrasDelFichero = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(FICHEROS_ENTRADA[0]))) {
      String linea;
      while ((linea = br.readLine()) != null) {
        linea = linea.trim();
        if (linea.isEmpty()) {continue;}
        palabrasDelFichero.add(linea);
      }

    }

    System.out.println(palabrasDelFichero.get(0));
  }
  catch (IOException e) {System.err.println("ERROR de lectura/escritura: " + e.getMessage());}
}