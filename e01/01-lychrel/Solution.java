import java.nio.ReadOnlyBufferException;
import java.util.Scanner;

// Numeri di Lychrel


class Solution{
    public static void main(String[] args) {  
        Scanner s = new Scanner(System.in);
        long input = s.nextLong(); 
        s.close();
        lychrel(input);
    }
    
    /**
     * Requires: Any positive number as a string 
     * Modifies: Modifies System.out
     * Effects: Prints to System.out the Lychrel sequence of given number S
     */
    public static void lychrel(long input){
        long l = input;
        long n = 0L;        

        while(true){
            n = reverse(l);
            System.out.println(l);
            if (n==l) break;
            l += n;
           
        }

    }
    /**
     * Requires: Any string != null
     * Modifies: _
     * Effects: Returns a reverse order string of input S
     */

    /* 
    public static long swap(String s){        
        int l = s.length();        
        if (l<=1) return s;    
        return swap(s.substring(1)) + s.charAt(0);
    }
    */



    /**
     * Requires: Any long >= 0
     * Modifies: _
     * Effects: Returns a long which is obtained by reversing input
     *          Es. 1234L -> 4321L
     */
    public static long reverse(long input){
        long output = reverse(input,lengthOfInput(input)-1);
        //System.out.println("output: "+output);
        return output;
    }

    /**
     * Requires: Any long >= 0
     * Modifies: _
     * Effects: Returns the number of digits of input interpreted as a decimal number
     *          Es. 1234L -> 4
     */

    public static int lengthOfInput(long input){
        if(input == 0) return 0;
        return 1+lengthOfInput(input/10);
    }

    /**
     * Requires: Any long >= 0 and integer i >= 0
     * Modifies: _
     * Effects: Returns a long which is obtained by reversing input
     *          Es. 1234L -> 4321L
     */

    public static long reverse(long input, int i){
        //System.out.println("input:"+input+" i:"+i);
        if(input==0 || i<0) return 0;       
        return input%10 * (long) Math.pow(10, i) + reverse(input/10,--i);
    }
}