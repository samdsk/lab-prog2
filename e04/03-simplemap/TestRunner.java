import java.util.Scanner;

import javax.lang.model.util.SimpleTypeVisitor8;

public class TestRunner {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);

        SIMap m = new SIMap();
       
        while(true){
            try{
                if(!reader.hasNext()) break;

                String op = reader.next();
                if(op.equals("+")){
                    String str = reader.next();
                    int n = reader.nextInt();
                    m.put(str,n);
                }
                if(op.equals("-")){
                    String str = reader.next();
                    int n = m.remove(str);
                    System.out.println("Removed: "+n);
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
        reader.close();
        System.out.println(m.toString());

    }
}
