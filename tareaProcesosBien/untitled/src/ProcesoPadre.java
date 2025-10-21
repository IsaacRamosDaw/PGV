import java.io.*;
import java.util.ArrayList;

public class ProcesoPadre {
  public static void main(String[] args) {
    ProcessBuilder pb;

    for (int i = 1; i <= 5; i++) {
      ArrayList<String> listaPalabras = new ArrayList<>();

      try (BufferedReader br = new BufferedReader(new FileReader("datos" + i + ".txt"))) {
        String palabra;

        while ((palabra = br.readLine()) != null) {listaPalabras.add(palabra);}

        String palabrasHijo = String.join(",", listaPalabras);

        pb = new ProcessBuilder("java", "ProcesoHijo", palabrasHijo, String.valueOf(i));
        
        pb.start();

        Process process = pb.start();
        process.waitFor();

      } catch (FileNotFoundException e) {
        System.err.println("No se ha encontrado el fichero: " + "datos" + i + ".txt");
      } catch (IOException e) {
        System.err.println("Error de lectura del fichero: " + "datos" + i + ".txt");
      } catch (InterruptedException e) {
        System.err.println("Error al Iniciar el proceso");
        System.err.println(e);
      }
    }

    int totalVocalesGeneral = 0;

    try (FileWriter fw = new FileWriter("resumen.res")) {} 
    catch (IOException e) {System.err.println("No se pudo crear el archivo: resumen.res");}
    
    for (int i = 1; i <= 5; i++) {
      int totalVocalesFichero = 0;

      try (BufferedReader br = new BufferedReader(new FileReader("vocales-" + i + ".res"))) {
        String numeroLeido;

        while ((numeroLeido = br.readLine()) != null) {
          totalVocalesFichero = Integer.parseInt(numeroLeido);
          totalVocalesGeneral += totalVocalesFichero;
        }

      } catch (IOException e) {
        System.err.println("Error al leer vocales-" + i + ".res");
        System.err.println(e);
      }


      try (BufferedReader br = new BufferedReader(new FileReader("listaPalabras-" + i + ".res"))) {
        String listaPalabrasString;

        while ((listaPalabrasString = br.readLine()) != null) {
          String[] arrayPalabras = listaPalabrasString.split(",");
          int numeroDePalabras = arrayPalabras.length;

          double promedioDeVocales = 0.0;

          if (numeroDePalabras > 0) {promedioDeVocales = (double) totalVocalesFichero / numeroDePalabras;}

          
          try (FileWriter fwResumen = new FileWriter("resumen.res", true)) {
            fwResumen.write("Lista de palabras del hijo " + i + ":\n");
            fwResumen.write(listaPalabrasString + "\n");
            fwResumen.write("Total de vocales: " + totalVocalesFichero + "\n");
            fwResumen.write("Promedio de vocales por fichero: " + promedioDeVocales + "\n\n");

          } catch (IOException e) {
            System.err.println("Error al escribir en resumen.res");
            System.err.println(e);
          }
        }
        
      } catch (IOException e) {
        System.err.println("Error al escribir en resumen.res");
        System.err.println(e);
      }
    }

    try (FileWriter fw = new FileWriter("resumen.res", true)) {
      fw.write("Total de vocales encontradas en todos los ficheros: " + totalVocalesGeneral);

    } catch (Exception e) {
      System.err.println("Error al escribir el total general en resumen.res");
      System.err.println(e);
    }
  }
}