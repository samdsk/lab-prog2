public interface BoolVect{
    /**
     * L'interfaccia BoolVect rappresenta il contratto di un vettore di valori booleani immutabile
     */


    /**
     * Restiuisce la dimesione di questo BoolVect
     * @return la dimesione
     */
    int dim();

    /**
     * Restituisce la taglia cioè la dimensione massima che questo BoolVect può assumere
     * @return la taglia
     */
    int taglia();

    /**
     * Restituisce un nuovo BoolVect nel quale è stato modificato 
     * valore booleano nella posizione "pos" di questo BoolVect
     * @param b valore booleano
     * @param pos posizione 
     * @return il nuovo BoolVect
     * @throws IndexOutOfBoundsException se posizione non valida
     */
    BoolVect scrivi(final boolean b, final int pos) throws IndexOutOfBoundsException;

    /**
     * Restituisce il valore booleano nella posizione pos di questo BoolVect
     * @param pos posizione
     * @return il valore booleano
     * @throws IndexOutOfBoundsException se posizione non valida
     */
    boolean leggi(final int pos) throws IndexOutOfBoundsException;

    /**
     * Restituisce un nuovo BoolVect eseguendo op. binaria AND tra questo BoolVect e BoolVect B
     * @param B il BoolVect
     * @return il nuovo BoolVect
     * @throws NullPointerException se BoolVect B è null
     * @throws IllegalArgumentException se BoolVect hanno taglie diverse
     */
    BoolVect and(final BoolVect B) throws NullPointerException, IllegalArgumentException;

    /**
     * Restituisce un nuovo BoolVect eseguendo op. binaria OR tra questo BoolVect e BoolVect B
     * @param B il BoolVect
     * @return il nuovo BoolVect
     * @throws NullPointerException se BoolVect B è null
     * @throws IllegalArgumentException se BoolVect hanno taglie diverse
     */
    BoolVect or(final BoolVect B) throws NullPointerException, IllegalArgumentException;

    /**
     * Restituisce un nuovo BoolVect eseguendo op. binaria XOR tra questo BoolVect e BoolVect B
     * @param B il BoolVect
     * @return il nuovo BoolVect
     * @throws NullPointerException se BoolVect B è null
     * @throws IllegalArgumentException se BoolVect hanno taglie diverse
     */
    BoolVect xor(final BoolVect B) throws NullPointerException, IllegalArgumentException;
}