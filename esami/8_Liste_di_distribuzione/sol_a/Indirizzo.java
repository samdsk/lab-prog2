import java.util.Objects;
public record Indirizzo(Locale locale,Dominio dominio){
    /**
     * Overview: Indirizzo un classe immutabile e rappresenta un indirizzo email 
     *  formato dalle parti locale e dominio
     * 
     * 
     * AF = locale@dominio
     * 
     * RI = locale != null
     *      && dominio != null
     */

    /**
     * Costruisce un indirizzo email a partire dalla parte locale e dominio fornito
     * @param locale la parte locale dell'indirizzo email
     * @param dominio la parte del dominio dell'indirizzo email
     * @throws NullPointerException se locale o dominio è null
     */
    public Indirizzo(final Locale locale,final Dominio dominio){
        Objects.requireNonNull(locale,"La parte locale dell'indirizzo email non può essere null!");
        Objects.requireNonNull(dominio, "La parte del dominio dell'indirizzo email non può essere null!");
        
        this.dominio = dominio;
        this.locale = locale;

        assert repOk();

    }

    /*RI */
    private boolean repOk() {
        return locale != null && dominio != null;
    }
   
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append(locale.locale());
        str.append("@");
        str.append(dominio.dominio());

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

    public int compareTo(Indirizzo o2) {
        return 0;
    }

}