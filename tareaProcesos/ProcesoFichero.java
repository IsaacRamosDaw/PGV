package PGV.tareaProcesosSinProceso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProcesoFichero {
  public static void ejecutar(String ficheroLectura, String ficheroVocales, String ficheroMinusculas) {

    ArrayList<String> palabrasDelFichero = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ficheroLectura))) {
      String linea;
      while ((linea = br.readLine()) != null) {
        linea = linea.trim();
        if (linea.isEmpty()) {continue;}

        palabrasDelFichero.add(linea);
      }

      if (!palabrasDelFichero.isEmpty()) {procesarPalabras(palabrasDelFichero, ficheroVocales, ficheroMinusculas);} 
      else {System.out.println("El archivo no contiene palabras para procesar.");}

    } 
    catch (IOException e) {System.err.println("ERROR de lectura/escritura: " + e.getMessage());}
  }

  public static void procesarPalabras(ArrayList<String> palabras, String ficheroVocales, String ficheroMinusculas) {
    int contadorVocales = 0;
    int contadorMinusculas = 0;

    String vocales = "aeiouáéíóúü";

    for (String palabra : palabras) {

      for (char caracter : palabra.toCharArray()) {

        if (vocales.indexOf(Character.toLowerCase(caracter)) != -1) {
          contadorVocales++;
        }

        if (Character.isLowerCase(caracter)) {
          contadorMinusculas++;
        }
      }
    }

    imprimirVocales(contadorVocales, ficheroVocales);
    imprimirMinusculas(contadorMinusculas, ficheroMinusculas);
  }

  public static void imprimirVocales(int vocales, String fichero) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
      bw.write(String.valueOf(vocales));
    } catch (IOException e) {
      System.err.println("ERROR al escribir vocales: " + e.getMessage());
    }
  }

  public static void imprimirMinusculas(int minusculas, String fichero) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
      bw.write(String.valueOf(minusculas));
    } catch (IOException e) {
      System.err.println("ERROR al escribir minúsculas: " + e.getMessage());
    }
  }
}
