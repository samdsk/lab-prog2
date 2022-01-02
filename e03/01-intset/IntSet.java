import java.util.ArrayList;
import java.util.Random;

public class IntSet{
    //Overview: IntSet is a set of integers which doesn't contian any duplicates
    private ArrayList<Integer> set;
    private int size;

    //constructors
    public IntSet(){
        this.set = new ArrayList<Integer>();
        this.size = 0;
    }
    //methods

    /**
     * Requires: _
     * Modifies: Inserts a element to this.set and increases this.size by 1
     * Effects: _
     */
    public void insert(int x){
        if(this.contains(x)) return;
        this.set.add(x);
        this.size++;
    }

    /**
     * Requires: _
     * Modifies: Removes X from this.set and decreases this.size by 1 only if this.contains(X) == true.
     * Effects: If this.set contains X then it will be removed from this.set and decreased this.size by 1.
     *          Throws an EmptyException if this.set is empty.
     */
    public void remove(int x){        
        if(this.set.isEmpty()) throw new EmptyException("The set is empty.");  
        if(this.contains(x)){
            size--;    
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
     * Effects: if set.isEmpty == true throws an EmptyException.
     *          Returns a random value of this.set.
     */
    public int choose(){
        if(this.set.isEmpty()) throw new EmptyException("The set is empty.");
        Random r = new Random();
        return set.get(r.nextInt(this.size));
    }
    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns this.size 
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

    /** AF */
    @Override
    public String toString(){
        String output = "{";
        for (Integer n : this.set) {
            output += n + ",";
        }

        output += "} size:" + size;
        return  output;
    }

}