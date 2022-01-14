
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test1{
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        List<Parola> lst = new LinkedList<>();
        
        while(reader.hasNextLine()){
            lst.add(new Parola(reader.nextLine()));
        }

        reader.close();
    }

    public static void anagrammi(List<Parola> lst){
        Anagrammi a = new Anagrammi();

        a.ProcessWords(lst);

        List<List<String>> output = a.getAnagrammi();
        
        sort(output);
        print_anagrammi(output);
    }

    public static void sort(List<List<String>> output){
        output.sort(new Comparator<List<String>>() {

            @Override
            public int compare(List<String> a, List<String> b) {
                if(a.size()==b.size()) return a.get(0).compareTo(b.get(0));
                if(a.size()>b.size()) return -1;
                return 1;
            }
            
        });
    }

    public static void print_anagrammi(List<List<String>> lst){
        for (List<String> s: lst) {
            System.out.println(s.toString());
        }
    }
} 