package Algebretta;

import java.util.Arrays;

public class VettoreDenso{

    private final int dim;
    private final int[] vettore;

    public VettoreDenso(int n){
        dim = n;
        vettore = new int[dim];
    }

    public VettoreDenso(int[] arr){
        dim = arr.length;
        vettore = Arrays.copyOf(arr, dim);
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


    public String toString() {        
        return vettore.toString();
    }

}