import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Anagrammi_v1 extends Anagramma{
    //Overview questa classe estende la classe anagramma, permette di creare 
    //una lista di anagrammi per una data stringa e una data lista di stringhe

    /**
     * AF(list) = [list[0],...,a[i],...,a[size]] dove i è un numero intero 0<=i<size
     * 
     * RI = super.repOk() && size>=1 && anagram_lst.size == size
     */

    //fields

    //lista di anagrammi
    private final List<String> anagram_lst;

    //numero di elementi nella lista anagram_lst
    private int size;

    //constructors

    /**
     * Initialize this as super(s) and adds s to anagram_lst 
     */
    public Anagrammi_v1(String s){        
        super(s);    
        anagram_lst = new ArrayList<>();  
        anagram_lst.add(s);  
        size = 1;

        assert repOk();
    }
    
    /**
     * Modifies: this and lst
     * Effects: Appeneds to anagram_lst anagram words found in lst removing them from lst
     *          Returns modified lst
     *          Throws NullPointerException if lst list is null
     */
    public List<String> build_list_of_anagrams(List<String> lst)throws NullPointerException{
        if(lst == null) throw new NullPointerException("Provided list is null!");
        lst.remove(super.getString());
        Iterator<String> it = lst.iterator();
        while(it.hasNext()){
            String s = it.next();
            if(isAnagram(s)) {
                anagram_lst.add(s);
                it.remove();
                size++;
            }
        }

        assert repOk();
        //System.out.println(lst.toString());
        return lst;
    }

    @Override
    public boolean repOk(){
        if(super.repOk()){
            return size>=1 && anagram_lst.size() == size;
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        //if(o == null) return false;
        if(o == this) return true;
        if(! (o instanceof Anagrammi_v1)) return false;

        Anagrammi_v1 a = (Anagrammi_v1) o;

        if(!super.equals(a)) return false;

        if(size!= a.size) return false;
        for (String s1 : a.anagram_lst) {
            if(!anagram_lst.contains(s1)) return false;
        }

        return true;
    }

    @Override
    public String toString(){        
        return anagram_lst.toString();
    }

    @Override
    public int hashCode(){
        return super.hashCode() + anagram_lst.hashCode() +Objects.hash(size);
    }

}
