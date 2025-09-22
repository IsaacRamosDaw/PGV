package PGV.primeraTarea.sumadorMultihilo;
import java.io.File;

public class LanzadorSumador {
  public void lanzarSumador(
      Integer n1,
      Integer n2, 
      String fichResultado
    ) {
    String clase = "Sumador.java";
    ProcessBuilder pb;
    try {
      pb = new ProcessBuilder(
          "java", 
          clase,
          n1.toString(),
          n2.toString()
        );

      pb.redirectError(new File("errores.txt"));
      pb.redirectOutput(new File(fichResultado));
      pb.start();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    LanzadorSumador l = new LanzadorSumador();
    l.lanzarSumador(1, 5, "result1.txt");
    l.lanzarSumador(6, 10, "result2.txt");
    System.out.println("Ok");
  }
}
