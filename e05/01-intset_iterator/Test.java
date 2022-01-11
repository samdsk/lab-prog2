import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        IntSet s = new IntSet();

        s.insert(1);
        s.insert(2);
        s.insert(3);
        s.insert(4);

        System.out.println("For each");

        for (Integer i : s) {
            System.out.println(i);
        }

        Iterator<Integer> it = s.iterator();
        
        System.out.println("While");
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
