import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Path {
    /**
     * RI = path != null && noDuplicates == true && each element of path list is a 
     *      Directory except for the last one which can be either directory or a file
     */

    private final List<Entry> path;

    public Path(){
        path = new LinkedList<>();

        assert repOk();
    }

    public Path(Entry e){
        Objects.requireNonNull(e);
        path = new LinkedList<>();
        path.add(e);
        assert repOk();
    }

    public Path(Path p){
        Objects.requireNonNull(p);
        path = List.copyOf(p.path);
        assert repOk();
    }

    private Path(List<Entry> p){
        Objects.requireNonNull(p);
        path = List.copyOf(p);
        assert repOk();
    }

    public Path add(Entry e){
        path.add(Objects.requireNonNull(e));
        assert repOk();
        return new Path(path);
    }

    public Path remove(Entry e)throws IllegalArgumentException{

        if(path.contains(Objects.requireNonNull(e))){
            if(path.get(path.size()-1).equals(e)){
                path.remove(e);
                assert repOk();
                return new Path(path);
            }
            throw new IllegalArgumentException("Can't remove middle element");
        }

        throw new IllegalArgumentException("No such element in path");
    }


    public boolean repOk(){
        int size = path.size();

        if(path == null || !noDuplicates()) return false;

        for (int i = 0; i < size; i++) {
            if(i<size-2 && path.get(i).isFile()) return false;
        }

        return true;
        
    }

    private boolean noDuplicates(){
        for(Entry e1 : path){
            int dups = 0;
            for(Entry e2 : path){
                if(e1.equals(e2)) dups++;
            }

            if(dups > 1) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        int size = path.size();
        StringBuilder output = new StringBuilder("/");

        if(size == 0) return output.toString();

        for (int i = 0; i < size; i++) {
            output.append(path.get(i).toString());
            if(i<size-2) output.append("/");
        }

        return output.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Path)) return false;

        final Path p = (Path) o;

        for (Entry entry : path) {
            if(!p.path.contains(entry)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path);
    }
    
}
