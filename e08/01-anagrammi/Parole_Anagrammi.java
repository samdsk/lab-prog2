import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Parole_Anagrammi extends Parola {

    /**
     * AF(a) = [Parola_0,...Parola_n] where Parola_i are anagrams of Parola_0 where i is 0<=i<size
     * 
     * RI = super.repOk() && size==list.size=>1 && list.get(0).word == this.word
     */

    private List<Parola> list;
    private int size;

    public Parole_Anagrammi(Parola s) throws NullPointerException {
        super(s);
        list = new LinkedList<>();
        list.add(s);
        size = 1;
    }

    public List<Parola> Trova_Anagrammi(List<Parola> l)throws NullPointerException {
        l.remove(list.get(0));

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

    public int size(){
        return size;
    }
    //RI = super.repOk() && size==list.size=>1 && list.get(0).word == this.word
    
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
}
