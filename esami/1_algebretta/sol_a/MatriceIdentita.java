import java.util.Objects;

public class MatriceIdentita extends ABSMatrice{
    /**
     * Overview: Class MatriceIdentita è una classe concreta implenta una matrice identità
     * 
     * AF = 
     * 
     * RI = matrice.lenth>0 
     *  && perogni i compreso tra 0 e matrice.length-1 -> matrice[i].length == matrice.length
     */

    final private int[][] matrice;

    public MatriceIdentita(final int n) throws IllegalArgumentException{
        if(n<=0) throw new IllegalArgumentException("Dimensione della matrice non può essere minore o uguale a zero");

        matrice = new int[n][n];

        for(int i = 0;i<dim();i++)
            matrice[i][i] = 1;
        
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
        return matrice.length;
    }

    @Override
    public int val(int i, int j) throws IndexOutOfBoundsException {
        if(checkIndex(i, j)) return matrice[i][j];

        throw new IndexOutOfBoundsException("Indici non sono validi");
    }

    @Override
    public Matrice per(int alpha) {
        if(alpha==0) return new MatriceNulla(this.dim());

        int[][] res = new int[dim()][dim()];

        for(int i = 0; i<dim();i++)
            res[i][i] = matrice[i][i] * alpha;
    
        return new MatriceDensa(res);
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
