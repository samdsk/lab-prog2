package Algebretta;

public class Vettore {
    private final int dim;
    private final int[] vettore;

    public Vettore(int n){
        dim = n;
        vettore = new int[dim];
    }

    public int dim() {
        return this.dim;
    }

    public int val(int i) {
        return vettore[i];
    }

    public Vettore per(int alpha) {
        Vettore output = new Vettore(dim);
        for (int i = 0; i < dim; i++) {
            output.vettore[i] = vettore[i]*alpha;
        }

        return output;
    }


    public Vettore piu(Vettore v) {
        if(v==null) throw new NullPointerException();
        Vettore small;
        Vettore large;

        if(this.dim < v.dim){
            small = this;
            large = v;
        }else{
            large = this;
            small = v;
        }

        Vettore output = new Vettore(large.dim);
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
