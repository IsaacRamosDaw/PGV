public class TareaRun implements Runnable {
  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Soy un hilo y esto es lo que hago");
    }
  }
}