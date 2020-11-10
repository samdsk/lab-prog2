import java.util.Scanner;

public class Soluzione {

    /**
     * Pre-condizioni: state deve essere 0 o 1;
     * Post-condizioni: cambia il valore di state: se state era 0, diventa 1;
     *                                             se state era 1, diventa 0;
     */
    public static int changeState(int state) {
        if (state == 0) {
            state = 1;
        } else {
            state = 0;
        }
        return state;
    }

    /**
     * Post-condizioni: la funzione sceglie un simbolo tra i due proposti in base alla
     *                  variabile state e lo stampa nel flusso di output;
     */
    public static void symbolPrinter(int state) {
        if (state == 0) {
            System.out.print("-");
        } else {
            System.out.print("#");
        }
    }

    /**
     * Post-condizioni: la funziona stampa una scacchiera di n*8 righe e n*8 colonne
     *                  i cui quadrati neri sono '#' e i quadrati bianchi sono '-';
     */
    public static void chessboardPrinter(int n) {
        int state = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < n; j++) {
                //rows printer
                for (int k = 0; k < 8; k++) {
                    for (int z = 0; z < n; z++) {
                        symbolPrinter(state);
                    }
                    state = changeState(state);
                }
                System.out.println();
            }
            state = changeState(state);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        chessboardPrinter(n);
    }
}