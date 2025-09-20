package PGV.primeraTarea;

public class Main {
    public static void main(String[] args){
    Sumador s = new Sumador();

    int n1 = Integer.parseInt(args[0]);
    int n2 = Integer.parseInt(args[1]);

    System.out.println(s.sumar(n1,n2));
  }
}
