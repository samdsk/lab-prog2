import java.util.LinkedList;
import java.util.List;

public class UnboundedIntQueue {
    //Overview: UnboundIntQueue is an mutable, implements a FIFO data structure as a unbounded integer queue

    /**
     * AF(q,size) = [q.queue.get(i) | 0<=i<size]
     * 
     * Rep Invariant 
     *  queue != null
     *  size>=0
     */

    //fields
    /** Unbounded Integer queue */
    private LinkedList<Integer> queue;
    /** size of the queue */
    private int size;

    /**Initiaze this as an empty queue */
    public UnboundedIntQueue(){
        queue = new LinkedList();
        size = 0;
    }

    /**
     * Modifies: this.queue and this.size
     * Effects: Appends integer N to the tail of the queue and increase size by 1
     */
    public void enqueue(int n){
         queue.add(n);
         size++;
    }

    /**
     * Modifies: this.queue and this.size
     * Effects: Removes first element of the queue and decreases 
     */
    public int dequeue(){
        if(queue.isEmpty()) throw new QueueEmptyException("Queue is empty cann't extract");
        size--;
        return queue.remove();
    }
    /**RI */
    public boolean repOk(){
        if(queue != null && size>=0) return true;
        return false;
    }
    /**AF */
    @Override
    public String toString(){
        String output = "[";
        while(!queue.isEmpty()){
            output += queue.remove();
            output += ",";
        }

        return output+="]";
    }

    public int size(){
        return this.size;
    }
    public boolean equals(Object o){
        if(!(o instanceof UnboundedIntQueue)) return false;
        UnboundedIntQueue q = (UnboundedIntQueue) o;
        if(size != q.size) return false;
        int count=0;
        while(count<size){
            if(queue.get(count) != q.queue.get(count)) return false;
        }

        return true;
    }

}