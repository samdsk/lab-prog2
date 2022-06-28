import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Locale(String locale){
    /**
     * Overview: Il record Locale è un oggetto immutabile 
     *  che rappresenta la locale di un indirizzo email
     * 
     * come parte locale si indente la parte che sta sinistra del @ di un indirizzo email 
     * es: locale@dominio
     * 
     * AF = locale
     * RI = locale != null && locale.length > 0 && valid(locale)
     */


    /**
     * Costruisce un nuovo oggetto Locale con la data stringa 
     * che rappresenta la parte locale dell'indirizzo email valida
     * 
     * @param locale la parte locale
     * @throws NuulPointerException se locale è null
     * @throws IllegalArgumentException se locale è una stringa vuota 
     *          oppure se non rappresenta la parte locale di un indirizzo email valido
     */
    public Locale(String locale){
        if(
            !(Objects.requireNonNull(
                locale,"La parte locale dell'indirizzo email non può essere null!")
            .length() > 0)
        ) throw new IllegalArgumentException("La parte locale dell'indirizzo email non può essere vuota!");

        if(!valid(locale)) throw new IllegalArgumentException("La parte locale non è valida");

        this.locale = locale;

        assert repOk();

    }
    /** RI */
    private boolean repOk() {
        if(locale != null 
            && locale.length()>0 
            && valid(locale)) return true;

        return false;
    }

    /**
     * Restituisce true se la stringa locale è la parte locale di un indirizzo email valido
     * altrimenti false
     * @param locale la parte locale
     * @return true se valido false altrimenti
     */
    private static boolean valid(String locale){
        String er = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*$";
        final Predicate<String> VALIDO = Pattern.compile(er).asPredicate();
        return VALIDO.test(locale);
    }
}
