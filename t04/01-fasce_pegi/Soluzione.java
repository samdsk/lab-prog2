import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        /*
        fino a 6 anni: fascia 3
        da 7 fino a 11 anni: fascia 7
        da 12 fino a 15 anni: fascia 12
        da 16 fino a 17 anni: fascia 16
        da 18 anni in poi: fascia 18
        */
        Scanner s = new Scanner (System.in);
        int eta = s.nextInt();
        s.close();

        int fascia;

        if (eta < 7) {
            fascia = 3;
        } else if (eta < 12) {
            fascia = 7;
        } else if (eta < 16) {
            fascia = 12;
        } else if (eta < 18) {
            fascia = 16;
        } else {
            fascia = 18;
        }

        System.out.println("fascia " + fascia);
    }
}