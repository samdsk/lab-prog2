import java.util.LinkedList;
import java.util.List;

/*OVERVIEW: Le istanze di questa classe rappresentano code di interi.
            Gli oggetti di questo tipo sono mutabili.
            Esempio di coda: [x1, x2, ..., xk], k <= n (k minore o uguale 
            alla dimensione massima n della coda).
            Poiche' e' una struttura dati che opera in modali o' FIFO, 
            a seguito di una enqueue dell'elemento y, otterro': 
                [x1, x2, ..., xk, y] ;
            a seguito di una enqueue dell'elemento x1, otterro': 
                [x2, ..., xk] .
*/

public class IntQueueUnb {

    //CAMPI

    /**  */
    private final List<Integer> elements;

    private int head, tail;
    
    //COSTRUTTORi
    /**
     * Effetti collaterali: modifica lo stato di this; //come tutti i costruttori
     * Post-condizioni: inizializza this affinche' rappresenti una coda vuota;
     */
    public IntQueueUnb() {
        //new list non funzionerebbe, list e' una interfaccia, non ha costruttori
        elements = new LinkedList<>(); //per implementare guardo doc di linkedlist

        head = -1;
        tail = 0;

        assert repOk();
    }

    //METODI

    /**
     * Effetti collaterali: potrebbe modificare this;
     * Post-condizioni: aggiunge n alla coda;
     *                  this = [x1, x2, ... , xk], this_post = [x1, x2, ... , xk, n];
     */
    public void enqueue(int n) {
        elements.add(n);

        assert repOk();
    }

    /**
     * Post-condizioni: restituisce true se la coda e' vuota, false altrimenti
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Effetti collaterali: potrebbe modificare this;
     * Post-condizioni: se la coda e' vuota, solleva un'eccezione di tipo EmptyException,
     *                  altrimenti restituisce l'elemento in testa alla coda e 
     *                  lo elimina da this;
     *                  this = [x1, x2, ... , xk], this_post = [x2, ... , xk];
     */
    public int dequeue() {

        assert repOk();

        //if (isEmpty()) throw new EmptyException("Impossibile estrarre elemento (coda vuota).");
        
        return elements.remove(0);
    }

    @Override
    /**
     * Post-condizioni: implementa la funzione di astrazione
     */
    public String toString() {
        //bisogna ritornare la stringa che corrisponde alla rappresentazione del tipo
        //guardare AF e il suo contratto per implementare
        String result = "IntQueue : [";

        if (!isEmpty()) {
            int i;
            for (i = 0; i < size() -1; i++) {
                result += elements.get(i) + ", ";
            }
            result += elements.get(i);
        }

        return result + "]";
    }

    /**
     * Post-condizioni: restituisce il numero di elementi presenti nella coda
     */
    public int size() {
        assert repOk();
        return elements.size();
    }

    //TODO
    /**
     * Post-condizioni: implementa l'invariante di rappresentazione
     */
    private boolean repOk() {
        //guardo IR per implementarlo
        //restituisce true quando le clausole sono tutte vere --> AND logico
        return (elements != null && (head != -1 || tail == 0));
    }

    //TODO
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
}
