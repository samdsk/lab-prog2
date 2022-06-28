import java.util.HashMap;
import java.util.Set;

public class Alias {
    /**
     * Overview: Alias è una classe concreta mutabile 
     * e rappresenta un elenco senza duplicati di indizzi email dello stesso dominio
     * 
     * AF = 
     * 
     * RI = elenco != null
     *      && nome != null
     *      && nome.length > 0
     *      && dominio != null
     *      && elenco non abbiano duplicati
     *      && tutti gli elementi del elenco devono avere dominio == this.dominio
     * 
     */

    final private Set<Indirizzo> elenco;
    final private String nome;
    final private Dominio dominio;
    

    public String getName();
    public String getDomain();
    public void add(Indirizzo i);
    public void remove(Indirizzo i);
    public boolean contains(Indirizzo i)

}
