import java.io.File;

public class primerProceso {
  public static void main(String[] args) {

    // create the command
    String cmd = "tasklist";

    try {
      ProcessBuilder pb = new ProcessBuilder(cmd);
      // redirect the output to a new file named output.txt
      pb.redirectOutput(new File("output.txt"));

      // 
      Process p = pb.start();
      int exitCode = p.waitFor();

      System.out.println("Código de salida: " + exitCode);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
