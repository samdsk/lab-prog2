package Bancarelle;


import java.util.Map;

public class PrezzoLineare extends AbsListino{   

    public PrezzoLineare(Map<Giocattolo, Integer> map){
        super(map);
    }

    @Override
    public int prezzo(Giocattolo g, int num) {
        if(g==null) throw new NullPointerException("Giocattolo non può essere nullo.");
        return prezzo(g) * num;
    }
    
}
