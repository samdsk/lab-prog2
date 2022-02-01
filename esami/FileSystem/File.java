import java.util.Objects;

public class File implements Entry{
    /**
     * RI = name != null && name != "" && dim >= 0
     */

    private final String name;
    private final Dimension dim;

    public File(final String n, final int d){

        if(Objects.requireNonNull(n).length()<1) throw new IllegalArgumentException("Nome del file non può essere vuoto!");
        if(Objects.requireNonNull(d)<0) throw new IllegalArgumentException("Dimensione del file non può essere negativo!");
        name = n;
        dim = new Dimension(d);

        assert repOk();

    }

    public boolean repOk(){
        if(name != null 
        && !name.equals("") 
        && dim.getDim() >= 0)
            return true;
        
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDimension() {
        return dim.getDim();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof File)) return false;

        final File f = (File) o;

        return name.equals(f.name) && dim.equals(f.dim);
    }

    @Override
    public String toString() {
        return name+"("+dim.getDim()+")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,dim);
    }
    
}
