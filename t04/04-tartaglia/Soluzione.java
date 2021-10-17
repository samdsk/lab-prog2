import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        
        int[][] numeri = new int[n+2][n+2];            

        numeri[0][0] = 1;
        numeri[1][0] = 1;
        numeri[1][1] = 1;

        for (int i = 2; i < n+2; i++) {
            for (int j = 2; j < n+2; j++) {
                if (j > i) {
                    break;
                }
                //vorrei che triangolo facesse questo
                numeri[i][j] = numeri[i-1][j-1] + numeri[i-1][j];
                //numeri[i][j] = triangolo[i][j];
            }
        }
        //numeri[3][3] = triangolo(2,1);
        for (int j = 2; j < n+2; j++) {
            int row = n+1;
            System.out.print(numeri[row][j] + " ");
        }

        /*
        TARTAGLIA PRINTER
        
        for (int i = 2; i < n+2; i++) {
            for (int j = 2; j < n+2; j++) {
                if (j > i) {
                    break;
                }
                numeri[i][j] = numeri[i-1][j-1] + numeri[i-1][j];
                //triangolo()
                System.out.print(numeri[i][j] + " ");
            }
            System.out.println();
        }
        */
    }
}

