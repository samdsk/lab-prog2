public interface CorpoCeleste{
    //Overview: un oggetto CorpoCeleste rappresenta un corpo celeste avente una posizione tridimensionale e una velocità
    
    /**
     * Aggiorna la velocita di questo CorpoCeleste 
     * rispetto la forze esercitate dal CorpoCeleste {@link c} su questo
     * @param c il secondo corpo celeste con cui calcore la velocità
     * @throws NullPointerException se {@link c} è null
     */
    void aggiornaVelocita(CorpoCeleste c);

    void aggiornaPosizione();

    long energiaPotenziale();

    long energiaCinetica();

    long energia();

    String nome();

    Punto posizione();

    Punto velocita();

}