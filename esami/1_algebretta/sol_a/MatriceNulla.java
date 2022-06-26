import java.util.Objects;

public class MatriceNulla extends ABSMatrice {
    /**
     * Overview: MatriceNulla è una classe concreta che rappresenta una matrice
     * nulla 
     * 
     * AF = [0,0;0,0] esempio di una matrice nulla dimesione 2
     * 
     * RI = dim > 0
     */

    final private int dim;

    /**
     * Costruisce una matrice nulla di dimensione n
     * @param n dimensione della matrice
     * @throws IllegalArgumentException se la dimensione è minore o uguale a zero
     */

    public MatriceNulla(final int n) throws IllegalArgumentException{
        if(n<=0) throw new IllegalArgumentException("Dimensione deve essere maggiore di 0");

        dim = n;

        assert repOk();
    }

    private boolean repOk() {
        return dim>0;
    }

    @Override
    public int dim() {
        return dim;
    }

    @Override
    public int val(int i, int j) throws IndexOutOfBoundsException {
        if(checkIndex(i, j)) 
            return 0;
        throw new IndexOutOfBoundsException("Indici non validi");
    }

    @Override
    public Matrice per(int alpha) {        
        return new MatriceNulla(dim);
    }

    @Override
    public Matrice per(Matrice m) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(m,"Matrice m non può essere null!");
        if(!checkDim(m)) throw new IllegalArgumentException("Matrici hanno dimensioni diversi");
        return new MatriceNulla(dim);
    }

    @Override
    public Vettore per(Vettore v) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(v,"Vettore v non può essere null!");
        if(!checkDim(v)) throw new IllegalArgumentException("Vettore e Matrice hanno dimensioni diversi");
        return new VettoreNullo(dim);
    }

    @Override
    public Matrice piu(Matrice m) throws NullPointerException, IllegalArgumentException {
        return new MatriceDensa(m);
    }
    
}
