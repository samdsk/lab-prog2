import java.util.Scanner;

public class Soluzione {

    /**
     * Post-condizioni: stampa nel flusso di output un '*';
     */
    public static void leftStarPrinter() {
        System.out.print("*");
    }

    /**
     * Post-condizioni: stampa nel flusso di output un '*' seguito da un line new;
     */
    public static void rightStarPrinter() {
        System.out.println("*");
    }

    /**
     * Post-condizioni: stampa nel flusso di output i volte ' ';
     */
    public static void leftSpacesPrinter(int i) {
        int spacersx = i;
        while (spacersx > 0) {
            System.out.print(" ");
            spacersx--;
        }
    }

    /**
     * Post-condizioni: stampa nel flusso di output (spacerdx < (2*n)-(2*i)-3) volte ' ';
     */
    public static void rightSpacesPrinter(int n, int i) {
        int spacerdx = 0;
        while (spacerdx < (2*n)-(2*i)-3) {
            System.out.print(" ");
            spacerdx++;
        }
    }

    /**
     * Post-condizioni: se n è negativo, la funziona non stampa nulla;
     *                  altrimenti stampa nel flusso di output una V di dimensione n;
     */
    public static void vPrinter(int n) {
        for (int i = 0; i < n; i++) {
            //print leftspace
            leftSpacesPrinter(i);
            //print leftstars
            leftStarPrinter();
            //print rightspaces
            rightSpacesPrinter(n, i);
            //print rightstars
            if (i < n-1) {
                rightStarPrinter();
            }
        }
    }
    
    
    public static void main(String[] args) {
        java.util.Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        
        vPrinter(n);
    }
}