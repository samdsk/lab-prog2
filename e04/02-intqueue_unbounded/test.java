import java.util.Scanner;

public class test {
    public static void main(String[] args) {    

        //scanning, getting queue dimension, initializing values
        Scanner s = new Scanner(System.in);
        IntQueueUnb coda = new IntQueueUnb();
        int counterplus = 0, counterminus = 0;
        int num;

        //looping for inputs
        while (s.hasNextLine()) {
            //why is the first of this executed twice?
            if (s.hasNext("\\+1")) { //checks if nextline from s is "+1"
                //System.out.println("line is +1");
                counterplus++; 
                //System.out.println("counterplus: " + counterplus);
                coda.enqueue(counterplus);
                //System.out.println("enqueued completed, actual queue: " + coda);
            } else if (s.hasNext("\\-1")) { //checks if nextline from s is "-1"
                //System.out.println("line is -1");
                counterminus++;
                //System.out.println("counterminus: " + counterminus);
                if (coda.isEmpty()) { //checking if i'm not dequeueing from an empty queue
                    //System.out.println("tried dequeue from empty queue, terminating program");
                    break;
                }
                num = coda.dequeue(); 
                //System.out.println("dequeued completed, actual queue: " + coda);
                System.out.println(num);
            }
            String line = s.nextLine(); //per "consumare" la linea
        }

        System.out.println(coda);
        System.out.println(coda.size());
        s.close();
    }
}
