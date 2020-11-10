import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a, b, c;
        int n = s.nextInt();
        s.close();

        //inizializzo a 2 perche gli 1 sarano sempre veri
        for (a = 1; a < n; a++) {
            for (b = 1; b < n; b++) {
                int prod = (a*b)%9;
                if (prod > 9) {
                    prod = prod%9;
                }
                for (c = 1; c < n; c++) {
                    if (prod == c%9 && a*b != c) {
                        System.out.println(a + " " + b + " " + c);
                    }
                    
                }
            }
        }
    }
}


/*a = a;
                    if (a > 9) {
                        a = a%9;
                    }
                    b = b;
                    if (b > 9) {
                        b = b%9;
                    }
                    int prod = a*b;
                    if (prod > 9) {
                        prod = prod%9;
                    }
                        for (int k = 0; k < n; k++) {
                    c = k;
                    if (a == 2 && b == 2 && k == 4) {
                        continue;
                    }
                    if (prod == c && c < n && c%9 == 0) {
                        //System.out.println("a: " + a + " b: " + b + " prod: " + prod);
                        System.out.println(a + " " + b + " " + c);
                        break;
                    }
                    */