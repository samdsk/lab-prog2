// Disegna - Rombo
public class Solution {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);   
        print(n);
    }

    /**
     * Requires: N a positive integer
     * Modifies: System.out
     * Effects: Builds a diamond shape having a width of 2N+1
     */
    public static void print(int n){
        row_a(n,n+1);
        row_b(0,2*n+1);              
    }
    /**
     * Requires: N and W are positive integers
     * Modifies: System.out
     * Effects: Builds N rows. A row is made of N-1 spaces 
     *          followed by W-N times the charactor "*"
     */
    public static void row_a(int n, int w){
        if(n==0) return;        
        column(n, w);
        row_a(n-1,w+1);
    }
    /**
     * Requires: N and W are positive integers
     * Modifies: System.out
     * Effects: Builds N+1 rows. A row is made of N-1 spaces 
     *          followed by W-N times the charactor "*"
     */
    public static void row_b(int n, int w){
        if(n>w) return;        
        column(n, w);
        row_b(n+1,w-1);
    }
    /**
     * Requires: R and W are positive integers
     * Modifies: System.out
     * Effects: prints columns of row R of width Width.
     *          prints a row to system.out N spaces followed by W-R times the charactor "*"
     */
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
