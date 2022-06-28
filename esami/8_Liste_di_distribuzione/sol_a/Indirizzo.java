import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Indirizzo(String locale,String dominio) {
    /**
     * Overview: Indirizzo un classe immutabile e rappresenta un indirizzo email
     * 
     * 
     * AF = locale@dominio
     * 
     * RI = locale != null && locale.length > 0
     *      && dominio != null && dominio.length > 0
     *      && validator(locale,dominio)
     */

    /**
     * Costruisce un indirizzo email a partire dalla parte locale e dominio fornito
     * @param locale la parte locale dell'indirizzo email
     * @param dominio la parte del dominio dell'indirizzo email
     * @throws NullPointerException se locale o dominio è null
     * @throws IllegalArgumentException se locale o dominio sono vuote, 
     *      oppure se locale e dominio non forma un indirizzo email valido
     */
    public Indirizzo(final String locale, final String dominio){
        if(!(Objects.requireNonNull(locale,
            "La parte locale dell'indirizzo email non può essere null!").length()>0))
            throw new IllegalArgumentException("La parte locale dell'indirizzo email non può essere vuota!");
        if(!(Objects.requireNonNull(dominio,
            "La parte del dominio dell'indirizzo email non può essere null!").length()>0))
            throw new IllegalArgumentException("La parte del dominio dell'indirizzo email non può essere vuota!");
        
        validator(locale, dominio);

        this.dominio = dominio;
        this.locale = locale;

        assert repOk();

    }

    /*RI */
    private boolean repOk() {
        return false;
    }
    /**
     * Controlla se le due stringhe locale e dominio fornite forma un indirizzo email valido 
     * @param locale la parte locale dell'indirizzo email
     * @param dominio la parte del dominio dell'indirizzo email
     * @throws IllegalArgumentException se locale e dominio non forma un indirizzo email valido
     */
    private static void validator(final String locale, final String dominio) throws IllegalArgumentException{

        String er_locale = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*$";
        String er_dominio = "^[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        final Predicate<String> VALIDO_L = Pattern.compile(er_locale).asPredicate();
        final Predicate<String> VALIDO_D = Pattern.compile(er_dominio).asPredicate();

        if(!VALIDO_L.test(locale)) throw new IllegalArgumentException("La parte locale dell'indirizzo non valido!");
        if(!VALIDO_D.test(dominio)) throw new IllegalArgumentException("La parte del dominio dell'indirizzo non valido!");

        return;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append(locale);
        str.append("@");
        str.append(dominio);

        return str.toString();
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Indirizzo)) return false;

        Indirizzo other = (Indirizzo) o;

        return this.dominio.equals(other.dominio()) && this.locale.equals(other.locale());
    }

    @Override
    public int hashCode(){
        return Objects.hash(locale,dominio);
    }
}