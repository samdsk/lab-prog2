import java.util.Collections;
import java.util.Objects;

public class VettoreNullo implements Vettore{
    /**
     * Overview: VettoreNullo è una classe concreta immutabile 
     * che rappresenta un vettore nullo
     */

    /**
     * AF = (0, ... , 0) n volte zero dove n è la dimensione del vettore
     * RI = dim > 0
     */

    final private int dim;

    public VettoreNullo(final int n){
        if(n<=0) throw new IllegalArgumentException("Dimensione deve essere maggiore di 0");
        this.dim = n;

        assert repOk();
    }

    public boolean repOk(){
        return dim > 0;
    }

    @Override
    public int dim() {
        return this.dim;
    }

    @Override
    public int val(int i) {
        if(i<0 || i>= dim) throw new IndexOutOfBoundsException("indice non può essere >= dimensione del vettore oppure è negativo");
        return 0;
    }

    @Override
    public Vettore per(int alpha) {
        return new VettoreNullo(this.dim);
    }

    @Override
    public Vettore piu(Vettore v) {
        Objects.requireNonNull(v,"Vettore v non può essere null!");
        if(v.dim() != this.dim) throw new IllegalArgumentException("Vettore v deve avere la stessa dimensione.");
        return v;
    }

    @Override
    public String toString(){
        return Collections.nCopies(dim, "0").toString().replace('[', '(').replace(']', ')');
    }
    
}
