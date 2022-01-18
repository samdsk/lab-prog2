package Bancarelle;

import java.util.Map;

public class PrezzoScontato extends AbsListino{
    
    private final int limit;

    public PrezzoScontato(Map<Giocattolo, Integer> map,int l){
        super(map);
        limit = l;
    }

    @Override
    public int prezzo(Giocattolo g, int num) {
        if(g==null) throw new NullPointerException("Giocattolo non può essere nullo.");
        int prezzo = prezzo(g);

        if(num>limit){
            return (num * prezzo) + (limit - num) * (prezzo * 85/100); 
        }

        return prezzo * num;
    }
    
}
