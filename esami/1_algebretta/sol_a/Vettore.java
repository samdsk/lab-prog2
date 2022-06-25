public interface Vettore {
    /**
     * Overview: l'interfaccia rappresenta il contratto di un vettore immutabile 
     * a valori interi e fornisce seguenti operazioni su di essi: 
     * prodotto scalare, somma tra due vettori, 
     * verificare la dimensione del vettore,
     * verificare il valore della posizione i-esima nel vettore
     */

     /**
      * Restituisce la dimensione di questo vettore
      * @return la dimensione
      */
    int dim();

    /**
     * Restituisce il valore nella posizione i-esima del questo vettore
     * @param i posizione del valore da verificare
     * @return valore della posizione i
     * @throws IndexOutOfBoundsException se indice i è maggiore uguale al dimensione del vettore oppure s'è negativo
     */
    int val(final int i)throws IndexOutOfBoundsException;

    /**
     * Restituisce un nuovo Vettore ottenuto eseguendo 
     * il prodotto scalare su questo vettore per alpha 
     * 
     * @param alpha scalare con cui eseguire il prodotto scalare
     * @return il nuovo Vettore
     */
    Vettore per(final int alpha);

    /**
     * Restituisce un nuovo Vettore ottenuto eseguendo 
     * la somma tra questo vettore e il vettore "v"
     * @param v il secondo vettore 
     * @return il nuovo vettore
     * @throws NullPointerException se il vettore "v" è null
     * @throws IllegalArgumentException se il vettore "v" ha dimensione diversa da questo vettore
     */
    Vettore piu(final Vettore v)throws NullPointerException, IllegalArgumentException;
}