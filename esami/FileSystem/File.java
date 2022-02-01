import java.util.Objects;

public class File implements Entry{
    /**
     * RI = name != null && name != "" && dim >= 0 && abs_path != null
     */ 

    private final String name;
    private final Dimension dim;
    private final Path abs_path;
    //private final Path rel_path;

    public File(final String n, final int d, Path p){

        if(Objects.requireNonNull(n).length()<1) throw new IllegalArgumentException("Nome del file non può essere vuoto!");
        if(Objects.requireNonNull(d)<0) throw new IllegalArgumentException("Dimensione del file non può essere negativo!");
        Objects.requireNonNull(p);

        name = n;
        dim = new Dimension(d);
        abs_path = new Path(p.add(this));
        assert repOk();

    }

    public boolean repOk(){
        if(name != null 
        && !name.equals("") 
        && dim.getDim() >= 0
        && abs_path != null)
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

        return name.equals(f.name) && dim.equals(f.dim) && abs_path.equals(f.abs_path);
    }

    @Override
    public String toString() {
        return name+"("+dim.getDim()+")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,dim);
    }

    @Override
    public Path getPath() {
        return abs_path;
    }
    
}
