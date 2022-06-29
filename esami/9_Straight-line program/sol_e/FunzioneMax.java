import java.util.Objects;

public class FunzioneMax implements Funzione {
    /**
     * Overview la FunzioneMax implementa l'interfaccia Funzione, 
     * calcola il massimo tra tre interi
     * 
     * RI = argomenti != null && argomenti.length == numeroArgomenti 
     */

    final private int numeroArgomenti = 3;
    final private Funzione[] argomenti = new Funzione[numeroArgomenti];

    //RI
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
        if(posizione<numeroArgomenti || posizione>= numeroArgomenti)
            throw new IllegalArgumentException("Posizione non valida");

        argomenti[posizione] = funzione;
        
    }

    @Override
    public int valuta() {
        int max = argomenti[0].valuta();
        for(Funzione f : argomenti)
            if (max < f.valuta())
                max = f.valuta();

        return max;
    }
    
}
