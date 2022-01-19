package Piastrelle;

import java.util.Objects;

public class Pavimentazione {
    /**
     * RI = quantita > 0 e piastrella != null
     */
    private final int quantita;
    private final Piastrella piastrella;
    
    public Pavimentazione(int q, Piastrella p){
        Objects.requireNonNull(p);
        quantita = q;
        piastrella = p;
    }

    public int costo(){
        return piastrella.costo()*quantita;
    }

    public int superficie(){
        return piastrella.superficie()*quantita;
    }

}
