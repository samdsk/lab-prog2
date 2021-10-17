import java.util.Scanner;

public class Soluzione {

    /**
     * Post-condizioni: la funzione stampa sul flusso di output ' ' |n-i| volte;
     */
    public static void leftSpacesPrinter(int n, int i) {
        int spacersx = n-i;
        //mi trovo nella prima meta' (in altezza) del rombo
        if (spacersx > 0) {
            while (spacersx > 0) {
                System.out.print(" ");
                spacersx--;
            }
        }
        //mi trovo nella prima meta' (in altezza) del rombo
        else if (spacersx < 0) {
            while (spacersx < 0) {
                System.out.print(" ");
                spacersx++;
            }
        }
    }

    /**
     * Post-condizioni: la funzione stampa sul flusso di output '*' 2*i +1 volte se ci si 
     *                  trova nella prima meta' del rombo (in altezza); se ci si trova 
     *                  nella nella seconda meta' del rombo (in altezza) stampa '*' 
     *                  (2*n) - (2*(i-n)) +1 volte;
     */
    public static void starPrinter(int n, int i) {
        //mi trovo nella prima meta' (in altezza) del rombo
        if (i < n+1) {
            int j = (2*i) +1;
            while (j > 0) {
                System.out.print("*");
                j--;
            }
        }
        //mi trovo nella prima meta' (in altezza) del rombo
        else if (i > n) {
            int j = (2*n) - (2*(i-n)) +1;
            while (j > 0) {
                System.out.print("*");
                j--;
            }
        }
    }

    /**
     * Pre-condizioni: n >= 0;
     * Post-condizioni: La funzione stampa un rombo di diagonale 2n+1;
     */
    public static void diamondPrinter(int n) {
        //stampo spazi sx, poi asterischi
        for (int i = 0; i < (2*n)+1; i++) {
            leftSpacesPrinter(n, i);
            starPrinter(n, i);
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        diamondPrinter(n);
    }
}
