import java.util.Iterator;
import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        Predicate<Integer> filter = new IsGreaterThan(5);

        IntSet s = new IntSet();

        for (int i = 0; i < 10; i++) s.insert(i);

        Iterator<Integer> s_it = s.iterator();

        FilterIterator<Integer> fit = new FilterIterator<>(s_it, filter); 
        while(fit.hasNext()){
            System.out.println(fit.next());
        }

    }

}
