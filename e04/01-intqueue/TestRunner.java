import java.util.Scanner;

public class TestRunner {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int n = reader.nextInt();
        int count = 0;
        IntQueue q = new IntQueue(n);
        try{
            while(reader.hasNextByte()){
                byte b = reader.nextByte();
                if(b == +1){
                    System.out.println("Enqueue");
                    q.enqueue(++count);
                    System.out.println("Count:"+count);
                    System.out.println("Queue: "+q.toString()+" elements: "+q.elements());
                }
                if(b == -1){
                    System.out.println("Dequeue");
                    System.out.println("Dequeue: "+q.dequeue());
                    System.out.println("Queue: "+q.toString()+" elements: "+q.elements());
                }
            }
        }catch(Exception e){
            System.out.println("Error:"+e.toString());
            System.out.println("Queue: "+q.toString()+" elements: "+q.elements());
        }
        
        reader.close();

    }
    
}
