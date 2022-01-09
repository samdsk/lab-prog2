public class IntQueue{
    //Overview IntQueue is immutable and implements a FIFO data structure as a Queue for integers

    /**
     * AF(queue,head,tail) = [queue[head],queue[head+1],...,queue[tail-1]] if head<tail
     *                     = [queue[head],queue[head+1],...,queue[queue.length-1],queue[0],...,queue[tail-1]] if head>tail
     * 
     * Rep Invariant 
     *      elements<=queue.length 
     *      0<=head<queue.length
     *      0<=tail<queue.length
     */


    //fields
    //queue holds n integers where n is size
    private final int[] queue;
    // head holds the first element of the queue and tail the last element
    //number of elements in queue
    private int elements;

    //constructors
    /** Inizialize this as an empty queue of given size N */
    public IntQueue(int n){
        this.queue = new int[n];
        this.head = 0;
        this.tail = 0;        
        this.elements = 0;

        assert repOk();
    }
    //methods
    /**Modifies: this if queue is not full
     * Effects: if queue is not full the element N will be appended to the queue
     *          throws QueueFullException if queue is full
     */
    public void enqueue(int n){
        if(isFull()) throw new QueueFullException("Queue is full");        
        this.queue[tail] = n;
        this.tail = (tail+1) % queue.length;
        this.elements++;
        assert repOk();
    }
    /**
     * Effects: return true if queue is full
     */
    public boolean isFull(){        
        if(elements==queue.length) return true;
        return false;
    }
    /**
     * Effects: return true if queue is empty
     */
    public boolean isEmpty(){
        if(elements==0) return true;
        return false;
    }

    /**
     * Modifies: this if is not empty
     * Effects: if the queue is not empty return the value contained in head
     *             throws QeueEmptyException if queue is empty
     */
    public int dequeue(){
        if(isEmpty()) throw new QueueEmptyException("Queue is empty!");
        
        int output = queue[head];
        head = (head+1) % queue.length;
        elements--;

        assert repOk();
        return output;
        
    }

    public int elements(){
        return this.elements;
    }
    /**
     * RI
     */
    public boolean repOk(){
        if(elements <= queue.length 
        && 0<=head && head<queue.length
        && 0<=tail && tail<queue.length)
            return true;
        return false;
    }
    /**
     * AF
     */
    public String toString(){        
        String output = "[";
        if(head<tail){
            for (int i = head; i < tail; i++) {
                output += queue[i]; 
                output += ",";               
            }            
        }else{
            for (int i = head; i < queue.length; i++) {
                output += queue[i]; 
                output += ",";               
            }
            for (int i = 0; i < tail; i++) {
                output += queue[i]; 
                output += ",";               
            }  
        }

        return output + "]";

    }
    /**
     * Equals
     */
    public boolean equals(Object o){
        if(!(o instanceof IntQueue)) return false;
        return equals((IntQueue)o);
    }

    public boolean equals(IntQueue q){
        if(this.elements != q.elements || this.queue.length != q.queue.length) return false;
        for (int i = 0; i < queue.length; i++) {
            if(this.queue[this.head + i % queue.length] != q.queue[q.head+i% queue.length]) return false;
        }

        return true;
    }

}