import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Locale(String locale){
    /**
     * Overview: Locale è un oggetto immutabile e rappresenta la locale di un indirizzo email 
     * es. LOCALE@dominio
     * 
     * AF(locale) = locale
     * 
     * RI(locale) = locale != null && VALIDO.test(locale)
     */

    /**
     * Inizializza this con la data stringa
     * @param locale la parte locale dell'indirizzo email
     * @throws NullPointerException se l'input locale è null
     * @throws IllegalArgumentException se l'input non corrisponde alla parte locale di un indirizzo email
     */
    public Locale{
        Objects.requireNonNull(locale);
        if(!VALIDO.test(locale)) throw new IllegalArgumentException("L'input non corrisponde a un locale di una email.");
    }

    //il pattern regex utilizzato per verificare la parte locale di un indirizzo email
    private final static String er = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*$";
    //predicato permette di verificare una data stringa corrisponde al pattern regex "er"
    private final static Predicate<String> VALIDO = Pattern.compile(er).asPredicate();

    //RI
    public boolean repOk(){
        return locale != null && VALIDO.test(locale);
    }
}