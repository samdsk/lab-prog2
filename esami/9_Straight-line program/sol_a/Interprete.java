import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Interprete {
     private final Map<String,Funzione> elenco_variabili;
     private final List<Istruzione> istruzioni;

     public Interprete(final List<Istruzione> lista_istruzioni){
        Objects.requireNonNull(lista_istruzioni);

        istruzioni = new ArrayList<>(lista_istruzioni);
        elenco_variabili = new TreeMap<>();
     }

     public int valuta(final int[] ingresso){
        if(Objects.requireNonNull(ingresso).length>26) throw new IllegalArgumentException("Numero di argomenti non può superare 26!");

        creaMappa(ingresso);

        for(Istruzione i : istruzioni){
            i.setArgs(elenco_variabili);
        }

        return istruzioni.get(istruzioni.size()-1).valuta();
     }

     private void creaMappa(int[] ingresso){
        char variabile = 'A';

        for(int i : ingresso){
            elenco_variabili.put(Character.toString(variabile).toString(), Funzione.costante(i));
            variabile = (char)(variabile+1);
        }
     }

     

}
