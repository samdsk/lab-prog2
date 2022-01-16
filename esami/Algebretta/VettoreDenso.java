package Algebretta;

import java.util.Arrays;
import java.util.Objects;

public class VettoreDenso{
    //Overview VettoreDenso è un tipo immutabile, rappresenta un vettore a valori interi di dimensione n;
    //vettore di dimensione 4 -> 1,2,3,4

    /**
     * AF(vettore,dimensione) = vettore[0],...,vettore[i],... dove i è 0<=i<dimensione
     * 
     * RI(v) = v.dim == v.vettore.length && v.dim>=1
     */

    private final int dim;
    private final int[] vettore;

    public VettoreDenso(int n){
        dim = n;
        vettore = new int[dim];
        assert repOk();
    }

    public VettoreDenso(int[] arr){
        dim = arr.length;
        vettore = Arrays.copyOf(arr, dim);
        assert repOk();
    }
    public VettoreDenso(VettoreDenso v){
        dim = v.dim;
        vettore = Arrays.copyOf(v.vettore, dim);
        assert repOk();
    }

    public boolean repOk(){
        if(vettore!=null 
        && dim>=1
        && vettore.length == dim)
        return true;
        
        return false;
    }


    public int dim() {
        return this.dim;
    }

    public int val(int i) {
        return vettore[i];
    }

    public VettoreDenso per(int alpha) {
        VettoreDenso output = new VettoreDenso(dim);
        for (int i = 0; i < dim; i++) {
            output.vettore[i] = vettore[i]*alpha;
        }

        return output;
    }

    public int prodotto(VettoreDenso v){
        int output = 0;
        for (int i = 0; i < dim; i++) {
            output += this.vettore[i]*v.vettore[i];
        }

        return output;
    }

    public VettoreDenso piu(VettoreDenso v) {
        if(v==null) throw new NullPointerException();
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
    public String toString() {
        String output = "(";
        for (int i = 0; i < dim; i++) {
            output += vettore[i];
            if(i<dim-1) output+= ",";
        }

        return output += ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(dim,vettore);
    }

}