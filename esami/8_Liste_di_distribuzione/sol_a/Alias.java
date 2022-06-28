
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Alias implements Iterable<Indirizzo> {
    /**
     * Overview: Alias è una classe concreta mutabile 
     * e rappresenta un elenco senza duplicati di indizzi email dello stesso dominio
     * 
     * AF = Alias nome = 
     *      indirizzo_1
     *      ...
     *      indirizzo_n
     * 
     * RI = elenco != null
     *      && nome != null
     *      && nome.length > 0
     *      && dominio != null
     *      && elenco non abbiano duplicati     
     * 
     */

    final private Set<Locale> elenco;
    final private String nome;
    final private Dominio dominio;


    /**
     * Inizializza un Alias avente un elenco vuoto con il dato nome e dominio
     * @param nome il nome dell'alias
     * @param dominio il dominio del dominio
     * @throws NullPointerException se il nome oppure dominio è null
     * @throws IllegalArgumentException se il nome è vuoto
     */
    public Alias(final String nome, final Dominio dominio){
        if(!(Objects.requireNonNull(nome, "Nome non può essere null!").length()>0)) 
            throw new IllegalArgumentException("Nome dell'alias non può essere vuoto");
        
        Objects.requireNonNull(dominio,"Dominio non può essere null!");

        elenco = new HashSet<>();
        this.nome = nome;
        this.dominio = new Dominio(dominio.dominio());

        assert repOk();
    }

    /** RI */
    public boolean repOk(){
        if(elenco != null
            && nome != null
            && nome.length()>0
            && dominio != null
        ) return true;
        

        return false;
    }


    /**
     * Restituisce il nome di questo alias
     * @return nome
     */
    public String getName(){
        return nome;
    }

    /**
     * Restituisce il dominio di questo alias
     * @return dominio
     */
    public Dominio getDomain(){
        return dominio;
    }

    /**
     * Aggiunge un nuovo indirizzo email (parte locale) a questo alias 
     * e restituisce true se è stato aggiungto all'alias altrimenti false
     * @param i l'indirizzo email
     * @return true se stato aggiungo a questo alias false altrimenti
     * @throws NullPointerException se l'indirizzo è null
     * @throws IllegalArgumentException se l'indirizzo ha un dominio diverso da quello di questo alias
     */
    public boolean add(Indirizzo i) throws NullPointerException,IllegalArgumentException{
        Objects.requireNonNull(i,"L'indirizzo non può essere null!");
        if(!i.dominio().equals(this.dominio)) throw new IllegalArgumentException("Dominio dell'indirizzo fornito non combacia con il dominio dell'alias");
        
        if(!elenco.contains(i.locale())) elenco.add(i.locale());
        else return false;

        assert repOk();
        return true;
    }
    /**
     * Rimuove l'indirizzo dato se contenuto in questo alias e
     * restituisce true se è stato rimosso da questo alias false altrimenti
     * @param i l'indirizzo da rimuovere
     * @return true se è stato rimosso false altrimenti
     * @throws NullPointerException se l'indirizzo è null
     * @throws IllegalArgumentException se l'indirizzo da rimuovere ha un dominio diverso da questo alias
     */
    public boolean remove(Indirizzo i) throws NullPointerException,IllegalArgumentException{
        Objects.requireNonNull(i,"L'indirizzo non può essere null!");
        if(!i.dominio().equals(this.dominio)) throw new IllegalArgumentException("Dominio dell'indirizzo fornito non combacia con il dominio dell'alias");
        
        if(elenco.contains(i.locale())) elenco.remove(i.locale());
        else return false;

        assert repOk();
        return true;
    }

    /**
     * Restituisce true se il dato indirizzo è contenuto in questo alias false altrimenti
     * @param i l'indirizzo da controllare
     * @return true se stato trovato nell'alias false altrimenti
     * @throws NullPointerException se l'indirizzo è null
     */
    public boolean contains(Locale l) throws NullPointerException{
        Objects.requireNonNull(l,"L'indirizzo non può essere null!");
        return elenco.contains(l);
    }

    @Override
    public Iterator<Indirizzo> iterator() {

        final List<Indirizzo> list = new ArrayList<>();

        for(Locale l : elenco)
            list.add(new Indirizzo(l, dominio));

        Collections.sort(list,
            new Comparator<Indirizzo>() {

                @Override
                public int compare(Indirizzo o1, Indirizzo o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            }
        );

        return list.iterator();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Alias)) return false;

        Alias that = (Alias) obj;

        if(!this.dominio.equals(that.getDomain()) || !this.nome.equals(that.getName()))
            return false;

        for(Locale l : elenco)
            if(!that.contains(l)) return false;

        return true;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        
        str.append("Alias "+nome+" =\n");

        for(Locale l : elenco)
            str.append("\t"+l.toString()+"@"+dominio.toString());
        str.append("\n");

        return toString();
    }

}
