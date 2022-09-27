import java.util.Objects;

public record Indirizzo(Dominio dominio,Locale locale) {
    /**
     * Overivew: Indirizzo è un oggetto immutabile rappresenta l'indirizzo
     * email
     * 
     * AF(locale,dominio) = locale@dominio
     * 
     * RI = dominio != null && locale != null
     */

    /**
     * Inizializza this con il dato dominio e locale
     * @param dominio la parte del dominio dell'indirizzo email
     * @param locale la parte locale dell'indirizzo email
     * @throws NullPointerException se dominio oppure locale è null
     */
    public Indirizzo{
        Objects.requireNonNull(dominio);
        Objects.requireNonNull(locale);
    }
    
    //RI
    public boolean repOk(){
        return dominio != null && locale != null;
    }

    @Override
    public String toString(){
        return locale+"@"+dominio;
    }
}
