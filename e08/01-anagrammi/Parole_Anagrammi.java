import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Parole_Anagrammi extends Parola{
    //Overview extends Parola class by adding a method to build 
    //a list of parola which are anagrams of super

    /**
     * AF(a) = [Parola_0,...Parola_n] where Parola_i are anagrams of Parola_0 where i is 0<=i<size
     * 
     * RI = super.repOk() && size==list.size && size=>1 && list.get(0).word == this.word
     */

    //fields
    // List of Parola in which we store anagrams
    private List<Parola> list;
    // the number of anagrams, it is always >= 1 because of reflexive property of an anagram
    private int size;

    /**
     * Initialize super as Parola s, this.list as a list containing s.word and size = 1
     */
    public Parole_Anagrammi(Parola s){
        super(s);
        list = new LinkedList<>();
        list.add(s);        
        size = 1;
    }

    /**
     * Modifies: this and the given List l
     * Effects: If the given List l has anagrams of this, they will be 
     *          appened to this.list (only once, no duplicates), this.size increased by 1 and removed from List l and returns the modified l.
     *          Throws NullPointerException if List l is null.
     */
    public List<Parola> Trova_Anagrammi(List<Parola> l)throws NullPointerException {      
        
        Iterator<Parola> it = l.iterator();

        while(it.hasNext()){
            Parola p = it.next();
            if(isAnagram(p)){
                it.remove();
                if(!list.contains(p)) {
                    list.add(p);
                    size++;
                }
            }
        }
        return l;
    }

    /**
     * Retun the size of this.list
     * @return size
     */

    public int size(){
        return size;
    }

    /**
     * Returns this.list of anagrams
     * @return list
     */
    public List<Parola> getList(){
        return list;
    }

    //RI = super.repOk() && size==list.size && size=>1 && list.get(0).word == this.word    
    @Override
    public boolean repOk(){
        if(super.repOk()){
            if(size==list.size() 
            && size >= 1
            && list.get(0).word.equals(super.word)) return true;
        }

        return false;
    }

    @Override
    public String toString() {        
        return list.toString();
    }

    @Override
    public boolean equals(Object obj)throws NullPointerException {
        if(obj == null) throw new NullPointerException();
        if(obj == this) return true;
        if(!(obj instanceof Parole_Anagrammi)) return false;

        Parole_Anagrammi p = (Parole_Anagrammi) obj;

        if(!super.equals(p)) return false;

        for (Parola p1 : list) {
           for (Parola p2 : p.list) {
               if(!p1.equals(p2)) return false;
           }
        }

        return true;
    }

    @Override
    public int hashCode() {        
        return Objects.hash(list,size,word,sorted_word);
    }


}
