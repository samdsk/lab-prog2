import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeIterator implements Iterable<Integer> {
    //Overview implements a range iterator that generate a sequence of intergers between given limits and step.
    //Es. given (2,5,1) -> 2,3,4 from 2 to 5 every 1

    /**
     * AF(up,below,step) -> 
     *  c_0 = below
     *  c_i which is an integer obtained by below <= c_(i-1) + below + step < up
     * 
     * Rep Inv
     * for every c_i -> below <= c_i < up  && step != 0
     */

    //fields
    /**Indicates incremental step */ 
    final private int step;
    /**Upper limit */
    final private int upper_bound;
    /**Lower Limit */
    final private int lower_bound;
    /**Next value to be returned */
    int res;
    /**Indicates whether the res is between limits */
    boolean next;

    //constructors
    /**Initialize this as upper_bound = b and lower_bound = a if d>0
     *  upper_bound = a and lower_bound = b if d<0
     *  throws a RuntimeException if d == 0
    */
    public RangeIterator(int a,int b,int d){
        if(d==0) throw new RuntimeException("step can't be Zero!");
        step = d;  
        if(d>0){           
           upper_bound = b;
           lower_bound = a;
        }else{                    
           upper_bound = a;
           lower_bound = b;
        }
        next = true;
        res = lower_bound;
    }
    /**Initialize this as upper_bound = b and lower_bound = a 
     * step = 1
    */
    public RangeIterator(int a, int b){
        this(a,b,1);
    }
    /**Initialize this as upper_bound = a and lower_bound = 0 
     * step = 1
    */
    public RangeIterator(int a){
        this(0,a,1);
    }

    /**
     * Effects: Returns an Iterator which generate a sequence of integers between lower_bound to upper_bound of given step
     */
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                if(!next){
                    res += step;
                    if(step>=0) next = res < upper_bound;
                    return next = res > lower_bound;
                }
                return next;
            }

            @Override
            public Integer next() {
                if(next) {
                    next = false;
                    return res;
                }
                throw new NoSuchElementException("Out of bounds"+lower_bound+" - "+upper_bound);
            }
                    
        };
    } 
    
}
