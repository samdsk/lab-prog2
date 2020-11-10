import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, i = 0;
        n = s.nextInt();
        s.close();

        System.out.print(n + " ");

        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
                System.out.print(n + " ");
            } else {
                n = n*3 +1;
                System.out.print(n + " ");
            }
            i++;
        }

        System.out.print(i+1);
    }
}