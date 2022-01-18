package Bancarelle;

import java.util.ArrayList;
import java.util.List;
public class Acquista {  
    private final String giocattolo;  
    private final List<String> proprietari;
    private final List<Integer> prezzi;
    private final List<Integer> numero_pezzi;

    public Acquista(Giocattolo g){
        giocattolo = g.toString();
        proprietari = new ArrayList<>();
        prezzi = new ArrayList<>();
        numero_pezzi  = new ArrayList<>();
    }

    public void add(String p,int prezzo,int n){
        proprietari.add(p);
        prezzi.add(prezzo);
        numero_pezzi.add(n);
    }

    @Override
    public String toString() {
        String output = "Acquisto di: ";
        output += giocattolo;
        output += ", numero: ";
        output += sum(numero_pezzi);
        output += ", per un costo di: ";
        output += sum(prezzi);
        output += "\n";

        for(int i=0;i<proprietari.size();i++){
            output += proprietari.get(i) + " " + numero_pezzi.get(i) + "\n";
        }

        return output;
    }

    private int sum(List<Integer> l){
        int sum = 0;
        for (Integer i : l) {
            sum += i;
        }

        return sum;
    }

}
