// Numeri di Lychrel


class Solution{
    public static void main(String[] args) {   
        long l = Long.parseLong(args[0]);
        String s = args[0]; 
        long n = 0L;

        while(true){
            n = Long.parseLong(swap(s));
            System.out.printf("%d\n", l);
            if (n==l) break;
            l += n;
            s = Long.toString(l);            
        }

    }

    public static long swap(long l){        
        int i = Long.toString(l).length();
        long r = 0L;       
        
        while(l!=0){
            r += l%10* (long) Math.pow(10, --i);
            l = l/10;                    
        }

        return r;
    }

    public static String swap(String s){        
        int l = s.length();        
        if (l<=1) return s;    
        return swap(s.substring(1)) + s.charAt(0);
    }
}