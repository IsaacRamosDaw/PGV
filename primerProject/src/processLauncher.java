public class processLauncher {
  public void execute(String ruta){

    ProcessBuilder pb;
    try {
      pb = new ProcessBuilder(ruta);
      pb.start();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}