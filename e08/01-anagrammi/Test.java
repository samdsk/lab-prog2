
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test{
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        List<Parola> lst = new LinkedList<>();
        
        while(reader.hasNextLine()){
            lst.add(new Parola(reader.nextLine()));
        }

        reader.close();

        while(!lst.isEmpty()){
            Parole_Anagrammi pa = new Parole_Anagrammi(lst.get(0));
            lst = pa.Trova_Anagrammi(lst);
            System.out.println(pa.toString());
        }
    }
} 