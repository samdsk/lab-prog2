
public interface Alias extends Iterable<Indirizzo>{
    /**
     * Aggiunge il dato parte locale a questo alias
     * @param l la parte locale da aggiungere a questo alias
     * @return true se stato aggiunto altrimenti false
     * @throws NullPointerException se {@link l} è null
     */
    public boolean add(Locale l);

    //public void add(Indirizzo i);

    /**
     * Rimuove il dato parte locale da questo alias
     * @param l la parte locale da rimuovere a questo alias
     * @return true se stato rimosso altrimenti false
     * @throws NullPointerException se {@link l} è null
     */
    public boolean remove(Locale l);

    //public void remove(Indirizzo i);

    /**
     * Permette di verificare se il dato indirizzo {@link i} fa parte di questo alias
     * @param i l'indirizzo da verificare
     * @return true se stato trovato nell'elenco altrimenti false
     * @throws NullPointerException se {@link i} è null
     */
    public boolean contains(Indirizzo i);

    /**
     * Permette di verificare se il dato parte locale {@link locale} fa parte di questo alias
     * @param locale parte locale da verificare
     * @return true se stato trovato nell'elenco altrimenti false
     * @throws NullPointerException se {@link locale} è null
     */
    public boolean contains(Locale locale);

    /**
     * Restituisce il dominio questo alias
     * @return il dominio di questo alias
     */
    public Dominio dominio();

    /**
     * Restituisce il nome di questo alias
     * @return il nome del alias
     */
    public String nome();
}
