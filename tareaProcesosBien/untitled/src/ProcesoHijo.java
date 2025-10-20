import java.io.FileWriter;
import java.io.IOException;

public class ProcesoHijo {
  public static void main(String[] args){
    if (args.length == 0) {
      System.err.println("Hijo: Error. No se recibieron argumentos.");
      return;
    }

    String palabrasRecibidas = args[0];
    String procesoValor = args[1];
    String[] arrayPalabras = palabrasRecibidas.split(",");

    int totalVocales = 0;
    int totalMinusculas = 0;

    for (String palabra : arrayPalabras) {
      String palabraMinusculas = palabra.toLowerCase();

      totalMinusculas += contarMinusculas(palabra.trim());
      totalVocales += contarVocales(palabraMinusculas);
    }

    crearFichero(procesoValor, totalVocales, totalMinusculas, arrayPalabras);
  }

  private static int contarMinusculas(String texto) {
    int contador = 0;

    for (char c : texto.toCharArray()) { if (Character.isLowerCase(c)) { contador++; } }

    return contador;
  }

  private static int contarVocales(String texto) {
    int contador = 0;
    String caracteres = "aeiouáéíóúü";

    for (char c : texto.toCharArray()) {if (caracteres.contains(String.valueOf(c))) { contador++; }}

    return contador;
  }

  private static void crearFichero(String numeroFichero, int cantidadVocales, int cantidadMinusculas, String[] listaPalabras){
    try (FileWriter fw = new FileWriter("vocales-" + numeroFichero + ".res")) {
      fw.write(""+cantidadVocales);

    } catch (IOException e) {
      System.err.println("Hijo ERROR: No se pudo escribir el archivo: de vocales " + numeroFichero);
    }

    try (FileWriter fw = new FileWriter("minusculas-" + numeroFichero + ".res")) {
      fw.write(""+cantidadMinusculas);

    } catch (IOException e) {
      System.err.println("Hijo ERROR: No se pudo escribir el archivo de minusculas " + numeroFichero);
    }

    try (FileWriter fw = new FileWriter("listaPalabras-" + String.join(",", numeroFichero) + ".res")) {
      fw.write(""+ String.join(",", listaPalabras));
      
    } catch (IOException e) {
      System.err.println("Hijo ERROR: No se pudo escribir el archivo de listaDePalabras " + numeroFichero);
    }
  }
}



