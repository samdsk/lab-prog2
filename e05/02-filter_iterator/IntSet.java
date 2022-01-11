import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class IntSet implements Iterable<Integer>{
    //Overview: IntSet is a mutable set of integers which doesn't contian any duplicates
    /**
     * AF(s) = [s.set.get(i) | 0<=i<size]
     * 
     * RI(s) = 
     *      s.set!= null 
     *      && s.size>=0 
     *      && s.set.get(i) != s.set.get(j) for every 0<=i<j<size
     */

    //attributes

    /**List of positive integers, which doesn't contain duplicates */
    private List<Integer> set;
    /**Rappresents the cardinality of the set */
    private int size;

    //constructors

    /** Initialize this as an empty set */
    public IntSet(){
        this.set = new ArrayList<>();
        this.size = 0;

        assert repOk();
    }
    //methods

    /**
     * Requires: _
     * Modifies: this
     * Effects: If the element X is not contained in this.set it will be added to this.set 
     *          and increased this.size by 1, otherwise nothing is changed.
     */
    public void insert(int x){
        if(this.contains(x)) return;
        this.set.add(x);
        this.size++;

        assert repOk();
    }

    /**
     * Requires: _
     * Modifies: this
     * Effects: If this.set contains X then it will be removed from this.set and decreased this.size by 1,
     *          otherwise nothing is changed.
     *          Throws an EmptyException if this.set is empty.
     */
    public void remove(int x){        
        if(this.set.isEmpty()) throw new EmptyException("The set is empty.");  
        if(this.contains(x)){
            --size;    
            this.set.remove(x);      
        }  

        assert repOk();
    }

    /**
     * Requires: _
     * Modifies: _
     * Effects: If this.size == 0 returns true otherwise false.
     */
    public boolean isEmpty(){
        if(this.size == 0) return true;
        return false;
    }

    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns a random value of this.set.
     *          if set.isEmpty == true throws an EmptyException.       
     */
    public int choose(){
        if(this.set.isEmpty()) throw new EmptyException("The set is empty.");
        Random r = new Random();
        return set.get(r.nextInt(this.size));
    }
    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns this.size the size of the set
     */
    public int size(){
        return this.size;
    }

    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns true if this.set contains X otherwise returns false.
     */
    public boolean contains(int x){
        if(this.set.contains(x)) return true;
        return false;
    }

    /**
     * AF(c) = {c.set.get(i) | 0 <= i < size}
     * 
     * RI = c.set != null 
     *      && size >= 0
     *      && all elements are integers
     *      && doesn't contain any duplicates
    */
    @Override
    public String toString(){
        String output = "IntSet = {";
        for (Integer n : this.set) {
            output += n + ",";
        }

        output += "}";
        return  output;
    }
    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns true if confirms RI otherwise returns false
     */
    public boolean repOk(){
        if(this.set != null && this.size >=0){
            for(int i=0;i<size;i++){
                for(int j=0;i<size;j++){
                    if(i==j) continue;
                    if(this.set.get(i) == this.set.get(j)) return false;                    
                }
            }
            return true;
        }
        return false;
    }
    /**
     * Effects: Returns true if this equals to Object 0 otherwise false
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof IntSet) || o == null) return false;

        IntSet q = (IntSet) o;

        if(this.size==q.size){
            for(int i=0;i<size;i++){
                if(this.set.get(i) != q.set.get(i)) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Effects: Returns an Iterator for this class which let you consume one element per time
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Generator(this);
    }

    static class Generator implements Iterator<Integer>{
        //Overview implements an iterator for IntSet objects

        /**
         * AF(g) = [e_1,...,e_i,...] where i = cursor, 0<=i<g.s.size
         * 
         * RI(g) = g.s != null && 0<=cursor<g.size   
         */
        /**Holds an IntSet object */
        private IntSet s;
        /**Let spans through the IntSet elements */
        private int cursor;

        /** Initialize this as s = IntSet i and cursor = 0 */
        public Generator(IntSet i){
            s = i;
            cursor = 0;
        }
        /**Effects: Returns true if cursor<size else false*/
        @Override
        public boolean hasNext() {
            if(cursor<s.size) return true;
            return false;
        }
        /**Effects: Return next integer of the s.set, throws NoSuchElementException if s.set has no elements left */
        @Override
        public Integer next() {
            if(hasNext()) return s.set.get(cursor++);
            throw new NoSuchElementException("No elements left.");
        }


    }

}