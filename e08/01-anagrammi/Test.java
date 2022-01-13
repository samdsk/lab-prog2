import java.util.Comparator;
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

        List<List<Parola>> output = new LinkedList<>();
        
        while(!lst.isEmpty()){
            Parole_Anagrammi pa = new Parole_Anagrammi(lst.get(0));
            lst = pa.Trova_Anagrammi(lst);
            if(pa.size()>1) output.add(pa.getList());
        }

        output.sort(new Comparator<List<Parola>>() {

            @Override
            public int compare(List<Parola> o1, List<Parola> o2) {
                if(o1.size()==o2.size()) return 0;
                if(o1.size()>o2.size()) return -1;
                return 1;             
            }
            
        });
        

        for (List<Parola> s: output) {
            System.out.println(s.toString());
        }

/*        Collections.reverse(output);

        for (List<Parola> s: output) {
            System.out.println(s.toString());
        }

*/
    }

} 