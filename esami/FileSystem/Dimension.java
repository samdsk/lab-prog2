import java.util.Objects;

public class Dimension {
    /**
     * RI = dim >= 0
     */

    private final int dim;

    public Dimension(){
        this.dim = 0;
        assert repOk();
    }

    public Dimension(int n){
        this.dim = n;
        assert repOk();
    }

    public Dimension add(int n){
        return new Dimension(this.dim+n); 
    }

    public Dimension sub(int n){
        if(dim-n< 0) throw new IllegalArgumentException("Dimensione n inserito troppo grande.");
        return new Dimension(this.dim-n); 
    }

    public boolean repOk(){
        if(dim>=0) return true;
        return false;
    }

    public int getDim(){
        return this.dim;
    }

    @Override
    public String toString() {
        return Integer.toString(dim);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Dimension)) return false;

        final Dimension d = (Dimension) o;
        return dim == d.dim;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dim);
    }
}
