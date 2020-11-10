import java.util.Scanner;

public class Soluzione {

    /**
     * Pre-condizioni: n e' un numero positivo (ma in realta' non penso serva);
     * Post-condizioni: il metodo restituisce la somma delle cifre di n;
     */
    /*
    public static int sommaCifre(int n) {
        int somma = 0;
        while (n > 0) {
            somma = somma + n%10;
            n = n/10;
        }
        return somma;
    }
    */

    /**
     * Pre-condizioni: a e b sono due numeri positivi (se inserisco 0 succede un bordello
     *                 ma e' comunque come dovrebbe fare il programma no?);
     * Post-condizioni: il metodo restituisce un intero corrispondente alla somma delle
     *                  cifre del prodotto di a e b;
     */
    public static int prodottoSingoleCifre(int a, int b) {
        int prod = (a*b)%9;
        while (prod > 9) {
            prod = prod%9;
        }
        return prod;
    }

    /**
     * Pre-condizioni: a e b sono due numeri positivi;
     * Post-condizioni: il metodo restituisce la somma delle cifre del risultato a*b;
     */
    /*
    public static int sommaCifreRisultato(int a, int b) {
        int somma = a*b;
        int res = somma%9;
        return res;
    }
    */

    /**
     * Pre-condizioni: a e b sono due numeri positivi;
     * Post-condizioni: il metodo stampa sul flusso di uscita la terna a b c se la prova 
     * del nove funziona per i valori inseriti a e b, ma a*b != c;
     */
    public static void provaDelNove(int a, int b, int c) {
        int prod = prodottoSingoleCifre(a, b);
        if (prod == c%9 && a*b != c) {
            ternaryPrinter(a, b , c);
        }
    }

    /**
     * Post-condizioni: la funzione stampa nel flusso di output la terna a b c;
     */
    public static void ternaryPrinter(int a, int b, int c) {
        System.out.println(a + " " + b + " " + c);
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        int a, b, c;

        for (a = 1; a < n; a++) {
            for (b = 1; b < n; b++) {
                for (c = 1; c < n; c++) {
                    provaDelNove(a, b, c);
                }
            }
        }
        
    }
}
