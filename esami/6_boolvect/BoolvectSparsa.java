import java.util.ArrayList;
import java.util.*;
/*
 * Classe concreta che va ad estendere la classe astratta AbstractBoolean e va a rappresentare una BoolvectSparsa. 
 * Una boolvect è considerata sparsa quando il  numero di valori di verità è molto inferiore al numero di valori di falsità.
 * 
 * Siccome il valore in una cella può essere solamente o true, o false possiamo rappresentare un Boolvect 
 * come una lista di indici, posizioni in cui un valore nella cella è T, di conseguenza, tutti gli indici 
 * che non compaiono all'interno dell'attributo boolvect sono sicuramente F.
 * 
 * L'attributo Boolvect, per natura delle sue operazioni, deve essere mutabile, ed è quindi necessario mantenerlo private.
 * Per accedere all'attributo Boolvect restituiamo una lista non mutabile.
 * 
 * Dobbiamo tuttavia fare attenzione che la rappresentazione di List<Integer> Boolvect sia congruente
 * con i dati dimensione, e taglia, e non sia quindi possibile avere, ad esempio un valore in List<Integer>
 * con indice 5000, se la taglia è 30.
 * 
 * AF(Boolvect) = x_n, x_n-1, ..., x_1, x_0 ove x rappresenta il valore booleano T/F in posizione n.
 * IR(Boolvect) = Boolvect != null &&
 *                per ogni Integer x in Boolvect, x <= taglia &&
 *                dimensione == Collections.max(Boolvect)
 */

public class BoolvectSparsa extends AbstractBoolvect {
    
    private List<Integer> Boolvect;

    public Boolean repOk() {
        if (Boolvect == null || Collections.max(Boolvect) > taglia || getDimensione() != Collections.max(Boolvect))
            return false;
        return true;
    }

    /**
     * Istanzia un elemento di tipo BoolvectSparso vuoto, con dimensione 0.
     */
    public BoolvectSparsa() {
        Boolvect = new ArrayList<>();
        setDimensione(0);
    }

    @Override
    public Boolean leggi(int posizione) {
        if (Boolvect.contains(posizione))
            return true;
        return false;
    }

    @Override
    public void scrivi(int posizione, boolean b) throws IndexOutOfBoundsException {
        if (this.taglia < posizione)
            throw new IndexOutOfBoundsException("La posizione inserita è superiore alla taglia del Boolvect");
        if (b == true){
            if (!Boolvect.contains(posizione)) {
                Boolvect.add(posizione);
                if (getDimensione() <= posizione)
                    setDimensione(posizione+1);
            }
        } else {
            if (Boolvect.contains(posizione))
                Boolvect.remove(posizione);
        }
        assert repOk();
    }

    @Override
    public Boolvect and(Boolvect b) throws NullPointerException {
        Objects.requireNonNull(b, "Il boolvector con cui fare il confronto non può essere nullo");
        BoolvectSparsa bool = new BoolvectSparsa();
        if (getDimensione() > b.getDimensione()) {
            for (int i = 0; i < getDimensione(); i++) {
                if (leggi(i) == true && b.leggi(i) == true)
                    bool.scrivi(i, true);
            }
        } else {
            for (int i = 0; i < b.getDimensione(); i++) {
                if (leggi(i) == true && b.leggi(i) == true)
                    bool.scrivi(i, true);
            }
        }
        return bool;
    }

    @Override
    public Boolvect or(Boolvect b) {
        Objects.requireNonNull(b, "Il boolvector con cui fare il confronto non può essere nullo");
        BoolvectSparsa bool = new BoolvectSparsa();
        if (getDimensione() > b.getDimensione()) {
            for (int i = 0; i < getDimensione(); i++) {
                if (leggi(i) == true || b.leggi(i) == true)
                    bool.scrivi(i, true);
            }
        } else {
            for (int i = 0; i < b.getDimensione(); i++) {
                if (leggi(i) == true || b.leggi(i) == true)
                    bool.scrivi(i, true);
            }
        }
        return bool;
    }

    @Override
    public Boolvect xor(Boolvect b) {
        Objects.requireNonNull(b, "Il boolvector con cui fare il confronto non può essere nullo");
        BoolvectSparsa bool = new BoolvectSparsa();
        if (getDimensione() > b.getDimensione()) {
            for (int i = 0; i < getDimensione(); i++) {
                if ((leggi(i) == true && b.leggi(i) == false) || (leggi(i) == false && b.leggi(i) == true))
                    bool.scrivi(i, true);
            }
        } else {
            for (int i = 0; i < b.getDimensione(); i++) {
                if ((leggi(i) == true && b.leggi(i) == false) || (leggi(i) == false && b.leggi(i) == true))
                    bool.scrivi(i, true);
            }
        }
        return bool;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getDimensione()+1; i++) {
            if (Boolvect.contains(i)) s.append("T");
            else s.append("F");
        }
        return s.toString();
    }
}
