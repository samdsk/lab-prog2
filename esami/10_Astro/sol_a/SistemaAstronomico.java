import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SistemaAstronomico {
    private final List<CorpoCeleste> corpi;
    private int tempo;
    private long energia;

    public SistemaAstronomico(List<CorpoCeleste> lista){
        Objects.requireNonNull(lista);

        corpi = new ArrayList<>(lista);
        tempo = 0;
        energia = 0;
        assert repOk();
    }

    private void aggiornaVelocita(){
        for(CorpoCeleste c : corpi){
            for(CorpoCeleste b : corpi){
                if(!c.equals(b))
                    c.aggiornaVelocita(b);
            }
        }

        assert repOk();
    }

    private void aggiornaPosizione(){
        for(CorpoCeleste c : corpi){
            c.aggiornaPosizione();
        }
        assert repOk();
    }

    public void tick(){
        aggiornaVelocita();
        aggiornaPosizione();
        energiaTotale();
        tempo++;
        assert repOk();
    }

    private void energiaTotale(){

        //double sum_ep = 0;
        //double sum_ec = 0;
        long sum = 0;
        for(CorpoCeleste c : corpi){
            //sum_ep += c.energiaPotenziale();
            //sum_ec += c.energiaCinetica(); 
            sum += c.energia();
        }
        //System.out.println("EP: "+sum_ep+" EC: "+sum_ec+" ET: "+sum+" ET*: "+(sum_ep*sum_ec));
        energia = sum;

        assert repOk();
    }

    public int tempo(){return tempo;}

    public boolean repOk(){
        return corpi != null && tempo >=0 && energia >= 0;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        corpi.sort(new Comparator<CorpoCeleste>() {

            @Override
            public int compare(CorpoCeleste o1, CorpoCeleste o2) {
                return o1.nome().compareTo(o2.nome());
            }
            
        });
        for(CorpoCeleste c : corpi) str.append(c+"\n");

        str.append("Energia totale: "+energia);

        return str.toString();
    }
}
