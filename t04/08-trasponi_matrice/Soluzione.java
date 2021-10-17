import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);

        int[][] A = new int[N][M];

        //scope di r e c solo nei for annidati grazie alla dichiarazione
        //nel blocco
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                A[r][c] = s.nextInt();
            }
        }

        //matrice trasposta
        int[][] T = new int[M][N];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                T[r][c] = A[c][r];
            }
        }


        //print
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(T[r][c] + " ");
            }
            System.out.println();
        }

        s.close();
    }
}