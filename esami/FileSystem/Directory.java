import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Directory implements Entry, Iterable<Entry>{

    /**
     * RI = name != null && name != "" && dim>=0 && noDuplicates() == true
     */
    private final String name;
    private Dimension dim;

    private final List<Entry> elements;

    public Directory(final String n){
        if(Objects.requireNonNull(n).length()<1) throw new IllegalArgumentException("Nome del directory non pu essere vuoto");

        name = n;
        dim = new Dimension();
        
        elements = new ArrayList<>();

        assert repOk();
    }

    public boolean repOk(){
        if(name != null 
        && !name.equals("")
        && dim.getDim()>=0
        && noDuplicates()) return true;

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

    public boolean contains(Entry e){
        return elements.contains(e) && isDirectory();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDimension() {
        return (new Dimension(dim.getDim())).getDim();
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
    
}
