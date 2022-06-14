
/**
 *  Overview:   Questa interfaccia va a definire i comportamenti che devono avere le classi che la implementano.
 *              Un Boolvect, o vettore di valori booleani, è una sequenza di valori di verità, ciascuno delle quali deve essere
 *              T/F. Come tale, ogni implementazione di BoolVect deve essere in grado di leggere e scrivere in una i-esima posizione, cosiccome
 *              svolgere un'operazione &&, || e ^ con un'altra Boolvect
 */

/**
 * Si noti che le operazioni booleane (eg. &&, ||, ^) possono essere svolte anche su due boolvect di grandezza diversa, per 
 * il boolvect con grandezza minore verrà assunto valore F, per ogni posizione oltre la sua taglia
 * 
 */
public interface Boolvect {
    
    /**
     * Restituisce un valore di verità T/F di un Boolvect, data la posizione dell'indice
     * @param posizione: di cui si vuole sapere il valore di verità
     * @return  un valore T/F in base al valore di verità in posizione.
     *          se il valore posizione è maggiore della grandezza del boolvect, per convenzione viene restituito false.
     */
    public Boolean leggi(int posizione);

    /**
     * Scrive un valore di verità T/F in una data posizione di un Boolvect.
     * Se il valore che inseriamo è T, e la posizione è maggiore della dimensione, la dimensione deve essere aggiornata.
     * @param posizione in cui si vuole scrivere
     * @param b un valore booleano T/F
     * @throws IndexOutOfBoundsException se la posizione in cui si vuole scrivere è maggiore della taglia del boolvect.
     * 
     * Un boolean è un tipo primitivo, non può essere null, quindi non c'è bisogno di un controllo.
     */
    public void scrivi(int posizione, boolean b) throws IndexOutOfBoundsException;

    public int getDimensione();

    /**
     * Si noti che le operazioni booleane (eg. &&, ||, ^) possono essere svolte
     * anche su due boolvect di grandezza diversa, per il boolvect con grandezza
     * minore verrà assunto valore F, per ogni posizione oltre la sua taglia
     * 
     */

    /**
     * Restituisce un nuovo Boolvect ottenuta svolgendo l'operazione && tra questo
     * boolvect ed un boolvect dato
     * 
     * @param b il Boolvect con cui si vuole svolgere l'operazione && posizione per
     *          posizione
     * @return un nuovo Boolvect
     * @throws NullPointerException se il boolvect inserito è null
     */
    Boolvect and(final Boolvect b) throws NullPointerException;

    /**
     * Restituisce un nuovo Boolvect ottenuta svolgendo l'operazione || tra questo
     * boolvect ed un boolvect dato
     * 
     * @param b il Boolvect con cui si vuole svolgere l'operazione || posizione per
     *          posizione
     * @return un nuovo Boolvect
     * @throws NullPointerException se il boolvect inserito è null
     */
    Boolvect or(final Boolvect b);

    /**
     * Restituisce un nuovo Boolvect ottenuta svolgendo l'operazione ^ tra questo
     * boolvect ed un boolvect dato
     * 
     * @param b il Boolvect con cui si vuole svolgere l'operazione ^ posizione per
     *          posizione
     * @return un nuovo Boolvect
     * @throws NullPointerException se il boolvect inserito è null
     */
    Boolvect xor(final Boolvect b);

}