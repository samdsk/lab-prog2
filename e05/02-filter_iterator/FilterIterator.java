import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterIterator<T> implements Iterator<T> {
    //Overview implements a filter-iterator which returns the next element that satisfies a filter predicate
    /**
     * AF(o) = o.next se found = true
     * 
     * RI(o) = it != null && p != null
     * 
     */
    
    // source iterator
    final private Iterator<T> source;
    // predicate to apply
    final private Predicate<Integer> p;
    // next element of source
    T next;
    // indicates if has found a element that satisfies the predicate 
    boolean found;

    /**
     * Initialize this as this.source = source, p = filter and found = false
     */
    public FilterIterator(final Iterator<T> source, Predicate<Integer> filter) {
        this.source = source;
        p = filter;
        found = false;        
    }
    /**
     * Effects: Returns true if in source has an element which satisfies the predicate p
     *          otherwise false
     */
    @Override
    public boolean hasNext() {
        while(source.hasNext() && !found){
            next = source.next();
            if(p.test((Integer)next)){
                found = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Effects: Returns the next element of source which satisfies the predicate
     *          if found is true otherwise throws NoSuchElementException
     */
    @Override
    public T next() {
        if(found) {  
            found=false;
            return next;
        }
        throw new NoSuchElementException("No more elements left or empty");        
    }
  }