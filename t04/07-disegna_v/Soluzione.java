import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        java.util.Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        /*
        
        int[][] frame = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        */
        
        //iterations counter
        for (int i = 0; i < n; i++) {
            //print leftspace
            int spacersx = i;
            while (spacersx > 0) {
                System.out.print(" ");
                spacersx--;
            }
            //print leftstars
            System.out.print("*");
            //print rightspaces
            int spacerdx = 0;
            while (spacerdx < (2*n)-(2*i)-3) {
                System.out.print(" ");
                spacerdx++;
            }
            //print rightstars
            if (i < n-1) {
                System.out.println("*");
            }
        }
    }
}