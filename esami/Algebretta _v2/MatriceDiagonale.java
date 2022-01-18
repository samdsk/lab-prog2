public class MatriceDiagonale extends AbsMatrice{
    //fields
    /**un array di tipo Vettore */
    private final Vettore[] matrice;
    /**dimensione della matrice */
    private final int dim;

    /**
     * Effects: restituisce la matrice diagonale della matrice this
     * Es. 1 2 3    1 0 0
     *     4 5 6 -> 0 5 0
     *     7 8 9    0 0 9
     */
    public MatriceDiagonale(Matrice m){
        matrice = new Vettore[m.dim()];
        dim = m.dim();
        for (int i = 0; i < dim; i++) {
            int[] temp = new int[dim];
            temp[i] = m.val(i,i);
            matrice[i] = new VettoreDenso(temp);
        }
    }


    @Override
    public int dim() {
        return this.dim;
    }

    @Override
    public int val(int i, int j) {
        return matrice[i].val(j);
    }

    @Override
    public Vettore val(int i) {
        return matrice[i];
    }

    @Override
    public Matrice per(final Matrice m){
        if(m==null) throw new NullPointerException();
        if(m.dim()!=this.dim) throw new IllegalArgumentException("Matrici sono di dimensione diversa! this:"+this.dim+" m:"+m.dim());

        MatriceTrasposta t = new MatriceTrasposta(m);
        MatriceDiagonale output = new MatriceDiagonale(dim);
        int[] temp_array = new int[dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {                
                temp_array[j] = ((VettoreDenso)this.matrice[i]).per_helper(t.val(j));
            }
            output.matrice[i] = new VettoreDenso(temp_array);
        }
        return output;
    }

    /**
     * Effects: restituisce la matrice ottenuta facendo il prodotto tra this e uno scalare 
     */
    @Override
    public Matrice per(final int alpha){
        MatriceDiagonale output = new MatriceDiagonale(dim);

        for (int i = 0; i < dim; i++) {
            output.matrice[i] = this.matrice[i].per(alpha);
        }

        return output;
    }


    @Override
    public Matrice piu(Matrice m) {
        // TODO Auto-generated method stub
        return null;
    }



}