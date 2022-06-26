import java.util.Objects;

public class MatriceDensa extends ABSMatrice{
    /**
     * Overview: MatriceDensa è una classe concreta che 
     * implementa Matrice di dimensione n quadratica
     * 
     * AF = 
     * 
     * RI = matrice.length > 0 && matrice != null
     *  && perogni i compreso tra 0 e matrice.length-1 -> matrice[i].length == matrice.length
     */
    final private int[][] matrice;

    /**
     * Inizializza questa matrice con il dato array bidimensionale m
     * @param m un array bidimensionale
     * @throws IllegalArgumentException se array m è vuota oppure se non è un array bidimensionale quadratica
     * @throws NullPointerException se array m è null
     */
    public MatriceDensa(final int[][] m) throws IllegalArgumentException{
        Objects.requireNonNull(m,"Array bidimensionale non può essere null!");
        if(!(m.length>0)) throw new IllegalArgumentException("Array deve avere almeno un elemento!");

        for(int i = 0;i<m.length;i++)
            if(m[i].length != m.length) throw new IllegalArgumentException("Array deve essere quadratica!");

        matrice = m.clone();

        assert repOk();
    }
    
    public MatriceDensa(Matrice m){
        Objects.requireNonNull(m,"Matrice m non può essere null!");

        matrice = new int[m.dim()][m.dim()];

        for(int i = 0; i<dim();i++) 
            for(int j = 0;j<dim();j++) 
               matrice[i][j] = m.val(i, j);

        assert repOk();

    }
    private boolean repOk() {
        if(matrice.length<=0) return false;
        for(int i = 0;i<matrice.length;i++)
            if(matrice[i].length != matrice.length) return false;
        return true;
    }

    @Override
    public int dim() {
        return this.matrice.length;
    }

    @Override
    public int val(int i, int j){

        if(checkIndex(i, j)){
            return matrice[i][j];
        }

       throw new IndexOutOfBoundsException("Indici non sono validi!");
    }

    @Override
    public Matrice per(int alpha) {
        if(alpha==0) return new MatriceNulla(dim());

        int[][] m = new int[dim()][dim()];

        for(int i = 0; i<dim();i++) 
            for(int j = 0;j<dim();j++) 
                m[i][j] = matrice[i][j] * alpha;
        
        return new MatriceDensa(m);
    }

    @Override
    public Matrice per(Matrice m) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(m,"Matrice m non può essere null!");
        if(!checkDim(m)) throw new IllegalArgumentException("Matrici devono avere la stessa dimensione!");

        int[][] res = new int[dim()][dim()];

        for(int i = 0; i<dim();i++)
            for(int j=0;j<dim();j++)
                for(int r=0;r<dim();r++)
                    res[i][j] += this.matrice[i][r] * m.val(r,j);
        
        return new MatriceDensa(res);

    }

    @Override
    public Vettore per(Vettore v) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(v,"Vettore v non può essere null!");
        if(!checkDim(v)) throw new IllegalArgumentException("Matrice vettore devono avere la stessa dimensione!");

        int[] res = new int[dim()];

        for(int i = 0; i<dim();i++)
            for(int j=0;j<dim();j++)
                for(int r=0;r<dim();r++)
                    res[i] += this.matrice[i][r] * v.val(r);
        
        return new VettoreDenso(res);
    }

    @Override
    public Matrice piu(Matrice m) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(m,"Matrice m non può essere null!");
        if(!checkDim(m)) throw new IllegalArgumentException("Matrici devono avere la stessa dimensione!");

        int[][] res = new int[dim()][dim()];

        for(int i = 0; i<dim();i++)
            for(int j=0;j<dim();j++)                
                res[i][j] = this.matrice[i][j] + m.val(j,i);
        
        return new MatriceDensa(res);

    }
    

}
