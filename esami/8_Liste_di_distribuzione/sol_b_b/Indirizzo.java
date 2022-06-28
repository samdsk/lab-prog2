public class Indirizzo {
    /**
     * Le istanze di questa classe sono immutabili e concorrono a rappresentare
     * un indirizzo email composto da parte locale (prima di @) e parte dominio.
     * 
     * AF: N^2 -> locale.toString() + @ + dominio.toString()
     */

    // attributi
    final private Locale locale;
    final private Dominio dominio;

    // costruttori
    /**
     * Requires: s deve essere un indirizzo email
     * Effects: inizializza un nuovo indirizzo a partire da un indirizzo email
     * in formato testuale
     * @param s stringa dalla quale creare l'indirizzo
     */
    public Indirizzo(String s) {
        String[] parti = s.split("@");
        locale = new Locale(parti[0]);
        dominio = new Dominio(parti[1]);
    }

    /**
     * Requires: locale e dominio non null
     * Effects: inizializza this a partire da una parte locale e una parte di dominio
     * @param locale
     * @param dominio
     */
    public Indirizzo(Locale locale, Dominio dominio) {
        this.locale = new Locale(locale);
        this.dominio = new Dominio(dominio);
    }

    /**
     * Effects: restituisce la parte locale
     * @return la parte locale
     */
    public Locale locale() {
        return locale;
    }

    /**
     * Effects: restituisce la parte dominio
     * @return la parte dominio
     */
    public Dominio dominio() {
        return dominio;
    }

    /**
     * Effects: resisuisce una rappresentazione testuale dell'indirizzo email, 
     * ad esempio: prova@unimi.it
     */
    @Override
    public String toString() {
        return locale + "@" + dominio;
    }
}
