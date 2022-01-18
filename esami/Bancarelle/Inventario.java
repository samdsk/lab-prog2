package Bancarelle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inventario{
    private final Map<Giocattolo,Integer> inventario;

    public Inventario(){
        inventario = new HashMap<>();
    }

    public void aggiungi(int num, Giocattolo g){
        if(g==null) throw new NullPointerException("Giocattolo non può essere nullo.");
        if(num<0) throw new IllegalArgumentException("Il numero di giocattoli da aggiungere non può essere negativo");

        if(inventario.containsKey(g)){
            int temp = inventario.get(g);
            inventario.put(g,temp+num);
            return;
        }
        inventario.put(g,num);
    }

    public void rimuovi(int num,Giocattolo g){
        if(g==null) throw new NullPointerException("Giocattolo non può essere nullo.");
        if(num<0) throw new IllegalArgumentException("Il numero di giocattoli da rimuovere non può essere negativo.");

        if(inventario.containsKey(g)){
            int temp = inventario.get(g);
            if(temp-num < 0) if(num<0) 
                throw new IllegalArgumentException("Il numero di giocattoli da rimuovere supera il numero attualmente disponibile.");
            inventario.put(g,temp+num);
        }
        throw new IllegalArgumentException("Il giocattolo non è presente nell'inventario.");
    }

    public boolean hasGiocattolo(Giocattolo g){
        Objects.requireNonNull(g);

         if(inventario.containsKey(g)) return true;
         return false;
    }

    public int howMany(Giocattolo g){
        if(hasGiocattolo(g)) return inventario.get(g);
        return 0;
    }

}
