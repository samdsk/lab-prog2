// Disegna - V
public class Solution {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        //int width = (2*n)-1;
        try{
            print_v(n);
        }catch(IllegalArgumentException iae){
            System.out.println(iae.toString());
        }
    }

    /**
     * Requires: N a positive integer 
     * Effects: Create a shape V of N rows of hight 
     *          Throws IllegalArgumentException if inputN is negative
     * Modifies: _
     */
    public static void print_v(int n) throws IllegalArgumentException{
        if(n<0) throw new IllegalArgumentException("Input must be positive.");
        row(0,n,2*n-1);        
    }

    /**
     * Requires: _ //Three positive integers R,N and W
     * Effects: Create the row of shape V of given row, until R equals N
     *          Throw IllegalArgumentException if all three inputs are not positive
     * Modifes: _
     */
    public static void row(int r, int n, int w) throws IllegalArgumentException{
        if(r<0 || n<0 || w<0) throw new IllegalArgumentException("All three inputs must be positive.");
        if(r==n)return;        
        column(r, w);
        row(++r,n,--w);
    }

    /**
     * Requires: _ //Two positive integers R and Width
     * Effects: Prints columns of chessboard for given row number R of width Width
     *          Throws illegalArgumentException both inputs are not positive
     * Modifies: System.out
     */
    public static void column(int r, int width) throws IllegalArgumentException{
        if(r<0 || width<0) throw new IllegalArgumentException("Input R and Width must be positive.");
        for(int c=0;c<width;c++){
            if((c==r)||(c==width-1))
                System.out.print("*");
            else
                System.out.print(" ");
        }
        System.out.println();
    }
}
