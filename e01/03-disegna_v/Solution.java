// Disegna - V
public class Solution {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        //int width = (2*n)-1;

        print_v(n);

/*
        for(int r=0;r<n;r++){
            for(int c=0;c<width;c++){
                if((c==r) || (c==width-1)) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
            width--;
        }
*/
    }

    public static void print_v(int n){
        row(0,n,2*n-1);        
    }

    public static void row(int r, int n, int w){
        if(r==n)return;
        
        column(r, w);
        row(++r,n,--w);

    }

    public static void column(int r, int width){
        for(int c=0;c<width;c++){
            if((c==r)||(c==width-1))
                System.out.print("*");
            else
                System.out.print(" ");
        }
        System.out.println();
    }
}
