import java.util.Objects;

public class FunzioneMax implements Funzione{

    private final Funzione arr[];
    private final int size = 2;

    public FunzioneMax(){
        arr = new Funzione[size];
    }

    @Override
    public int numeroArgomenti() {
        return size;
    }

    @Override
    public void argomento(Funzione funzione, int posizione) {
        if(!(posizione>=0 && posizione < arr.length)) throw new IllegalArgumentException("Posizione non può essere negativa ne maggiore di 1!");
        arr[posizione] = Objects.requireNonNull(funzione);        
    }

    @Override
    public int valuta() {
        return Math.max(arr[0].valuta(), arr[1].valuta());
    }

    @Override
    public String toString(){
        return "Max";
    }
}
