import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Dominio(String dominio){
    /**
     * Overview: Dominio è un oggetto immutabile e rappresenta il dominio di un indirizzo email 
     * es. locale@DOMINIO
     * 
     * AF(dominio) = dominio
     * 
     * RI(dominio) = dominio != null && VALIDO.test(dominio)
     */

    /**
     * Inizializza this con la data stringa
     * @param dominio la parte del dominio dell'indirizzo email
     * @throws NullPointerException se l'input dominio è null
     * @throws IllegalArgumentException se l'input non corrisponde alla parte del dominio di un indirizzo email
     */
    public Dominio{
        Objects.requireNonNull(dominio);
        if(!VALIDO.test(dominio)) throw new IllegalArgumentException("L'input non corrisponde a un dominio di una email.");
    }

    
    /**
     * Inizializza this con il dato {@link dominio}
     * @param dominio la parte del dominio dell'indirizzo email
     * @throws NullPointerException se {@link dominio} è null
     */
    /*
    public Dominio(Dominio dominio){
        this(Objects.requireNonNull(dominio).dominio());
    }
    */
    //il pattern regex utilizzato per verificare la parte del dominio di un indirizzo email
    private final static String er = "^[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    //predicato permette di verificare una data stringa corrisponde al pattern regex "er"
    private final static Predicate<String> VALIDO = Pattern.compile(er).asPredicate();

    //RI
    public boolean repOk(){
        return dominio != null && VALIDO.test(dominio);
    }
}