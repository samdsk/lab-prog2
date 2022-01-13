import java.util.Arrays;
import java.util.Objects;

public class Anagramma {
    //Overview è una classe immutabile che permette di computare se una parola è un anagramma di un altra parola.

    /**
     * AF(a) = a.sorted_str tale che sorted_str è una stringa che ha caratteri in ordine crescente
     * 
     * RI(a) = str != null && sorted_str != null
     */

    //fields

    //str è la stringa che deve essere confrontato con un altra stringa
    private final String str;
    //sorted_str è la stringa str ricostruita con tutti caratteri riordinati in ordine crescente
    //Es ciao -> acio
    private final String sorted_str;

    //constructors

    /**
     * inizializza this con la stringa s
     * @param s stringa con cui inizializzare il confronto
     * @throws NullPointerException se s è null
     */
    public Anagramma(String s) throws NullPointerException{
        if(s == null) throw new NullPointerException("Inserted string is null!");
        this.str = s;
        sorted_str = sort(s);
    }

    /**
     * Riordina la stringa data s in ordine crescente
     * @param s la stringa che deve essere riordinata
     * @return la stringa riordinata in ordice crescente
     * @throws NullPointerException se s è null
     */
    public String sort(String s)throws NullPointerException{
        if(s == null) throw new NullPointerException("Inserted string is null!");
        char[] char_arr = s.toCharArray();
        Arrays.sort(char_arr);
        return new String(char_arr);
    }

    /**
     * Se la data stringa s è un'anagramma di this restituisce true altrimenti false
     * @param s è la stringa con cui verificare se è un'anagramma di this
     * @return restituisce true se s è un'anagramma di this
     * @throws NullPointerException se s è null
     */
    public boolean isAnagram(String s)throws NullPointerException{
        if(s == null) throw new NullPointerException("Inserted string is null!");
        String s2 = sort(s);
        boolean output = sorted_str.equals(s2);
        //System.out.println(str+" "+sorted_str+" "+s2+":"+output);
        return output;
    }

    /**
     * Restituisce stringa str
     * @return stringa str con cui si confronta
     */
    public String getString(){
        return str;
    }

    public boolean repOk(){
        return str != null && sorted_str != null;
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(! (o instanceof Anagramma)) return false;

        Anagramma a = (Anagramma) o;

        return str.equals(a.str) && sorted_str.equals(a.sorted_str);
    }

    @Override
    public String toString(){
        return sorted_str;
    }

    @Override
    public int hashCode(){
        return Objects.hash(str,sorted_str);
    }

}

