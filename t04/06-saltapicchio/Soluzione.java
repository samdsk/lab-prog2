import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Scanner s = new Scanner(System.in);
        int[] array = new int[n];
        int[] arraydiff = new int[n];

        //assegnamento da scanner
        for (int i = 0; i < n; i++) {
            array[i] = s.nextInt();
        }
        s.close();

        //caso base
        if (n == 1) {
            System.out.println("saltapicchio");
            return;
        }

        //metto le diff in un array di supporto
        for (int i = 0; i < n-1; i++) {
            if (array[i] > array[i+1]) {
                arraydiff[i] = array[i] - array[i+1];
            } else {
                arraydiff[i] = array[i+1] - array[i];
            }
        }

        for (int i = 1; i < n; i++) {
            int cont = 0;
            for (int j = 0; j < n-1; j++) {
                if (arraydiff[j] > n-1 || arraydiff[j] < 1) {
                    return;
                }
                if (arraydiff[j] == i) {
                    cont++;
                    if (cont > 1) {
                        return;
                    }
                }
            }
        }

        System.out.println("saltapicchio");
        

        /*
        //ARRAYPRINT
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        */
    }
}