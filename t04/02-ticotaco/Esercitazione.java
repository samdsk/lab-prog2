import java.util.Scanner;

public class Esercitazione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        
        for (int i = 1; i < n+1; i++) {
            if (i%3 == 0 && i%7 == 0) {
                System.out.println("Tico Taco");
            } else if (i%3 == 0 && i%7 != 0) {
                System.out.println("Tico");
            } else if (i%3 != 0 && i%7 == 0) {
                System.out.println("Taco");
            } else {
                System.out.println(i);
            }   
        }
    }
}