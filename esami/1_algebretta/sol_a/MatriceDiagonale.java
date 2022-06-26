import java.util.Objects;
public class MatriceDiagonale extends ABSMatrice {

    final private int[] matrice;

    public MatriceDiagonale(final int[] arr)throws NullPointerException, IllegalArgumentException{
        Objects.requireNonNull(arr,"Array arr non può essere null!");

        matrice = arr.clone();

        assert repOk();
    }

    private boolean repOk() {
        return matrice.length>0 && matrice != null;
    }

    @Override
    public int dim() {
        return matrice.length;
    }

    @Override
    public int val(int i, int j) throws IndexOutOfBoundsException {
        if(!checkIndex(i, j)) throw new IndexOutOfBoundsException("Indici non sono validi");
        return i == j ? matrice[i] : 0;
    }

    @Override
    public Matrice per(int alpha) {
        if(alpha==0) return new MatriceNulla(dim());

        int[] res = new int[dim()];

        for(int i = 0;i<dim();i++) res[i] = matrice[i] * alpha;

        return new MatriceDiagonale(res);
    }

    @Override
    public Matrice per(Matrice m) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(m,"Matrice m non può essere null!");
        if(!checkDim(m)) throw new IllegalArgumentException("Matrici devono avere la stessa dimensione!");

        if(m instanceof MatriceNulla) return new MatriceNulla(dim());
        if(m instanceof MatriceIdentita) return new MatriceDiagonale(matrice);
        
        return new MatriceDensa(this).per(m);
    }

    @Override
    public Vettore per(Vettore v) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(v,"Vettore v non può essere null!");
        if(!checkDim(v)) throw new IllegalArgumentException("Matrice vettore devono avere la stessa dimensione!");

        if(v instanceof VettoreNullo) return new VettoreNullo(dim());

        int[] res = new int[dim()];

        for(int i = 0; i<dim();i++)           
            res[i] = this.matrice[i] * v.val(i);
        
        return new VettoreDenso(res);
    }

    @Override
    public Matrice piu(Matrice m) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(m,"Matrice m non può essere null!");
        if(!checkDim(m)) throw new IllegalArgumentException("Matrici devono avere la stessa dimensione!");

        if(m instanceof MatriceNulla) return new MatriceDiagonale(matrice);

        if(m instanceof MatriceIdentita){
            int[] res = new int[dim()];
            for (int i = 0; i < matrice.length; i++) {
               res[i] = matrice[i]+1; 
            }

            return new MatriceDiagonale(res);
        }

        return new MatriceDensa(this).piu(m);
    }
    
}
