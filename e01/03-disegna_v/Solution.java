// Disegna - V
public class Solution {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        //int width = (2*n)-1;
        print_v(n);
    }

    /**
     * Requires: N a positive integer 
     * Effects: Create a shape V of N rows of hight 
     * Modifies: _
     */
    public static void print_v(int n){
        row(0,n,2*n-1);        
    }

    /**
     * Requires: Three positive integers R,N and W
     * Effects: Create the row of shape V of given row, until R equals N
     * Modifes: _
     */
    public static void row(int r, int n, int w){
        if(r==n)return;        
        column(r, w);
        row(++r,n,--w);
    }

    /**
     * Requires: Two positive integers R and Width
     * Effects: Prints columns of given row number R of width Width
     * Modifies: _
     */
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
