import java.util.Objects;

public class MatriceDensa implements Matrice {
    //Overview Matrice è un tipo immutabile, rappresenta una matrice quadratica NxN a valori interi di dimensione n
    //è strutturato come un vettore di dimensione n di VettoriDensi di dimensione n
    //Es. Matrice di dimesione 2 
    //  1 2
    //  3 4

    /**
     * AF(matrice, dim) = 
     *      [ matrice[0][0],...,matrice[0][j],...,matrice[0][dim-1] ; matrice[i][0],...,matrice[i][j],...,matrice[i][dim-1] ]
     *  dove i,j sono 0<=i<dim e 0<=j<dim
     * 
     * RI(m) = m.dim >= 1 && m.matrice.lenth == m.dim && m.matrice != null
     */

    //fields
    /**un array di tipo Vettore */
    private final Vettore[] matrice;
    /**dimensione della matrice */
    private final int dim;

    /**
     * inizializza this come una matrice nulla di dimensione size
     */
    public MatriceDensa(final int size){
        dim = size;
        matrice = new VettoreDenso[dim];

        for(int i=0;i<dim;i++) matrice[i] = new VettoreDenso(dim);

        assert repOk();
    }

    /**
     * inizializza this come una matrice quadratica equivalente a m
     * throws NullPointerException se m == null
     */
    public MatriceDensa(final Matrice m){
        if(m==null) throw new NullPointerException("Matrice m non può essere null!");
        dim = m.dim();
        matrice = new VettoreDenso[dim];

        for (int i = 0; i < matrice.length; i++){
            matrice[i] = new VettoreDenso(m.val(i));
        }

        assert repOk();
    }

    /**
     * RI(m) = m.dim >= 1 && m.matrice.lenth == m.dim && m.matrice != null
     */
    public boolean repOk(){
        if(matrice != null
        && dim>=1
        && dim == matrice.length) return true;

        return false;
    }

    public Vettore val(final int i){
        return this.matrice[i];
    }


    /**
     * Effects: restituisce la matrice identita di this
     * Es. 1 2 3    1 0 0
     *     4 5 6 -> 0 1 0
     *     7 8 9    0 0 1
     */
    public Matrice identita(){
        Matrice output = new Matrice(dim);
        for (int i = 0; i < dim; i++) {
            int[] temp = new int[dim];
            temp[i] = 1;
            output.matrice[i] = new VettoreDenso(temp);
        }
        return output;
    }

    /**
     * Effects: restituisce una matrice identita di dimensione n
     * throws IllegalArgumentException se la dimensione n è <= 1
     */
    public Matrice identita(final int n){
        if(n<1) throw new IllegalArgumentException("Dimensione della matrice identità deve essere >0");
        Matrice output = new Matrice(n);
        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            temp[i] = 1;
            output.matrice[i] = new VettoreDenso(temp);
        }
        return output;
    }

    /**
     * Effects: restituisce la dimensione della matrice this
     */
    public int dim() {
        return this.dim;
    }

    /**
     * Effects: restituisce la matrice ottenuta facendo il prodotto tra this e uno scalare 
     */
    public Matrice per(final int alpha){
        MatriceDensa output = new MatriceDensa(dim);

        for (int i = 0; i < dim; i++) {
            output.matrice[i] = this.matrice[i].per(alpha);
        }

        return output;
    }
/*
    public VettoreDenso prodottoVettore(VettoreDenso v){
        int[] temp = new int[dim];
        for (int i = 0; i < dim; i++) {                
            temp[i] = this.matrice[i].prodotto(v);
        }
        return new VettoreDenso(temp);
    }
*/  
    /**
     * Effects: restituisce una matrice trasposta della matrice this
     */
    public Matrice matriceTrasposta(){
        Matrice output = new Matrice(dim);
        int[] temp_array = new int[dim]; 

        for(int i=0;i<dim;i++){
            for (int j = 0; j < dim; j++) {
                temp_array[j] = matrice[j].val(i);
            }
            output.matrice[i] = new VettoreDenso(temp_array);
        }

        return output;
    }
    /**
     * Effects: restituisce la matrice prodotto facendo la somma tra m e this,
     * throw NullPointerException se m == null
     * throw IllegalArgumentException se le matrici hanno dimensioni diverse
     */
    public Matrice piu(final Matrice m){
        if(m==null) throw new NullPointerException();
        if(m.dim()!=this.dim) throw new IllegalArgumentException("Matrici sono di dimensione diversa! this:"+this.dim+" m:"+m.dim);

        MatriceDensa output = new MatriceDensa(dim);

        for(int i=0;i<dim;i++){
            output.matrice[i] = this.matrice[i].piu(m.val(i));
        }

        return output;
    }

    /**
     * Effects: restituisce la matrice prodotto facendo il prodotto tra m e this,
     * throw NullPointerException se m == null
     * throw IllegalArgumentException se le matrici hanno dimensioni diverse
     */
    public Matrice per(final Matrice m){
        if(m==null) throw new NullPointerException();
        if(m.dim()!=this.dim) throw new IllegalArgumentException("Matrici sono di dimensione diversa! this:"+this.dim+" m:"+m.dim());

        MatriceTrasposta t = new MatriceTrasposta(m);
        MatriceDensa output = new MatriceDensa(dim);
        int[] temp_array = new int[dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {                
                temp_array[j] = ((VettoreDenso)this.matrice[i]).per_helper(t.val(j));
            }

            output.matrice[i] = new VettoreDenso(temp_array);
        }

        return output;

    }    

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Matrice)) return false;
        Matrice m = (Matrice) o;

        if(this.dim != m.dim()) return false;
        for(int i=0;i<dim;i++){
            if(!(this.matrice[i].equals(m.val(i)))) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dim,matrice);
    }

    @Override
    public int val(int i, int j) {
        return matrice[i].val(j);
    }

}