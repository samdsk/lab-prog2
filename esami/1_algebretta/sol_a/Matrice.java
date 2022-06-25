public interface Matrice {
    /**
     * Overview: L'interfaccia matrice descrive un contratto per una matrice immutabile,
     * quadratica a valori interi
     */

    /**
     * Restituisce la dimensione di questo matrice
     * @return la dimensione
     */
    int dim();

    /**
     * Restituisce il valore nella posizione 
     * i-esima riga e j-esima colonna di questo matrice
     * 
     * @param i riga i-esima
     * @param j colonna j-esima
     * @return il valore nella posizione i,j
     * @throws IndexOutOfBoundsException se gli indici i,j sono fuori range
     */
    int val(final int i, final int j)throws IndexOutOfBoundsException;

    /**
     * Restituisce una nuova matrice ottenuta 
     * eseguendo il prodotto scalare con questa matrice e scalare alpha
     * @param alpha lo scalare
     * @return la nuova matrice
     */
    Matrice per(final int alpha);

    /**
     * Restituisce una nuova matrice ottenuta
     * eseguendo il prodotto tra questa matrice e la matrice m
     * @param m matrice con cui fare il prodotto
     * @return la nuova matrice
     * @throws NullPointerException se Matrice m è null
     * @throws IllegalArgumentException se le matrici hanno dimensioni diverse
     */
    Matrice per(final Matrice m)throws NullPointerException, IllegalArgumentException;

    /**
     * Restituisce un nuovo vettore ottenuto
     * eseguendo il prodotto tra questa matrice e il vettore v
     * @param v vettore con cui fare il prodotto
     * @return il nuovo vettore
     * @throws NullPointerException se Vettore v è null
     * @throws IllegalArgumentException se matrice e vettore hanno dimensioni diverse
     */
    Vettore per(final Vettore v)throws NullPointerException, IllegalArgumentException;

    /**
     * Restituisce una nuova matrice ottenuta
     * eseguendo la somma tra questa matrice e la matrice m
     * @param m matrice con cui fare la somma
     * @return la nuova matrice
     * @throws NullPointerException se Matrice m è null
     * @throws IllegalArgumentException se le matrici hanno dimensioni diverse
     */
    Matrice piu(final Matrice m)throws NullPointerException, IllegalArgumentException;
}
