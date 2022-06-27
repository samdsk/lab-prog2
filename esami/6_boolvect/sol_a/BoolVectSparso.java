import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class BoolVectSparso implements BoolVect {
    /**
     * Overview: BoolVestSparso è una classe concreta mutabile 
     * che implementa un BoolVect come un vettore di valori booleani sparso
     * 
     * per questo esercizio utilizzo come taglia 10000
     * 
     * 
     * AF = x_n,...,x_i,x_0 dove i è compreso tra 0 e dim()-1, un vettore di 
     *  valori booleani dove ogni X_i è rappresentato da V se i appartiene al vettore
     * 
     * RI = vettore != null && vettore non deve avere duplicati && ogni valore del vettore deve essere minore di taglia
     */


    final private SortedSet<Integer> vettore;
    final private int taglia = 10000;

    public BoolVectSparso(){
        vettore = new TreeSet<>();
        assert repOk();
        
    }

    private boolean repOk() {
        return vettore != null && vettore.last()< taglia;
    }

    @Override
    public int dim() {
        if(vettore.isEmpty()) return 0;
        return ((int) vettore.last()) +1;
    }

    @Override
    public int taglia() {
        return taglia;
    }

    @Override
    public void scrivi(boolean b, int pos) throws IndexOutOfBoundsException {
        if(pos<0 || pos >= taglia) throw new IndexOutOfBoundsException("Posizione non valida");
        vettore.add(pos);
        assert repOk();
    }

    @Override
    public boolean leggi(int pos) throws IndexOutOfBoundsException {
        if(pos<0 || pos >= taglia) throw new IndexOutOfBoundsException("Posizione non valida");
        if(vettore.contains(pos)) return true;
        return false;
    }

    @Override
    public void and(BoolVect B) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(B,"BoolVect B non può essere null!");

        int min_dim = this.dim() < B.dim() ? this.dim() : B.dim();

        for (int i = 0; i < min_dim; i++) {
            if(!(vettore.contains(i) && B.leggi(i))) vettore.remove(i);
        }
        assert repOk();
    }

    @Override
    public void or(BoolVect B) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(B,"BoolVect B non può essere null!");
        
        int max_dim = this.dim() < B.dim() ? B.dim() : this.dim();

        for (int i = 0; i < max_dim; i++) {
            if(B.leggi(i)) vettore.add(i);
        }
        assert repOk();
    }

    @Override
    public void xor(BoolVect B) throws NullPointerException, IllegalArgumentException {

        int max_dim = this.dim() < B.dim() ? B.dim() : this.dim();

        for (int i = 0; i < max_dim; i++) {
            if((vettore.contains(i) && !B.leggi(i)) || (!vettore.contains(i) && B.leggi(i))) vettore.add(i);
            if((!vettore.contains(i) && !B.leggi(i)) || (vettore.contains(i) && B.leggi(i))) vettore.remove(i);
        }
        assert repOk();
    }

    @Override
    public String toString(){

        StringBuilder str = new StringBuilder();
        for (int i = dim()-1; i>=0; i--) {
            str.append(vettore.contains(i) ? "V" : "F");
        }

        return str.toString();
    }
    
}
