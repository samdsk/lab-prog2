import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SIMap {
    //Overview SIMap is immutable, rappresent a data structer which map String k to Integer v 
    /**
     * AF(m) = for each entry in map {k->v,k->v,...,k->v} 
     * 
     * RI(m) = m.map != null
     *         m.map.size >= 0
     */
    //fields
    /** String to Integer map */
    private final Map<String,Integer> map;

    /** Initialize this as an empty map */
    public SIMap(){
        map = new HashMap<>();
    }

    /** 
     * Modifies: this if String k is not already contained
     * Effects: Insert String k and Integer v
     *          throws DuplicateKeyException if k is already contained
    */
    public void put(String k, int v){
        if(map.contains(k)) throw new DuplicateKeyException("String "+k+"is already contained in the map");
        map.put(k, v);
        assert repOk();
    }
    /**
     * Effects: Returns if String key is present in this.map
     */
    public boolean contains(String key){
        if(map.containsKey(key)) return true;
        return false;
    }

    /**
     * Modifies: this.map if map is not empty
     * Effects: Removes String key and corrisponding Integer value
     *          Throw EmptyMapException if map is empty
     */
    public void remove(String key){
        if(map.isEmpty()) throw new  EmptyMapException("Can't remove "+key+" from an empty map!");
        map.remove(key);
    }

    /**
     * Effects: Returns Integer values corrisponding to the String key
     */
    public int get(String key){
        if(!map.contains(key)) throw new NoSuchElementException("Can't find "+key);
        return map.get(key);
    }

    /** AF */
    public String toString(){
        String output = "{";
        int count = 0;
        int size = map.size-1;
        for (Map.Entry<String,Integer> e : map.entrySet()) {
            output += e.getKey()+"->"+e.getValue();
            if(count<size) output += " , ";            
        }
        return output += "}";
    }
    /** RI */
    public boolean repOk(){
        if(map == null || map.size <0 ) return false;
        return true;
    }

}
