import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProcesoPadre {
  public static void main(String[] args) {
    ProcessBuilder pb;

    // Bucle para lanzar los 5 procesos hijos
    for (int i = 1; i <= 5; i++) {
      ArrayList<String> listaDePalabras = new ArrayList<>();
      try (BufferedReader br = new BufferedReader(new FileReader("datos" + i + ".txt"))) {
        String palabra;

        while ((palabra = br.readLine()) != null) {
          listaDePalabras.add(palabra);
        }

        File currentDir = new File(System.getProperty("user.dir"));
        String datosParaHijo = String.join(",", listaDePalabras);
        String ficheroValor = String.valueOf(i);

        try {
          pb = new ProcessBuilder("java", "ProcesoHijo", datosParaHijo, ficheroValor);

          // Para obtener la consola del hijo
          pb.directory(currentDir);
          pb.inheritIO();

          Process procesoHijo = pb.start();
          procesoHijo.waitFor();

        } catch (IOException | InterruptedException e) {
          System.err.println("Padre ERROR al lanzar/esperar al Proceso Hijo.");
          e.printStackTrace();
        }

      } catch (FileNotFoundException e) {
        System.out.println("No se ha encontrado el fichero: " + "datos" + i + ".txt");
      } catch (IOException e) {
        System.out.println("Error de lectura del fichero: " + "datos" + i + ".txt");
      }
    }

    try (FileWriter fw = new FileWriter("resumen.res")) {
    } catch (IOException e) {
      System.err.println("No se pudo crear el archivo: resumen.res");
      e.printStackTrace();
    }

    int totalVocalesGeneral = 0;

    for (int i = 1; i <= 5; i++) {
      int totalVocalesFichero = 0;
      String listaPalabrasString = "";

      try (BufferedReader br = new BufferedReader(new FileReader("vocales-" + i + ".res"))) {
        String numeroLeido;
        while ((numeroLeido = br.readLine()) != null) {
          totalVocalesFichero = Integer.parseInt(numeroLeido);
          totalVocalesGeneral += totalVocalesFichero;
        }
      } catch (IOException e) {
        throw new RuntimeException("Error al leer vocales-" + i + ".res", e);
      }

      try (BufferedReader br = new BufferedReader(new FileReader("listaPalabras-" + i + ".res"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
          listaPalabrasString = linea;
          
          String[] arrayPalabras = listaPalabrasString.split(",");
          int numeroDePalabras = arrayPalabras.length;
          
          double promedioDeVocales = 0.0;
          if (numeroDePalabras > 0) {
            promedioDeVocales = (double) totalVocalesFichero / numeroDePalabras;
          }

          try (FileWriter fwResumen = new FileWriter("resumen.res", true)) {
            fwResumen.write("--- Fichero " + i + " ---\n");
            fwResumen.write("Lista de palabras del hijo " + i + ":\n");
            fwResumen.write(listaPalabrasString + "\n");
            fwResumen.write("Número de palabras: " + numeroDePalabras + "\n");
            fwResumen.write("Total de vocales: " + totalVocalesFichero + "\n");
            fwResumen.write("Promedio de vocales por palabra: " + String.format("%.2f", promedioDeVocales) + "\n\n");
          } catch (IOException e) {
            throw new RuntimeException("Error al escribir en resumen.res", e);
          }
        }
      } catch (IOException e) {
        throw new RuntimeException("Error al leer listaPalabras-" + i + ".res", e);
      }
    }

    // 4. ESCRIBIR EL TOTAL GENERAL
    try (FileWriter fw = new FileWriter("resumen.res", true)){
      fw.write("==================================\n");
      fw.write("Total de vocales encontradas en todos los ficheros: " + totalVocalesGeneral);
      fw.write("\n==================================\n");
    } catch (Exception e) {
      System.err.println("Error al escribir el total general en resumen.res");
      e.printStackTrace();
    }
  }
}