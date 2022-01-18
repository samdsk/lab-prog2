package Bancarelle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Compratore {
    private final Set<Bancarella> bancarelle;

    public Compratore(Set<Bancarella> b){
        Objects.requireNonNull(b);

        bancarelle = new HashSet<>();
        bancarelle.addAll(b);
    }

    public Acquista compra(int num, Giocattolo g){
        return compra(num, g,new Acquista(g));
    }

    private Acquista compra(int num, Giocattolo g,Acquista a){
        if(bancarelle.isEmpty()) return a;
        if(num==0) return a;

        Bancarella b = find_min(g);
        
        if(b == null) throw new IllegalArgumentException("Giocattolo non trovato.");
        
        if(b.howMany(g) >= num){            
            a.add(b.proprietario(),b.acquista(g, num),num);
            return a;
        }
        a.add(b.proprietario(),b.acquista(g, b.howMany(g)),b.howMany(g));
        
        num -= b.howMany(g);
        bancarelle.remove(b);

        return compra(num, g,a);
    }

    public Bancarella find_min(Giocattolo g){
        Bancarella min = null;
        Iterator<Bancarella> it = bancarelle.iterator();
        while(it.hasNext()){
            Bancarella temp = it.next();
            if(temp.hasGiocattolo(g)){
                min = temp;
                break;
            }
        }        

        while(it.hasNext()){
            Bancarella temp = it.next();
            if(temp.hasGiocattolo(g) && temp.prezzo(g) < min.prezzo(g)){
                min = temp;                
            }
        }

        return min;
    }
}
