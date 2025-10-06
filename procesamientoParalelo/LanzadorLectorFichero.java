package PGV.procesamientoParalelo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LanzadorLectorFichero {
  public static void main(String[] args) {
    String rutaInformatica = "D:/isaac/DAM/PGV/procesamientoParalelo/informatica.txt";
    String rutaComercio = "D:/isaac/DAM/PGV/procesamientoParalelo/comercio.txt";
    String rutaGerencia = "D:/isaac/DAM/PGV/procesamientoParalelo/gerencia.txt";
    String rutaContabilidad = "D:/isaac/DAM/PGV/procesamientoParalelo/contabilidad.txt";
    String rutaRRHH = "D:/isaac/DAM/PGV/procesamientoParalelo/rrhh.txt";

    ExecutorService executor = Executors.newFixedThreadPool(5);

    Runnable tareaInformatica = new LectorDeFichero(rutaInformatica);
    executor.submit(tareaInformatica);

    Runnable tareaComercio = new LectorDeFichero(rutaComercio);
    executor.submit(tareaComercio);

    Runnable tareaGerencia = new LectorDeFichero(rutaGerencia);
    executor.submit(tareaGerencia);

    Runnable tareaContabilidad = new LectorDeFichero(rutaContabilidad);
    executor.submit(tareaContabilidad);

    Runnable tareaRRHH = new LectorDeFichero(rutaRRHH);
    executor.submit(tareaRRHH);

    executor.shutdown();

    try {
      boolean terminado = executor.awaitTermination(60, TimeUnit.SECONDS);

      if (!terminado) {
        System.out.println("\nSe agotó el tiempo de espera. Algunos hilos podrían no haber terminado.");
      }

    } catch (InterruptedException e) {
      System.err.println("El programa principal fue interrumpido mientras esperaba.");
      executor.shutdownNow();
    }
  }
}
