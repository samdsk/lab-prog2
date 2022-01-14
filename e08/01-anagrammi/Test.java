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

        find_anagrams(lst);

        //System.out.println(lst);
    }

    /**
     * Modifies: system.out and lst
     * Effects: prints to system.out the lists of anagrams found from the given Parola list lst.
     *          removes processed anagrams from the list
     */

    public static void find_anagrams(List<Parola> lst){
        List<List<Parola>> output = new LinkedList<>();
        
        while(!lst.isEmpty()){
            Parole_Anagrammi pa = new Parole_Anagrammi(lst.get(0));
            lst = pa.Trova_Anagrammi(lst);
            if(pa.size()>1) output.add(pa.getList());
        }

        sort_anagram_list(output);
        print_anagrams(output);
    }
    /**
     * Modifies: the given list l
     * Effects: Reorder the given list of Parola list l in descending order
     */
    public static void sort_anagram_list(List<List<Parola>> l){
        l.sort(new Comparator<List<Parola>>() {
            @Override
            public int compare(List<Parola> o1, List<Parola> o2) {
                if(o1.size()==o2.size()) return 0;
                if(o1.size()>o2.size()) return -1;
                return 1;             
            }            
        });
    }
    /**
     * Modifies: sysout.out
     * Effects: prints to system.out the lists of Parola of the given list of Parola list l
     */
    public static void print_anagrams(List<List<Parola>> l){
        for (List<Parola> s: l) {
            System.out.println(s.toString());
        }
    }

} 