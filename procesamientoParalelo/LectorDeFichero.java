package PGV.procesamientoParalelo;

import java.io.*;

public class LectorDeFichero implements Runnable {
  private final String rutaArchivo;

  public LectorDeFichero(String rutaArchivo) {
    this.rutaArchivo = rutaArchivo;
  }

  @Override
  public void run() {
    String nombreFichero = new File(rutaArchivo).getName();
    long sumaTotal = 0;
    BufferedReader br = null;

    try {
      FileReader fr = new FileReader(new File(rutaArchivo));
      String linea;
      br = new BufferedReader(fr);

      while ((linea = br.readLine()) != null) {
        try {
          long cantidad = Long.parseLong(linea.trim());
          sumaTotal += cantidad;
        } catch (Exception e) {
          System.err.println("¡Advertencia del Hilo " + Thread.currentThread().getName()
              + "! Línea no es un número válido y será ignorada: " + linea);
        }
      }
      System.out.println("SUMA TOTAL: " + sumaTotal + " Del fichero " + nombreFichero);
    } catch (FileNotFoundException e) {
      System.err.println(
          "¡Error del Hilo " + Thread.currentThread().getName() + "! Archivo no encontrado en: " + rutaArchivo);

    } catch (IOException e) {
      System.err
          .println("Ocurrió un error de I/O en el Hilo " + Thread.currentThread().getName() + ": " + e.getMessage());

    } finally {
      try {
        if (br != null) {
          br.close();
        }
      } catch (IOException ex) {
        System.err.println("Error al cerrar el recurso: " + ex.getMessage());
      }
    }
  }
}
