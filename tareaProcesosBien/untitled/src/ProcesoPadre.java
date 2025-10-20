import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProcesoPadre {
  public static void main(String[] args) {
    ProcessBuilder pb;

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


          //| InterruptedException e

        } catch (IOException | InterruptedException e) {
          System.err.println("Padre ERROR al lanzar/esperar al Proceso Hijo.");
          e.printStackTrace();
        }

      } catch (FileNotFoundException e) {
        System.out.println("No se ha encontrado el fichero: " + "datos" + i);
      } catch (IOException e) {
        System.out.println("Error de lectura del fichero: " + "datos" + i);
      }
    }

    try (FileWriter fw = new FileWriter("resumen.res")) {
    } catch (IOException e) {
      System.err.println("No se pudo crear el archivo: ");
      e.printStackTrace();
    }

    int totalVocales = 0;

    for (int i = 1; i <= 5; i++) {
      int promedioDeVocales = 0;
      String listaPalbras;
      String numeroLeido;

      try (BufferedReader br = new BufferedReader(new FileReader("vocales-" + i + ".res"))) {
        while ((numeroLeido = br.readLine()) != null) {
          totalVocales += Integer.parseInt(numeroLeido);
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      try (BufferedReader br = new BufferedReader(new FileReader("listaPalabras-" + i + ".res"))) {
        while ((listaPalbras = br.readLine()) != null) { 
          FileWriter fwResumen = new FileWriter("resumen.res", true);

          fwResumen.write("Lista de palabras del hijo " + i);
          fwResumen.write("\n");
          fwResumen.write(listaPalbras);
          fwResumen.write("\n");

          fwResumen.close();

        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    try (FileWriter fw = new FileWriter("resumen.res", true)){
      fw.write("Total de vocales encontradas: " + totalVocales);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void escribirResumen(){

  }
}
