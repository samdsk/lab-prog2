import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SIMap {
    //Overview SIMap is mutable, represent a data structer which map String k to Integer v 
    /**
     * AF(m) = for each entry in map {k_1->v_1,k_2->v_2,...,k_n->v_n} 
     * 
     * RI(m) = m.map != null
     *         m.map.size >= 0
     *         m.map doesn't contain any duplicate
     */
    //fields
    /** String to Integer map */
    private final Map<String,Integer> map;

    /** Initialize this as an empty map */
    public SIMap(){
        map = new HashMap<>();
        assert repOk();
    }

    /** 
     * Modifies: this if String k is not already contained
     * Effects: Insert String k and Integer v
     *          throws DuplicateKeyException if k is already contained
    */
    public void put(String k, int v){
        if(contains(k)) throw new DuplicateKeyException("String "+k+"is already contained in the map");
        map.put(k, v);
        assert repOk();
    }
    /**
     * Effects: Returns true if String key is present in this.map otherwise false
     */
    public boolean contains(String key){
        if(map.containsKey(key)) return true;
        return false;
    }

    /**
     * Modifies: this.map if map is not empty
     * Effects: Removes String key and corrisponding Integer value
     *          Returns value of removed key
     *          Throws EmptyMapException if map is empty
     *          Throws NoSuchElementException if key is not found
     */
    public int remove(String key){
        if(map.isEmpty()) throw new  EmptyMapException("Can't remove "+key+" from an empty map!");
        int output = get(key);
        map.remove(key);
        assert repOk();
        return output;
    }

    /**
     * Effects: Returns Integer values corrisponding to the String key
     *          Throws NoSuchElementException if key is not found
     */
    public int get(String key){
        if(!contains(key)) throw new NoSuchElementException("Can't find "+key);
        return map.get(key);
    }

    /**
     * Effects: Returns the size of the map
     */
    public int size(){
        return map.size();
    }

    /** AF */
    public String toString(){
        String output = "{";
        int count = 0;
        int size = map.size()-1;
        for (Map.Entry<String,Integer> e : map.entrySet()) {
            output += e.getKey()+"->"+e.getValue();
            if(count<size) output += " , ";            
        }
        return output += "}";
    }
    /** RI */
    public boolean repOk(){
        if(map == null || map.size() <0 ) return false;
        
        for(String s : map.keySet()){
            int dups = 0;
            for(String t : map.keySet()){
                if(s==t) dups++;
            }
            if(dups>1) return false;
        }

    
        return true;
    }

    public boolean equals(Object o){
        if(!(o instanceof SIMap)) return false;

        SIMap q = (SIMap) o;
        if(size() != q.size()) return false;

        for(String key : map.keySet()){
            if(!q.contains(key)) return false;
        }

        return true;
    }
}
