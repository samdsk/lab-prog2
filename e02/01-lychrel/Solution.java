// Numeri di Lychrel


class Solution{
    public static void main(String[] args) {   
        try {
            lychrel(args[0]);
        } catch (NullPointerException npe) {
            System.out.println(npe.toString());
        }catch (IllegalArgumentException iae){
            System.out.println(iae.toString());
        }
        
    }
    
    /**
     * Requires: _
     * Modifies: System.out
     * Effects: Prints to the system out the Lychrel sequence of given number
     *          Throws NullPointerException if input is null and 
     *          Throws IllegalArgumentException if input is negative
     */
    public static void lychrel(String s) throws NullPointerException, IllegalArgumentException {
        if(s == null) throw new NullPointerException("lychrel - Null string is not accepted.");        

        long l = Long.parseLong(s);

        if(l<0) throw new IllegalArgumentException("Must be a positive number.");

        long n = 0L;

        while(true){
            n = Long.parseLong(swap(s));
            System.out.printf("%d\n", l);
            if (n==l) break;
            l += n;
            s = Long.toString(l); 
        }
    }
    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns a reverse order string of input
     *          Throws NullPointerException if input is null
     */
    public static String swap(String s) throws NullPointerException{    
        if(s == null) throw new NullPointerException("swap - Null string is not accepted.");     
        int l = s.length();        
        if (l<=1) return s;    
        return swap(s.substring(1)) + s.charAt(0);
    }
}