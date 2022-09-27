import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> istr = new ArrayList<>(List.of("X SUM A B","Y SUM C D","Z MUL X Y"));

        List<Istruzione> istruzioni = new ArrayList<>();

        for(String str : istr){
            istruzioni.add(new Istruzione(str));
        }

        Interprete intr = new Interprete(istruzioni);
        int output = intr.valuta(new int[]{1,2,3,4});

        System.out.println(output);
    }
}
