import java.util.Iterator;
import java.util.Scanner;


public class TestRunner {

  public static void main(String[] args) {
    Poly p = new Poly();
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt())
        p = p.add(new Poly(s.nextInt(), s.nextInt()));
    }
    Iterator<Integer> it = p.degrees();
    while (it.hasNext())
      System.out.println(it.next());
  }
}
