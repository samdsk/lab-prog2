import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        IntSet s = new IntSet();

        Scanner scan = new Scanner(System.in);

        /*
        for (int i = 0; i < 10; i++) {
            s.insert(i);
        }

        System.out.println(s.toString());

        for (int i = 11; i > 7; i--) {
            s.remove(i);
        }

        //quando viene chiamata una println viene effettuato solitamente il 
        //toString di quell'oggetto
        System.out.println(s.toString());
        */

        int i = 0;

        while (scan.hasNextInt()) {
            s.insert(scan.nextInt());
        }

        System.out.println(s.size());
    
    }
}

//javac *.java
//java test