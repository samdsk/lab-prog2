import java.util.Arrays;
import java.util.Objects;

public class Parola {
    //Overview it's immutable, represent a word 
    //which contains a string made by reording charactors in ascending order.

    /**
     * AF(p) = p.word 
     * RI(p) = p.word != null && p.sorted_word != null;
     */

    //fields
    //the word that class represents
    protected final String word;
    //rapresents the word where charactors are reordered in ascending order
    //Es. dcab -> abcd
    protected final String sorted_word;

    /**
     * Initialize this as word = s and sorted_word = sort(s)
     * if s is null throws NullPointerException
     */
    public Parola(String s) throws NullPointerException{
        if(s == null) throw new NullPointerException();
        this.word = s;
        sorted_word = sort(s);

        assert repOk();
    }

    public String getSig(){
        return this.sorted_word;
    }

    /**
     * Initialize this as the given Parola s
     * throws NullPointerException if s is null
     */
    public Parola(Parola s)throws NullPointerException{
        if(s == null) throw new NullPointerException();
        this.word = s.word;
        sorted_word = s.sorted_word;

        assert repOk();
    }

    /**
     * Effects: Returns a new string where the given s is reordered in ascending order
     *          throws NullPointerException if s == null
     */
    public String sort(String s)throws NullPointerException{
        if(s == null) throw new NullPointerException();
        char[] temp = s.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }

    /**
     * Effects: Return true if the given Parola p is an anagram of this
     *          throws NulPointerException if p == null
     */
    public boolean isAnagram(Parola p)throws NullPointerException{
        if(p == null) throw new NullPointerException();
        return sorted_word.equals(p.sorted_word);
    }

    /**
     * RI
     */
    public boolean repOk(){
        if(word != null && sorted_word != null) return true;
        return false;
    }

    @Override
    public boolean equals(Object obj)throws NullPointerException {
        if(obj == null) throw new NullPointerException();
        if(!(obj instanceof Parola)) return false;

        Parola p = (Parola) obj;

        if(!word.equals(p.word) || !sorted_word.equals(p.sorted_word)) return false;

        return true;
    }

    @Override
    public String toString() {  
        return word;
    }

    @Override
    public int hashCode() {
        return Objects.hash(word,sorted_word);
    }
}
