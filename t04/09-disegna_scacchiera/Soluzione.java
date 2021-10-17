import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        int state = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int z = 0; z < n; z++) {
                        if (state == 0) {
                            System.out.print("-");
                        } else {
                            System.out.print("#");
                        }
                    }
                    if (state == 0) {
                        state = 1;
                    } else {
                        state = 0;
                    }
                }
                System.out.println();
                
            }
            if (state == 0) {
                state = 1;
            } else {
                state = 0;
            }
        }







        /*
        //triplo for
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8*n; j++) {
                for (int k = 0; k < n; k++) {
                    if ((k*8)%n == 0) {
                        if (state == 1) {
                            state = 0;
                        } else {
                            state = 1;
                        }
                    }
                    if (state == 0) {
                        System.out.print("-");
                        cont++;
                    } else {
                        System.out.print("#");
                        cont++;
                    }
                }
                if (cont%8 == 0) {
                    System.out.println();
                }
                

            }
            if (state == 1) {
                state = 0;
            } else {
                state = 1;
            }
        }

        */
    }
}