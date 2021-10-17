// Disegna - Rombo
public class Solution {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);   
        print(n);
    }

    public static void print(int n){
        row_a(n,n+1);
        row_b(0,2*n+1);              
    }

    public static void row_a(int n, int w){
        if(n==0) return;        
        column(n, w);
        row_a(n-1,w+1);
    }

    public static void row_b(int n, int w){
        if(n>w) return;        
        column(n, w);
        row_b(n+1,w-1);
    }

    public static void column(int r, int width){
        for(int c=0;c<=width;c++){
            if((c>=r)&&(c<width))
                System.out.print("*");
            else
                System.out.print(" ");
        }
        System.out.println();
    }
}
