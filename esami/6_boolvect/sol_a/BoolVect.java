public interface BoolVect{
    /**
     * L'interfaccia BoolVect rappresenta il contratto di un vettore 
     * di valori booleani
     */


    /**
     * Restiuisce la dimesione di questo BoolVect
     * @return la dimesione
     */
    int dim();

    /**
     * Restituisce la taglia cioè la dimensione massima che questo 
     * BoolVect può assumere
     * @return la taglia
     */
    int taglia();

    /**
     * Modifica questo BoolVect nella posizione "pos" 
     * aggiungendo valore booleano b
     * @param b valore booleano
     * @param pos posizione    
     * @throws IndexOutOfBoundsException se posizione non valida
     */
    void scrivi(final boolean b, final int pos) throws IndexOutOfBoundsException;

    /**
     * Restituisce il valore booleano nella posizione pos di questo BoolVect
     * @param pos posizione
     * @return il valore booleano
     * @throws IndexOutOfBoundsException se posizione non valida
     */
    boolean leggi(final int pos) throws IndexOutOfBoundsException;

    /**
     * Modifica questo BoolVect eseguendo op. binaria AND tra questo BoolVect 
     * e BoolVect B
     * @param B il BoolVect
     * @throws NullPointerException se BoolVect B è null
     * @throws IllegalArgumentException se BoolVect hanno taglie diverse
     */
    void and(final BoolVect B) throws NullPointerException, IllegalArgumentException;

    /**
     * Modifica questo BoolVect eseguendo op. binaria OR tra questo BoolVect 
     * e BoolVect B
     * @param B il BoolVect
     * @throws NullPointerException se BoolVect B è null
     * @throws IllegalArgumentException se BoolVect hanno taglie diverse
     */
    void or(final BoolVect B) throws NullPointerException, IllegalArgumentException;

    /**
     * Modifica questo BoolVect eseguendo op. binaria XOR tra questo BoolVect 
     * e BoolVect B
     * @param B il BoolVect
     * @throws NullPointerException se BoolVect B è null
     * @throws IllegalArgumentException se BoolVect hanno taglie diverse
     */
    void xor(final BoolVect B) throws NullPointerException, IllegalArgumentException;
}