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

public class IntQueue {

    //CAMPI

    //teniamo la rappresentazione interna alla classe
    /** Questa e' la struttura dati che contiene gli elementi 
     * della IntQueue this */
    final private int[] elements;

    //sono nascosti (private) e non final (cambiano)
    /** Gli indici della testa e della coda della IntQueue this.
     * Head indica il primo elemento di this (-1 se la coda e' vuota);
     * Tail indica l'indice della prima posizione disponibile;
     *      se tail == head, allora la coda e' piena.
     */
    private int head, tail;

    /**
     * Funzione di astrazione: (dominio: classe concreta, codominio: oggetti astratti)
     *      AF(elements, head, tail) = [elements[i] | head <= i < tail];
     *                               = [] se head == -1 AND tail == 0;
     *                               = [elements[head], elements[head+1], ... , 
     *                                 elements[elements.size -1], elements[0], ... ,
     *                                 elements[tail-1]] se tail <= head;
     *                               = [elements[head], elements[head+1], ... , 
     *                                 elements[tail-1]] se head < tail;                        
     * 
     * Invariante di rappresentazione: (un predicato logico che dice se la coda e' una 
     *                                 coda valida o meno) 
     *                                 la coda non contiene piu' elementi della sua
     *                                 capienza massima;
     *                                 elements non deve essere null;
     *                                 -1 <= head < elements.size-1 ;
     *                                 0 <= tail < elements.size-1 ;
     *                                 head == -1 --> tail == 0 ;
    */
    
    //COSTRUTTORi
    /**
     * Pre-condizioni: n >= 0;
     * Effetti collaterali: modifica lo stato di this; //come tutti i costruttori
     * Post-condizioni: inizializza this affinche' rappresenti una coda vuota di 
     *                  dimensione n;
     */
    public IntQueue(int n) {
        elements = new int[n];
        head = -1;
        tail = 0;

        assert repOk();
    }

    //METODI

    /**
     * Effetti collaterali: potrebbe modificare this;
     * Post-condizioni: se la coda e' piena, solleva un'eccezione di tipo FullException,
     *                  altrimenti aggiunge n alla coda;
     *                  this = [x1, x2, ... , xk], this_post = [x1, x2, ... , xk, n];
     */
    public void enqueue(int n) {
        /*eccezione controllata: il compilatore controlla che sia stata gestita
        http://bilbo.di.unipi.it/~gervasi/LIP-C-2006/Eccezioni/Controllate/main.html
        Le eccezioni controllate DEVONO essere gestite esplicitamente dal programma, 
        altrimenti il compilatore segnalera' un errore.
        Ogni volta che scriviamo un'istruzione che potrebbe lanciare un'eccezione 
        controllata, allora l'istruzione deve essere racchiusa in un blocco try-catch 
        che possa gestire quel tipo di eccezione; oppure il metodo che contiene 
        l'istruzione deve delegare la gestione dell'eccezione al chiamante, con la 
        clausola throws */    
        //eccezione non controllata:
        //if (isFull()) throw new FullException("Impossibile aggiungere elemento (coda piena).");
        if (isEmpty()) head = 0;
        elements[tail++] = n;
        tail = tail % elements.length;

        assert repOk();
    }

    /**
     * Post-condizioni: restituisce true se la coda e' piena, false altrimenti
     */
    public boolean isFull() {
        return head == tail;
    }

    /**
     * Post-condizioni: restituisce true se la coda e' vuota, false altrimenti
     */
    public boolean isEmpty() {
        return head == -1; //tail == 0 e' sempre vero se head == -1
    }

    /**
     * Effetti collaterali: potrebbe modificare this;
     * Post-condizioni: se la coda e' vuota, solleva un'eccezione di tipo EmptyException,
     *                  altrimenti restituisce l'elemento in testa alla coda e 
     *                  lo elimina da this;
     *                  this = [x1, x2, ... , xk], this_post = [x2, ... , xk];
     */
    public int dequeue() {
        //if (isEmpty()) throw new EmptyException("Impossibile estrarre elemento (coda vuota).");
        int result = elements[head];
        head = (head + 1) % elements.length;
        if (head == tail) {
            head = -1;
            tail = 0;
        }

        assert repOk();
        
        return result;
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
                result += elements[(head + i) % elements.length] + ", ";
            }
            result += elements[(head + i) % elements.length];
        }

        return result + "]";
    }

    /**
     * Post-condizioni: restituisce il numero di elementi presenti nella coda
     */
    public int size() {
        if (isEmpty()) return 0;
        if (isFull()) return elements.length;
        return (tail - head + elements.length) % elements.length;
    }

    /**
     * Post-condizioni: implementa l'invariante di rappresentazione
     */
    private boolean repOk() {
        //guardo IR per implementarlo
        //restituisce true quando le clausole sono tutte vere --> AND logico
        return (size() < elements.length
                && elements != null
                && -1 <= head && head < elements.length
                && 0 <= tail && tail < elements.length 
                && (head != -1 || tail == 0));
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

}