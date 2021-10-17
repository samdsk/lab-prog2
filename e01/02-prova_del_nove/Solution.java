//prova del nove
public class Solution {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        
        for(int i=1;i<n;i++){            
            for(int j=1;j<n;j++){
                for(int c=1;c<n;c++){
                    boolean check = provaDelNove(new int[]{i,j,c});
                    if(check && (c!=i*j)) System.out.printf("%d %d %d%n",i,j,c);
                }
            }
        }
        
    }
    /** Requires: An array of 3 positive integers  
     *  Effects: Returns true if it satifies prova del nove otherwise false
     * 
     */
    public static boolean provaDelNove(int[] n){
        int a = provaDelNoveHelp(n[0]);
        int b = provaDelNoveHelp(n[1]);
        int c = provaDelNoveHelp(n[2]);
        int m = provaDelNoveHelp(a*b);

        return (m==c);
    }

    /** Requires: An integer  
     *  Effects: Returns an integer < 10, obtained by summing digits
     *  ex. 123 -> 1+2+3 = 6
     */
    public static int provaDelNoveHelp(int n){ 
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
}
