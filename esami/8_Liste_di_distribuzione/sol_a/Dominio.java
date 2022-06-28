import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Dominio(String dominio) {
    /**dominio
     * Overview: Il record Dominio è un oggetto immutabile 
     *  che rappresenta la parte del dominio di un indirizzo email
     * 
     * come la parte del dominio si indente la parte che sta a destra del @ di un indirizzo email 
     * es: dominio@dominio
     * 
     * AF = dominio
     * RI = dominio != null && dominio.length > 0 && valid(dominio)
     */


    /**
     * Costruisce un nuovo oggetto dominio con la data stringa 
     * che rappresenta la parte del dominio dell'indirizzo email valido
     * 
     * @param dominio la parte dominio
     * @throws NuulPointerException se dominio è null
     * @throws IllegalArgumentException se dominio è una stringa vuota 
     *          oppure se non rappresenta la parte del dominio di un indirizzo email valido
     */
    public Dominio(String dominio){
        if(
            !(Objects.requireNonNull(
                dominio,"La parte del dominio di un indirizzo email non può essere null!")
            .length() > 0)
        ) throw new IllegalArgumentException("La parte del dominio di un indirizzo email non può essere vuota!");

        if(!valid(dominio)) throw new IllegalArgumentException("La parte del dominio non è valida");

        this.dominio = dominio;

        assert repOk();

    }
    /** RI */
    private boolean repOk() {
        if(dominio != null 
            && dominio.length()>0 
            && valid(dominio)) return true;

        return false;
    }

    /**
     * Restituisce true se la stringa dominio è la parte del dominio di un indirizzo email valido
     * altrimenti false
     * @param dominio la parte dominio
     * @return true se valido false altrimenti
     */
    private static boolean valid(String dominio){
        String er = "^[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        final Predicate<String> VALIDO = Pattern.compile(er).asPredicate();
        return VALIDO.test(dominio);
    }
    
}
