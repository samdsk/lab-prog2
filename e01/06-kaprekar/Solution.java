//Kaprekar
public class Solution {
    public static void main(String[] args) {
        String n = args[0];
        String next = kaprekar(args[0]);
        
        while(true){
            System.out.println(n);
            if(n.equals(next)) break;
            n = next;
            next = kaprekar(next);
        }
    }

    interface Compare{       
       boolean op(char a,char b);
    }

    public static String kaprekar(String s){        
        int a = Integer.parseInt(sort(s, (x,y) -> (x<y)));
        int b = Integer.parseInt(sort(s, (x,y) -> (x>y)));        
        return Integer.toString(b-a);
    }

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
