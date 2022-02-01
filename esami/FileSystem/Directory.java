
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Directory implements Entry, Iterable<Entry>{

    /**
     * RI = name != null && name != "" && dim>=0 && noDuplicates() == true && abs_path != null
     */
    private final String name;
    private Dimension dim;
    private final Path abs_path;
    //private final Path rel_path;

    private final List<Entry> elements;

    public Directory(final String n,Path p){
        if(Objects.requireNonNull(n).length()<1) throw new IllegalArgumentException("Nome del directory non pu essere vuoto");
        Objects.requireNonNull(p);

        name = n;
        dim = new Dimension();
        abs_path = new Path(p.add(this));
        elements = new LinkedList<>();

        assert repOk();
    }

    public boolean repOk(){
        if(name != null 
        && !name.equals("")
        && dim.getDim()>=0
        && noDuplicates()
        && abs_path != null)
            return true;

        return false;
    }

    private boolean noDuplicates(){
        for(Entry e1 : elements){
            int dups = 0;
            for(Entry e2 : elements){
                if(e1.equals(e2)) dups++;
            }

            if(dups > 1) return false;
        }

        return true;
    }

    public void createFile(final String n,final int d){
        if(Objects.requireNonNull(n).length()<1) throw new IllegalArgumentException("Nome del file non può essere vuoto!");
        if(Objects.requireNonNull(d)<0) throw new IllegalArgumentException("Dimensione del file non può essere negativo!");

        File f = new File(n,d,abs_path);

        if(!elements.contains(f)){

            elements.add(f);
            dim = dim.add(d);

            assert repOk();
        }
    }

    public void createDirectory(final String n){
        if(Objects.requireNonNull(n).length()<1) throw new IllegalArgumentException("Nome del Directory non può essere vuoto!");
        
        Directory dir = new Directory(n,abs_path);

        if(!containsDir(dir)){
            elements.add(dir);
        }
    }

    public boolean containsDir(Directory d){
        for(Entry e : elements){
            if(e.isDirectory() && e.getName().equals(d.name))
                return true;
        }

        return false;
    }

    public boolean removeFile(Entry e){

        if(elements.contains(Objects.requireNonNull(e))){
            elements.remove(e);
            assert repOk();

            return true;
        }

        return false;
    }

    

    public boolean arcCheck(Entry e){
        return elements.contains(e) && isDirectory();
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
        return false;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public Iterator<Entry> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Path getPath() {
        return abs_path;
    }
    
}
