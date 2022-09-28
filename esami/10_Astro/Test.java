import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<CorpoCeleste> lista = new ArrayList<>();
        lista.add(new Pianeta("Marte", new Punto(-8,-10,0)));
        lista.add(new Pianeta("Giove", new Punto(5,5,10)));
        lista.add(new Pianeta("Saturno", new Punto(2,-7,3)));
        lista.add(new Pianeta("Venere", new Punto(9,-8,-3)));

        SistemaAstronomico s = new SistemaAstronomico(lista);
        //for(int i =0;i<100;i++)
            s.tick();

        System.out.println(s);
        
    }
}
