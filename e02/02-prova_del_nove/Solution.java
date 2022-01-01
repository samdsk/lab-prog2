//prova del nove
public class Solution {

    /**
     * Requires: a positive number N as a string
     * Effects: Prints to system.out triplet (A B C) of possible combinations which confirms "la prova del nove", 
     *          where A,B,C are positive, less than N and A*B != C.
     *          
     * Modifies: System.out
     */

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        try{
            for(int i=1;i<n;i++){            
                for(int j=1;j<n;j++){
                    for(int c=1;c<n;c++){
                        boolean check = provaDelNove(new int[]{i,j,c});
                        if(check && (c!=i*j)) System.out.printf("%d %d %d%n",i,j,c);
                    }
                }
            }
        }catch(IllegalArgumentException iae){
            System.out.println(iae.toString());
        }
        
    }
    /** 
     * Requires: _ //An array of 3 positive integers  
     *  Effects: Returns true if it satisfies "prova del nove" otherwise false,
     *           throws an IllegalArgumentException if input array doesn't contain 3 elements
     */
    public static boolean provaDelNove(int[] n) throws IllegalArgumentException {
        if(n.length!=3) throw new IllegalArgumentException("Array must have 3 numbers.");
        int a = provaDelNoveHelp2(n[0]);
        int b = provaDelNoveHelp2(n[1]);
        int c = provaDelNoveHelp2(n[2]);
        int m = provaDelNoveHelp2(a*b);

        return (m==c);
    }

    /** 
     *  Requires: _ //A positive integer  
     *  Effects: Returns an integer < 10, obtained by summing digits,
     *           throws ad illegalArgumentException if input is negative
     *  ex. 123 -> 1+2+3 = 6
     */
    public static int provaDelNoveHelp(int n) throws IllegalArgumentException{ 
        if(n<0) throw new IllegalArgumentException("input must be a positive integer.");
        int sum = 0;
        while(true){
            while(n!=0){
                sum += n%10;
                n/=10;
            }
            if(sum<10){return sum;}
            else {n = sum;sum = 0;}
        }
        
    }

    /**
     * Effects: Apply the integer division to given integer N, 
     *          throws an IllegalArgumentException if input N is negative
     */

    public static int provaDelNoveHelp2(int n) throws IllegalArgumentException{ 
        if(n<0) throw new IllegalArgumentException("input must be a positive integer.");
        return n%9;        
    }

}
