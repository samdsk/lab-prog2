import java.util.HashSet;
import java.util.Set;

public class SparseBoolVect extends AbsBoolVect {
    private final Set<Integer> vector;

    public SparseBoolVect() {
        super(Integer.MAX_VALUE);
        vector = new HashSet<Integer>();
    }

    @Override
    public void scrivi(int pos, boolean b) throws IllegalArgumentException {
        
        if( pos<0 || Integer.MAX_VALUE - pos <0) throw new IllegalArgumentException("Errore: posizione supera i limiti vettore!");

        if(vector.contains(pos) && !b) {
            vector.remove(pos);            
        }else if(b && !vector.contains(pos)){
            vector.add(pos);
        }else{
            return;
        }

        aggiornaDimesione();
        return;
    }

    @Override
    public void and(BoolVect b) {        
        if(!(b instanceof SparseBoolVect))
    }

    @Override
    public BoolVect or(BoolVect b) {
        SparseBoolVect output = new SparseBoolVect();
        for (Integer i : vector) {
            output.scrivi(i, true);
        }

        return output;
    }

    @Override
    public BoolVect xor(BoolVect b) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void aggiornaDimesione() {
        if(vector.isEmpty()){
            dim = 0;
            return;
        }

        int max = 0;
        for (Integer i : vector) {
            if(max < i) max = i;
        }

        dim = max+1;
        
    }

    @Override
    public boolean leggi(int pos) {
        if(vector.contains(pos)) return true;
        return false;
    }
    
}
