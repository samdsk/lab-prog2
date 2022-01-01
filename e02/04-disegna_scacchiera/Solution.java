//Scaccheria
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
     * Effects: Builds the chessboard with a square size of N
     *          Throws IllegalArgumentException if N is negative
     */
    public static void print(int n){    
        if(n<0) throw new IllegalArgumentException("N must be a positive number.");    
        row(0,n);
    }

    /**
     * Requires: _ //positive integers for R and N
     * Modifies: System.out
     * Effects: Builds the row number R of chessboard, containing squares of size N
     *          Throws IllegalArgumentException if R or N is negative
     */
    public static void row(int r, int n) throws IllegalArgumentException{
        if(r<0 || n<0) throw new IllegalArgumentException("row - Inputs must be positive.");
        if(r==8) return;
        columns(r,n);
        row(r+1,n);
    }

    /**
     * Requires: _ //row number R>=0 and square size of N
     * Modifies: System.out
     * Effects: Prints columns of row R with square size N. 
     *          Throws IllegalArgumentException if R or N is negative
     */
    public static void columns(int r, int n) throws IllegalArgumentException{
        if(r<0 || n<0) throw new IllegalArgumentException("columns - Inputs must be positive.");
        String dash = "-";
        String hash = "#";
        String s = "";

        for(int i=0;i<8;i++){ 

            if(r%2==0){
                if((i%2==0))              
                    s += dash.repeat(n);
                else              
                    s += hash.repeat(n);
                
            }else{
                if((i%2==0))             
                    s += hash.repeat(n);
                else              
                    s += dash.repeat(n);                
            }  
        }
                
        s += "\n" + s.repeat(n-1);
        System.out.print(s);
    }
}
