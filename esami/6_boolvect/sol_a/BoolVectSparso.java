import java.util.Objects;
import java.util.Set;

public class BoolVectSparso implements BoolVect {
    /**
     * Overview: BoolVestSparso è una classe concreta immutabile 
     * che implementa un BoolVect come un vettore di valori booleani sparso
     * 
     * per questo esercizio utilizzo come taglia 10000
     */


    final private Set<Boolean> vettore;
    final private int taglia = 10000;

    public BoolVectSparso(final boolean[] b) throws NullPointerException{
        Objects.requireNonNull(b,"Array b non può essere null!");

        
    }

    @Override
    public int dim() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int taglia() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public BoolVect scrivi(boolean b, int pos) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean leggi(int pos) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BoolVect and(BoolVect B) throws NullPointerException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BoolVect or(BoolVect B) throws NullPointerException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BoolVect xor(BoolVect B) throws NullPointerException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
