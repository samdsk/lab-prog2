import java.util.List;

/**
 * Classe astratta che implementare parzialmente un Boolvect.
 * Un Boolvect è rappresentato da:  - una sequenza di valori T/F, 
 *                                  - una dimensione che rappresenta 1 + posizione più grande in cui si trova un valore di verità uguale a vero
 *                                  - una taglia che rappresenta la massima dimensione che può raggiungere e può essere infinita <= Integer.MaxValue (nel caso di matrice sparsa)
 * 
 * 
 * L'attributo taglia è final, ed è quindi ragionevole omettere il getter e mantenerlo public
 * L'attributo dimensione è mutabile, è quindi necessario implementare un getter, setter, e mantenere la sua rappresentazione privata
 * 
 * AF(AbstractBoolvect): "Boolvector di dimensione " + dimensione + " e taglia " + taglia
 * IR(AbstractBoolvect): dimensione >= 0 &&
 *                       taglia > 0 &&
 *                       dimensione > taglia
 * 
 */
public abstract class AbstractBoolvect implements Boolvect {
    private int dimensione;        
    public final int taglia = 100000;
    
    /**
     * Questo valore è inserito qua per correttezza, in verità dovrebbe essere
     * Integer.MAX_Value, tuttavia se fosse questo il caso, avremmo un VM
     * overflow, o comunque un errore di compilazione. 
     * ai fini dell'esericzio utilizzeremo 100000 come taglia.
     * 
     * Un'alternativa sarebbe utilizzare un altro tipo per rappresentare la taglia, ad esempio long.
     * In quel caso:
     * public final int taglia = Integer.MAX_VALUE; 
     * sarebbe la dichiarazione corretta.
     */

    public Boolean repOk() {
        if (dimensione < 0 || taglia < 0 || dimensione > taglia) {
            return false;
        }
        return true;
    }
    public void setDimensione(int dimensione) {
        this.dimensione = dimensione;
    }

    @Override
    public int getDimensione() {
        return this.dimensione;
    }
    
}