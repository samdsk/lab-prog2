import java.util.List;
import java.util.Objects;

/**
 * 
 */
public class BoolvectDenso extends AbstractBoolvect{

    private boolean[] Boolvect;

/**
 * L'implementazione con Boolean[] non mi piace molto, utilizzare taglia come
 * grandezza non è efficiente in quanto mantengo sempre un'array di dimensione
 * Integer.MAX_VALUE anche nel caso la dimensione sia bassa. sarebbe meglio
 * reinstanziare un array di boolean quando necessario, ma non ho avuto tempo di
 * farlo Utilizziamo quindi il valore 1000000 al posto di Integer.MAX_VALUE
 */
    public BoolvectDenso(){
        Boolvect = new boolean[1000000];
        setDimensione(0);
    }

    /**
     * non è necessario controllare che posizione < dimensione in quanto in quel caso sarà tornato false
     */
    @Override
    public Boolean leggi(int posizione) {
        return Boolvect[posizione];
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getDimensione() + 1; i++) {
            if (Boolvect[i] == true)
                s.append("T");
            else
                s.append("F");
        }
        return s.toString();
    }

    @Override
    public void scrivi(int posizione, boolean b) throws IndexOutOfBoundsException {
        if (posizione > taglia)
            throw new IndexOutOfBoundsException("La posizione inserita è maggiore della taglia del Boolvector");
        if ((b == true) && posizione > getDimensione()-1) setDimensione(posizione+1);
        Boolvect[posizione] = b;
    }

    @Override
    public Boolvect and(Boolvect b) throws NullPointerException {
        Objects.requireNonNull(b, "Il boolvector con cui fare il confronto non può essere nullo");
        BoolvectDenso bool = new BoolvectDenso();
        if (getDimensione() > b.getDimensione()) {
            for (int i = 0; i < getDimensione(); i++){
            if (leggi(i) == true && b.leggi(i) && true) {
                bool.scrivi(i, true);
            }
        }
    } else {
        for (int i = 0; i < b.getDimensione(); i++) {
            if (leggi(i) == true && b.leggi(i) && true) {
                bool.scrivi(i, true);
            }
        }
    }
        return bool;
    }

    @Override
    public Boolvect or(Boolvect b) {
        Objects.requireNonNull(b, "Il boolvector con cui fare il confronto non può essere nullo");
        BoolvectDenso bool = new BoolvectDenso();
        if (getDimensione() > b.getDimensione()) {
            for (int i = 0; i < getDimensione(); i++) {
                if (leggi(i) == true || b.leggi(i) && true) {
                    bool.scrivi(i, true);
                }
            }
        } else {
            for (int i = 0; i < b.getDimensione(); i++) {
                if (leggi(i) == true || b.leggi(i) && true) {
                    bool.scrivi(i, true);
                }
            }
        }
        return bool;
    }

    @Override
    public Boolvect xor(Boolvect b) {
        Objects.requireNonNull(b, "Il boolvector con cui fare il confronto non può essere nullo");
        BoolvectDenso bool = new BoolvectDenso();
        if (getDimensione() > b.getDimensione()) {
            for (int i = 0; i < getDimensione(); i++) {
                if ((leggi(i) == false && b.leggi(i) && true) || (leggi(i) == true && b.leggi(i) == false)){
                    bool.scrivi(i, true);
                }
            }
        } else {
            for (int i = 0; i < b.getDimensione(); i++) {
                if ((leggi(i) == false && b.leggi(i) && true) || (leggi(i) == true && b.leggi(i) == false)) {
                    bool.scrivi(i, true);
                }
            }
        }
        return bool;
    }
}
