package Bancarelle;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private final Map<Giocattolo,Integer> inventario;

    public Inventario(){
        inventario = new HashMap<>();
    }

    public void aggiungi(int num, Giocattolo g){
        if(g==null) throw new NullPointerException("Giocattolo non può essere nullo.");
        if(num<0) throw new IllegalArgumentException("Numero di giocattoli non può essrer negativo");
    }
}
