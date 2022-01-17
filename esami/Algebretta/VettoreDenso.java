package Algebretta;

import java.util.Arrays;
import java.util.Objects;

public class VettoreDenso{
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
     * inizializza this con array arr dato
     */
    public VettoreDenso(int[] arr){
        dim = arr.length;
        vettore = Arrays.copyOf(arr, dim);
        assert repOk();
    }

    /**
     * inizializza this con il VettoreDenso v
     */
    public VettoreDenso(VettoreDenso v){
        dim = v.dim;
        vettore = Arrays.copyOf(v.vettore, dim);
        assert repOk();
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
    public int dim() {
        return this.dim;
    }

    /**
     * Effects: restituisce il valore intero della posizione i del this.vettore
     */
    public int val(int i) {
        return vettore[i];
    }

    /**
     * Effects: restituisce un nuovo vettoredenso costruito facendo prodotto cartesiano con this
     */
    public VettoreDenso per(int alpha) {
        VettoreDenso output = new VettoreDenso(dim);
        for (int i = 0; i < dim; i++) {
            output.vettore[i] = vettore[i]*alpha;
        }
        return output;
    }

    /**
     * Effects: restituisce intero costruito facendo prodotto tra termini this e v e sommando il prodotti parziali
     */
    public int prodotto(VettoreDenso v){
        if(v==null) throw new NullPointerException("Vettore non può essere null.");
        int output = 0;
        for (int i = 0; i < dim; i++) {
            output += this.vettore[i]*v.vettore[i];
        }

        return output;
    }

    /**
     * Effects: restituisce vettoredenso costruito facendo somma vettoriale tra this e il vettoredenso v
     */
    public VettoreDenso piu(VettoreDenso v) {
        if(v==null) throw new NullPointerException("Vettore non può essere null.");
        VettoreDenso small;
        VettoreDenso large;

        if(this.dim < v.dim){
            small = this;
            large = v;
        }else{
            large = this;
            small = v;
        }

        VettoreDenso output = new VettoreDenso(large.dim);
        for(int i=0;i<small.dim;i++){
            output.vettore[i] = small.vettore[i]+large.vettore[i];
        }

        for(int i=small.dim;i<large.dim;i++){
            output.vettore[i] = 0;
        }

        return output;
    }

    /**
     * Effects: restituisce una String di formato vettore[0],...,vettore[i] dove i 0<=i<dim
     */
    public String vettoreToString(){
        String output = "";
        for (int i = 0; i < dim; i++) {
            output += vettore[i];
            if(i<dim-1) output+= ", ";
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

    /**AF */
    @Override
    public String toString() {
        String output = "(";
        for (int i = 0; i < dim; i++) {
            output += vettore[i];
            if(i<dim-1) output+= ", ";
        }

        return output += ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(dim,vettore);
    }

}