import java.util.Objects;

public class FunzioneNeg implements Funzione{
    /**
     * Overview la FunzioneNeg implementa l'interfaccia Funzione,
     * calcola valore negativo del dato intero
     * 
     * RI = argomenti != null && argomenti.length == numeroArgomenti
     */

    final private int numeroArgomenti = 1;
    final private Funzione[] argomenti = new Funzione[numeroArgomenti];

    // RI
    public boolean repOk() {
        return argomenti != null && argomenti.length == numeroArgomenti;
    }
    
    @Override
    public int numeroArgomenti() {
        return numeroArgomenti;
    }

    @Override
    public void argomento(Funzione funzione, int posizione) {
        Objects.requireNonNull(funzione, "Funzione non può essere null.");
        if (posizione < numeroArgomenti || posizione >= numeroArgomenti)
            throw new IllegalArgumentException("Posizione non valida");

        argomenti[posizione] = funzione;

    }

    @Override
    public int valuta() {
        return -argomenti[0].valuta();
    }
}
