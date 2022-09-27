import java.util.Objects;

public class FunzioneNeg implements Funzione{
    private int a;
    private final int size = 1;

    @Override
    public int numeroArgomenti() {
        return size;
    }

    @Override
    public void argomento(Funzione funzione, int posizione) {
        if(posizione != 0) throw new IllegalArgumentException("Posizione non può essere negativa ne maggiore di 0!");
        a = Objects.requireNonNull(funzione).valuta();
    }

    @Override
    public int valuta() {
        return -a;
    }

    @Override
    public String toString(){
        return "Neg";
    }
}
