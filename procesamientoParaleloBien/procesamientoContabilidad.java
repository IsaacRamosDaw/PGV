package PGV.procesamientoParaleloBien;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class procesamientoContabilidad {
  public static void main(String[] args) throws IOException { 

    String nombreFichero = args[0];    
    String nombreFicheroResultado = args[1];   
    ArrayList<String> cantidades;    
    long total = 0;  

    cantidades = UtilidadesFicheros.getLineasFichero(nombreFichero);    

    for (String lineaCantidad : cantidades) {  
      long cantidad = Long.parseLong(lineaCantidad);     
      total += cantidad; 
    }

    PrintWriter pw = UtilidadesFicheros.getPrintWriter(nombreFicheroResultado);  
    pw.println(total);  
    pw.close();
}

}
