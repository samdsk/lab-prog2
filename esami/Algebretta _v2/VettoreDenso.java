import java.util.Arrays;
import java.util.Objects;

public class VettoreDenso extends AbsVettore{
    //Overview VettoreDenso è un tipo immutabile, rappresenta un vettore a valori interi di dimensione n;
    //vettore di dimensione 4 -> 1,2,3,4

    /**
     * AF(vettore,dimensione) = (vettore[0],...,vettore[i],...) dove i è 0<=i<dimensione
     * 
     * RI(v) = v.dim == v.vettore.length && v.dim>=1
     */

    //fields

    //dimensione del vettore
    private final int dim;
    //vettore di interi
    private final int[] vettore;

    //contructors
    /**
     * inizializza this come un vettore di dimensione n
     */
    public VettoreDenso(int n){
        dim = n;
        vettore = new int[dim];
        assert repOk();
    }

    /**
     * inizializza this con dato array arr 
     */
    public VettoreDenso(int[] arr){
        dim = arr.length;
        vettore = Arrays.copyOf(arr, dim);
        assert repOk();
    }

    /**
     * inizializza this con il VettoreDenso v
     */
    public VettoreDenso(Vettore v){
        dim = v.dim();
        vettore = new int[v.dim()];
        for (int i = 0; i < dim(); i++) {
            vettore[i] = v.val(i);
        }
        
        assert repOk();
    }

    public int[] copy(){
        return vettore.clone();
    }

    /**
     * RI
     */
    public boolean repOk(){
        if(vettore!=null 
        && dim>=1
        && vettore.length == dim)
        return true;
        
        return false;
    }

    /**
     * Effects: restituisce this.dim
     */
    @Override
    public int dim() {
        return this.dim;
    }

    /**
     * Effects: restituisce il valore intero della posizione i del this.vettore
     */
    @Override
    public int val(int i) {
        return vettore[i];
    }

    /**
     * Effects: restituisce un nuovo vettoredenso costruito facendo prodotto cartesiano con this
     */
    @Override
    public Vettore per(int alpha) {
        VettoreDenso output = new VettoreDenso(dim);
        for (int i = 0; i < dim; i++) {
            output.vettore[i] = vettore[i]*alpha;
        }
        return output;
    }

    /**
     * Effects: restituisce intero costruito facendo prodotto tra termini this e v e sommando il prodotti parziali
     */    
    public int per_helper(Vettore v){
        if(v==null) throw new NullPointerException("Vettore non può essere null.");
        int output = 0;
        
        for (int i = 0; i < dim; i++) {
            output += this.vettore[i]*v.val(i);
        }

        return output;
    }

    /**
     * Effects: restituisce vettoredenso costruito facendo somma vettoriale tra this e il vettoredenso v
     */
    public Vettore piu(Vettore v) {
        if(v==null) throw new NullPointerException("Vettore non può essere null.");

        VettoreDenso output = new VettoreDenso(dim());
        for(int i=0;i<dim();i++){
            vettore[i] = val(i)+v.val(i);
        }

        return output;
    }



    @Override
    public boolean equals(Object o) {
        if(!(o instanceof VettoreDenso)) return false;

        VettoreDenso v = (VettoreDenso) o;

        if(this.dim != v.dim) return false;
        for(int i=0;i<dim;i++){
            if(this.vettore[i] != v.vettore[i]) return false;
        }

        return true;
    }



    @Override
    public int hashCode() {
        return Objects.hash(dim,vettore);
    }

}