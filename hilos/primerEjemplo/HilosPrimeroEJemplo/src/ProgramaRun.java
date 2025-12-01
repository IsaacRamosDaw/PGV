public class ProgramaRun {
  public static void main(String args[]) {
    Thread tarea = new Thread(new TareaRun());
    tarea.start();

    try {
      System.out.println("Yo soy el hilo principal y sigo haciendo mi trabajo");
      tarea.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Que la linea de debajo se ejecute al principio o final
    // es casualidad
    System.out.println("Fin del hilo principal");
  }
}
