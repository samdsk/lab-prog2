//Kaprekar
public class Solution {
    public static void main(String[] args) {
        kaprekar(args[0]);
    }

    interface Compare{       
       boolean op(char a,char b);
    }

    /**
     * Requires: N positive integer of 4 digits
     * Modifies: System.out 
     * Effects: Prints the sequenze of numbers until Kaprekar's constant
     */
    public static void kaprekar(String n){        
        String next = findNextKaprekarNumber(n);
        
        while(true){
            System.out.println(n);
            if(n.equals(next)) break;
            n = next;
            next = findNextKaprekarNumber(next);
        }
    }

    /**
     * Requires: N positive integer of 4 digits
     * Modifies: _
     * Effects: Given a 4 digit number returns the next Kaprekar number.
     */
    public static String findNextKaprekarNumber(String s){        
        int a = Integer.parseInt(sort(s, (x,y) -> (x<y)));
        int b = Integer.parseInt(sort(s, (x,y) -> (x>y)));        
        return Integer.toString(b-a);
    }

    /**
     * Requires: S a string != null and C a 
     * Modifies: _
     * Effects: Given a S string returns ordered string according to provided C
     */
    public static String sort(String s,  Compare c){
        int len = s.length();

        if(len<2) return s;

        String l = "";
        String r = "";        
        char pivot = s.charAt(0);

        for(int i=1; i<len;i++){
            char temp = s.charAt(i);
            if(c.op(temp,pivot))
                l += temp;
            else 
                r += temp;
        }

        //System.out.printf("%s - %c -  %s%n",l,pivot,r);

        String output = "";
        output += sort(l,c) + pivot + sort(r,c) ;

        return output;
    }
}
