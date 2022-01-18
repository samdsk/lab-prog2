package Bancarelle;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbsListino implements Listino{
    private final Map<Giocattolo, Integer> prezzi;

    public AbsListino(Map<Giocattolo, Integer> map){
        prezzi = new HashMap<>();        
        for(Entry<Giocattolo,Integer> e : map.entrySet()){
            prezzi.put(e.getKey(), e.getValue());
        }
    }

    public int prezzo(Giocattolo g) {
        if(g==null) throw new NullPointerException("Giocattolo non può essere nullo.");
        if(prezzi.containsKey(g)){
            return prezzi.get(g);
        }
        throw new IllegalArgumentException("Il giocattolo non è presente nel listino.");
    }
}
