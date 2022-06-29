/**
 * Funzione stabilisce l'interfaccia per una funzione da valori interi a intero
 */
public interface Funzione {
    /**
     * Restituisce il numero di argomenti di questa funzione
     * @return numero di argomenti 
     */
    int numeroArgomenti();

    /**
     * Assegna alla funzione data la posizione nella lista degli argomenti
     * @param funzione la Funzione 
     * @param posizione posizione a cui assegnare la funzione data nell'argomento di questa Funzione
     * @throws NullPointerException se funzione è null
     * @throws IllegalArgumentException se posizione non valida
     */
    void argomento(Funzione funzione, int posizione);

    /**
     * Restituisce il intero ottenuto valutando questa funzione
     * @return risultato
     */
    int valuta();

    /**
     * costruisce una Funzione costante
     * @param valore con cui inizializzare la Funzione
     * @return la Funzione costante
     * 
     */
    static Funzione costante(final int valore) {
        return new Funzione() {
            public int numeroArgomenti() {
                return 0;
            }

            public void argomento(Funzione funzione, int posizione) {
                throw new UnsupportedOperationException();
            }

            public int valuta() {
                return valore;
            }
        };
    }
    
}