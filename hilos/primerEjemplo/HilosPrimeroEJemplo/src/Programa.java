public class Programa {
  public static void main(String args[]) {
    Tarea tarea = new Tarea();
    tarea.start();
    System.out.println("Yo soy el hilo principal y sigo haciendo mi trabajo");
    try {
      tarea.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    // Que la linea de debajo se ejecute al principio o final
    // es casualidad
    System.out.println("Fin del hilo principal");
  }
}