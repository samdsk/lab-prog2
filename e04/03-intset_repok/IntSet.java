import java.util.ArrayList;
import java.util.List;

/**
 * OVERVIEW: le istanze di questa classe rappresentano insiemi (non limitati)
 *           di interi. Gli oggetti di questo tipo sono mutabili.
 */
public class IntSet {

    //CAMPI
    /** Struttura dati contenente gli elementi dell'insieme */
    private List<Integer> elements;

    /**
     * FUNZIONE DI ASTRAZIONE: (da classe concreta [dominio] a oggetti astratti [codominio])
     *                        AF(elements) = {} (insieme vuoto) se elements non contiene
     *                                       elementi (se elements.length < 1);
     *                                     = {x1, x2, x3, x4...} altriment;
     * 
     * INVARIANTE DI RAPPRESENTAZIONE: (predicato logico che esprime se l'insieme sia un 
     *                                 insieme valido o meno)
     *                                 insieme non e' di dimensione negativa (ma e' 
     *                                 possibile? non credo);
     *                                 elements != null (?);
     * 
     * METTO CONTROLLO IR SOLO NEI METODI MODIFICATORI
     */

    //COSTRUTTORI
    /**
     * Post-condizioni: inizializza un nuovo insieme di interi vuoto
     */
    public IntSet() { //e' sia il nome del costruttore che il tipo restituito
        elements = new ArrayList<>();
    }  

    //METODI
    /**
     * Effetti collaterali: potrebbe modificare this (dopo aver invocato il metodo): 
     *                      this_post = this + {x} 
     * Post-condizioni: aggiunge x all'insieme this
     */
    public void insert(int x) {
        if (!this.contains(x)) {
            elements.add(x); 
            /*c'e' un boxing automatico per cui posso inserire int 
            direttamente in ArrayList<Integer> */
        }
        assert repOk();
    }

    /**
     * Effetti collaterali: potrebbe modoificare this: this_post = this - {x}
     * Post-condizioni: rimuove x dall'insieme this
     */
    public void remove(int x) {
        //ALTERNATIVO (?)
        //elements.remove(Integer.valueOf(x)); 

        //trovato l'elemento, lo scambio con l'ultimo della lista e poi diminuisco
        //la lunghezza dell'array di 1
        int index = elements.indexOf(x);
        if (index != -1 ) {
            int lastIndex = elements.size() -1;
            Integer last = elements.get(lastIndex);
            elements.set(index, last);
            elements.remove(lastIndex);
        }
        assert repOk();
    }

    /**
     * Post-condizioni: restituisce un intero arbitrariamente scelto tra gli 
     *                  elementi di this e solleva un'eccezione di tipo 
     *                  EmptyException se l'insieme e' vuoto.
     */
    public int choose() {
        //EmptyException e' unchecked
        if (size() == 0) {
            throw new EmptyException("Impossibile estrarre elemento (insieme vuoto).");
        }
        //return 0; //per poter compilare
        return elements.get(elements.size() -1);
    }

    /**
     * Post-condizioni: restituisce la cardinalita' dell'insieme this
     */
    public int size() {
        return elements.size();
    }

    /**
     * Post-condizioni: restituisce true se x e' in this, false altrimenti
     */
    public boolean contains(int x) {
        return elements.contains(x);
    }

    /**
     * Post-condizioni: restituisce una rappresentazione testuale di this 
     *                  ad esempio: {1, 2, 3}
     */
    @Override
    public String toString() {
        String r ="IntSet: {";
        for (int i = 0; i < size() -1; i++) {
            r += elements.get(i) + ", ";
        }
        r += elements.get(elements.size() -1);
        return r + "}";
    }

    /**
     * Post-condizioni: implementa l'invariante di rappresentazione
     */
    private boolean repOk() {
        //guardo IR per implementarlo
        //restituisce true quando le clausole sono tutte vere --> AND logico
        return (elements.size() >= 0
                && elements != null);
    }

    /* OVERRIDE IN CUI IL COMPILATORE DA' ERRORE
    @Override
    public String toString(int x) {
        return "";
    }
    */
} 