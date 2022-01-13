import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        RangeIterator rit = new RangeIterator(-10,-1,1);
        Iterator<Integer> it = rit.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
