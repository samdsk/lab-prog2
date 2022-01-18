package Bancarelle;

import java.util.Objects;

public class Bancarella{
    private final String proprietario;
    private final Inventario inventario;
    private final Listino listprezzi;

    public Bancarella(String p, Inventario i, Listino l){
        proprietario = p;
        inventario = i;
        listprezzi = l;
    }

    public boolean hasGiocattolo(Giocattolo g){
        return inventario.hasGiocattolo(g);
    }


    public int prezzo(Giocattolo g,int num){
        return listprezzi.prezzo(g,num);
    }

    public int prezzo(Giocattolo g) {
        return listprezzi.prezzo(g);
    }

    public int howMany(Giocattolo g){
        return inventario.howMany(g);
    }

    public String proprietario(){
        return proprietario;
    }

    public int acquista(Giocattolo g,int num){
        Objects.requireNonNull(g);
        if(num<=0) throw new IllegalArgumentException("Numero di oggetti da acquistare deve essere positivo.");
        int temp = howMany(g); 
        if(temp>= num){
            inventario.rimuovi(num, g);
            return prezzo(g, num);
        }
        throw new IllegalArgumentException("Impossibile aquistare più di "+temp);
    }



}
