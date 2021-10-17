//Scaccheria
public class Solution {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        print(n);
    }

    public static void print(int n){        
        row(0,n);
    }

    public static void row(int r, int n){
        if(r==8) return;
        columns(r,n);
        row(r+1,n);
    }

    public static void columns(int r, int n){
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
