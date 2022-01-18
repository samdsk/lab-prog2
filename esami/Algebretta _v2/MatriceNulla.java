public class MatriceNulla extends AbsMatrice{
    //fields
    /**un array di tipo Vettore */
    private final Vettore[] matrice;
    /**dimensione della matrice */
    private final int dim;

    /**
     * inizializza this come una matrice nulla di dimensione size
     */
    public MatriceNulla(final int size){
        dim = size;
        matrice = new VettoreDenso[dim];

        for(int i=0;i<dim;i++) matrice[i] = new VettoreDenso(dim);

    }

    @Override
    public int dim() {
        return this.dim;
    }

    @Override
    public int val(int i, int j) {        
        return 0;
    }

    @Override
    public Vettore val(int i) {        
        return new VettoreNullo(dim);
    }

    @Override
    public Matrice piu(Matrice m) {        
        return m;
    }

    @Override
    public Matrice per(int alpha) {        
        return this;
    }

    @Override
    public Matrice per(Matrice m) {        
        return this;
    }
}
