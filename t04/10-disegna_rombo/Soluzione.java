import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        int spacersx = n;

        //stampo spazi sx, poi asterischi

        for (int i = 0; i < (2*n)+1; i++) {
            spacersx = n-i;
            if (spacersx > 0) {
                while (spacersx > 0) {
                    System.out.print(" ");
                    spacersx--;
                }
            } else if (spacersx < 0) {
                while (spacersx < 0) {
                    System.out.print(" ");
                    spacersx++;
                }
            }
            if (i < n+1) {
                int j = (2*i) +1;
                while (j > 0) {
                    System.out.print("*");
                    j--;
                }
            } else if (i > n) {
                int j = (2*n) - (2*(i-n)) +1;
                while (j > 0) {
                    System.out.print("*");
                    j--;
                }
            }
            System.out.println();
        }
    }
}

/*

for (int i = 0; i < (2*n)+1; i++) {
            spacersx = n-i;
            if (spacersx > 0) {
                while (spacersx > 0) {
                    System.out.print(" ");
                    spacersx--;
                }
            } else if (spacersx < 0) {
                while (spacersx < 0) {
                    System.out.print(" ");
                    spacersx++;
                }
            }
            for (int j = 0; (2*n)+j < (2*n)+1; j++) {
                for (int k = 1; k < (2*n)+1; k+2) {
                    if (k > n) {
                        while 

                    }
                }
                System.out.print("*");
            }
            System.out.println();
        }



        for (int i = 0; i < (2*n)+1; i++) {
            spacersx = n-i;
            if (spacersx > 0) {
                while (spacersx > 0) {
                    System.out.print(" ");
                    spacersx--;
                }
            } else if (spacersx < 0) {
                while (spacersx < 0) {
                    System.out.print(" ");
                    spacersx++;
                }
            }
            for (int j = 0; (2*n)+j < (2*n)+1; j++) {
                

                System.out.println();
            }
        }





        } else if (i == n+1) {
                int j = (2*n)+1;
                while (j > 0) {
                    System.out.print("*");
                    j--;
                }




*/