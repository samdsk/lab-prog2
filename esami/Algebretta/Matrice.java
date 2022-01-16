package Algebretta;
public class Matrice {
    //Overview Matrice è un tipo immutabile, rappresenta una matrice quadratica a valori interi di dimensione n
    //è strutturato come un vettore di dimensione n di VettoriDensi di dimensione n
    //Es. Matrice di dimesione 2 
    //  1 2
    //  3 4

    /**
     * AF(matrice, dim) = 
     *      matrice[0][0],...,matrice[0][j],...,matrice[0][dim-1]
     *      matrice[i][0],...,matrice[i][j],...,matrice[i][dim-1]
     *      matrice[dim-1][0],...,matrice[dim-1][j],...,matrice[dim-1][dim-1]
     *  dove i,j sono 0<=i<dim e 0<=j<dim
     * 
     * RI(m) = m.dim >= 1 && m.lenth == m.dim && m != null
     */

    private final VettoreDenso[] matrice;
    private final int dim;

    public Matrice(final int size){
        dim = size;
        matrice = new VettoreDenso[dim];

        for(int i=0;i<dim;i++) matrice[i] = new VettoreDenso(dim);
    }

    public Matrice(final Matrice m){
        dim = m.dim;
        matrice = new VettoreDenso[dim];

        for (int i = 0; i < matrice.length; i++){
            matrice[i] = new VettoreDenso(m.matrice[i]);
        }
    }

    public Matrice diagonale(){
        Matrice output = new Matrice(dim);
        for (int i = 0; i < dim; i++) {
            int[] temp = new int[dim];
            temp[i] = this.matrice[i].val(i);
            output.matrice[i] = new VettoreDenso(temp);
        }
        return output;
    }

    public Matrice identita(){
        Matrice output = new Matrice(dim);
        for (int i = 0; i < dim; i++) {
            int[] temp = new int[dim];
            temp[i] = 1;
            output.matrice[i] = new VettoreDenso(temp);
        }
        return output;
    }

    public Matrice identita(final int n){
        Matrice output = new Matrice(n);
        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            temp[i] = 1;
            output.matrice[i] = new VettoreDenso(temp);
        }
        return output;
    }

    public int dim() {
        return this.dim;
    }



    public Matrice prodottoScalare(final int alpha){
        Matrice output = new Matrice(dim);

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

    public Matrice sommaMatriciale(final Matrice m){
        Matrice output = new Matrice(dim);

        for(int i=0;i<dim;i++){
            output.matrice[i] = this.matrice[i].piu(m.matrice[i]);
        }

        return output;
    }

    public Matrice prodottoMatriciale(final Matrice m){
        if(m==null) throw new NullPointerException();
        if(m.dim!=this.dim) throw new IllegalArgumentException("Matrici sono di dimensione diversa! this:"+this.dim+" m:"+m.dim);

        Matrice t = m.matriceTrasposta();
        Matrice output = new Matrice(dim);
        int[] temp_array = new int[dim];

        for (int i = 0; i < dim; i++) {
            VettoreDenso a1 = this.matrice[i];
            
            for (int j = 0; j < dim; j++) {
                VettoreDenso a2 = t.matrice[j];
                temp_array[j] = a1.prodotto(a2);
            }

            output.matrice[i] = new VettoreDenso(temp_array);
        }

        return output;

    }

}
