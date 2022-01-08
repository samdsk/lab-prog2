import java.util.ArrayList;
import java.util.Random;

public class IntSet{
    //Overview: IntSet is a mutable set of integers which doesn't contian any duplicates

    //attributes

    /**List of positive integers, which doesn't contain duplicates */
    private ArrayList<Integer> set;
    /**Rappresents the cardinality of the set */
    private int size;

    //constructors

    /** Initialize this as an empty set */
    public IntSet(){
        this.set = new ArrayList<Integer>();
        this.size = 0;
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
    public int getSize(){
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

    public boolean equals(IntSet q){
        if(this.size==q.size && this.set != null){
            for(int i=0;i<size;i++){
                if(this.set.get(i) != q.set.get(i)) return false;
            }
            return true;
        }
        return false;
    }

    public boolean equalcxcvcxvs(Object q){
        if(!(q instanceof IntSet)) return false;
        return equals((IntSet) q);
    }

}