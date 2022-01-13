import java.util.Arrays;
import java.util.Objects;

public class Parola {

    /**
     * AF(p) = p.word 
     * RI(p) = p.word != null && p.sorted_word != null;
     */
    protected final String word;
    protected final String sorted_word;

    public Parola(String s) throws NullPointerException{
        if(s == null) throw new NullPointerException();
        this.word = s;
        sorted_word = sort(s);

        assert repOk();
    }

    public Parola(Parola s)throws NullPointerException{
        if(s == null) throw new NullPointerException();
        this.word = s.word;
        sorted_word = s.sorted_word;

        assert repOk();
    }

    public String sort(String s)throws NullPointerException{
        if(s == null) throw new NullPointerException();
        char[] temp = s.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }

    public boolean isAnagram(Parola p)throws NullPointerException{
        if(p == null) throw new NullPointerException();
        return sorted_word.equals(p.sorted_word);
    }

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
