// Numeri di Lychrel


class Solution{
    public static void main(String[] args) {   
        lychrel(args[0]);
    }
    
    /**
     * Requires: Any positive number as a string 
     * Modifies: Modifies system.out
     * Effects: Print the Lychrel sequence of given number
     */
    public static void lychrel(String s){
        long l = Long.parseLong(s);
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
     * Requires: Any string != null
     * Modifies: _
     * Effects: Returns a reverse order string of input
     */
    public static String swap(String s){        
        int l = s.length();        
        if (l<=1) return s;    
        return swap(s.substring(1)) + s.charAt(0);
    }
}