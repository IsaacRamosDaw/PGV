public static void main(String[] args){
  String ruta =
          "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

  processLauncher lp = new processLauncher();
  lp.execute(ruta);
  System.out.println("Finalizado");
}