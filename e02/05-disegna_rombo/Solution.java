// Disegna - Rombo
public class Solution {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);   
        try{
            print(n);
        }catch(IllegalArgumentException iae){
            System.out.println(iae.toString());;
        }
    }

    /**
     * Requires: _ //N a positive integer
     * Modifies: System.out
     * Effects: Builds a diamond shape having a width of 2N+1
     *          Throws IllegalArgumentException if input N is negative
     */
    public static void print(int n) throws IllegalArgumentException{
        if(n<0) throw new IllegalArgumentException("Input must be positive.");
        row_a(n,n+1);
        row_b(0,2*n+1);              
    }
    /**
     * Requires: _ //N and W are positive integers
     * Modifies: System.out
     * Effects: Builds N rows. A row is made of N-1 spaces 
     *          followed by W-N times the charactor "*"
     *          Throws IllegalArgumentException if input N or W is negative
     */
    public static void row_a(int n, int w)throws IllegalArgumentException{
        if(n<0||w<0) throw new IllegalArgumentException("row_a - Inputs must be positive.");
        if(n==0) return;        
        column(n, w);
        row_a(n-1,w+1);
    }
    /**
     * Requires: _ //N and W are positive integers
     * Modifies: System.out
     * Effects: Builds N+1 rows. A row is made of N-1 spaces 
     *          followed by W-N times the charactor "*"
     *          Throws IllegalArgumentException if input N or W is negative
     */
    public static void row_b(int n, int w)throws IllegalArgumentException{
        if(n<0||w<0) throw new IllegalArgumentException("row_b - Inputs must be positive.");
        if(n>w) return;        
        column(n, w);
        row_b(n+1,w-1);
    }
    /**
     * Requires: _ //R and W are positive integers
     * Modifies: System.out
     * Effects: prints columns of row R of width W.
     *          prints a row to system.out N spaces followed by W-R times the charactor "*"
     *          Throws IllegalArgumentException if input R or W is negative
     */
    public static void column(int r, int w)throws IllegalArgumentException{
        if(r<0||w<0) throw new IllegalArgumentException("columns - Inputs must be positive.");
        for(int c=0;c<=w;c++){
            if((c>=r)&&(c<w))
                System.out.print("*");
            else
                System.out.print(" ");
        }
        System.out.println();
    }
}
