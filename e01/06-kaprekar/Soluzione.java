import java.util.Scanner;

public class Soluzione {


    /**
     * Pre-condizioni: 1000 <= n < 10000, n ha cifre tutte diverse;
     * Effetti collaterali: n viene modificata (le sue cifre vengono ordinate);
     * Post-condizioni: la funziona restituisce un valore n con le stesse cifre del valore
     *                  di n in ingresso, ma ordinate;
     */
    public static int sortNumber(int n) {
        int[] digits = intToArray(n);
        //controlli e ordinamento
        if (digits[0] > digits [1]) {
            int temp = digits[0];
            digits[0] = digits[1];
            digits[1] = temp;
        }
        if (digits[2] > digits [3]) {
            int temp = digits[2];
            digits[2] = digits[3];
            digits[3] = temp;
        }
        if (digits[0] > digits [2]) {
            int temp = digits[0];
            digits[0] = digits[2];
            digits[2] = temp;
        }
        if (digits[1] > digits [3]) {
            int temp = digits[1];
            digits[1] = digits[3];
            digits[3] = temp;
        }
        if (digits[1] > digits [2]) {
            int temp = digits[1];
            digits[1] = digits[2];
            digits[2] = temp;
        }
        n = arrayToInt(digits);
        return n;
    }

    /**
     * Pre-condizioni: 1000 <= n < 10000;
     * Effetti collaterali: n sara' composto dalle stesse cifre, ma in ordine decrescente;
     * Post-condizioni: la funzione ordina le cifre del numero n in ordine decrescente;
     */
    public static int reverseNumber(int n) {
        int[] digits = intToArray(n);
        //controlli e ordinamento
        if (digits[0] < digits [1]) {
            int temp = digits[0];
            digits[0] = digits[1];
            digits[1] = temp;
        }
        if (digits[2] < digits [3]) {
            int temp = digits[2];
            digits[2] = digits[3];
            digits[3] = temp;
        }
        if (digits[0] < digits [2]) {
            int temp = digits[0];
            digits[0] = digits[2];
            digits[2] = temp;
        }
        if (digits[1] < digits [3]) {
            int temp = digits[1];
            digits[1] = digits[3];
            digits[3] = temp;
        }
        if (digits[1] < digits [2]) {
            int temp = digits[1];
            digits[1] = digits[2];
            digits[2] = temp;
        }
        n = arrayToInt(digits);
        return n;
    }

    /**
     * Pre-condizioni: 1000 <= n < 10000;
     * Post-condizioni: la funziona restituisce un array di 4 interi contenenete i valori
     *                  delle singole cifre del numero.
     */
    public static int[] intToArray(int n) {
        int[] digits = {0, 0, 0, 0};
        for (int i = 3; i > -1; i--) {
            digits[i] = n%10;
            n = n/10;
        }
        return digits;
    }

    /**
     * Pre-condizioni: digits e' di 4 elementi;
     * Post-condizioni: la funziona restituisce un intero equivalente ai valori degli elementi
     *                  dell'array digits con la giusta notazione decimale;
     */
    public static int arrayToInt(int[] digits) {
        int n = 0;
        int k = 0;
        for (int i = 3; i > -1; i--) {
            int powerIterator = k;
            int power = 1;
            while (powerIterator > 0) {
                power *= 10;
                powerIterator--;
            }
            n = n + (digits[i] * power);
            k++;
        }
        return n;
    }

    /**
     * Pre-requisiti: 1000 <= n < 10000;
     * Effetti collaterali: n viene modificato nel successivo numero della sequenza di
     *                      Kaprecar;
     * Post-condizioni: la funzione calcola il numero successivo ad n della sequenza di
     *                  Kaprecar;
     */
    public static int kaprekar(int n) {
        int nsorted = sortNumber(n);
        int nreversed = reverseNumber(n);
        n = nreversed - nsorted;
        return n;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        if (n == 6174) {
            System.out.println(n);
            return;
        }

        while (kaprekar(n) != 6174) {
            System.out.println(n);
            n = kaprekar(n);
        }
        System.out.println(n);
        System.out.println("6174");
    }
}

/*
int digits[] = digitsArrayCreator(n);
        for (int i = 0; i < 4; i++) {
            System.out.print(digits[i]);
        }
*/