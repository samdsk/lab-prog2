import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AliasNormale extends ABSAlias{

    /**
     * Overview: AliasNormale è un oggetto mutabile ed implementa l'interfaccia Alias, 
     * rappresenta un elenco senza duplicati di indirizzi email in cui il dominio è medesimo
     * 
     * AF(nome,dominio,elenco) = 
     *      Alias nome = 
     *              locale_0@dominio
     *              locale_1@dominio
     *                  .
     *                  .
     *                  .
     *              locale_n@dominio
     * 
     * RI = nome != null && nome.length()>0 && elenco != null && dominio != null &&
     *      elenco non abbia duplicati
     */

    //L'elenco delle parti locali degli indirizzi
    private final Set<Locale> elenco;
    //Dominio degli inidirizzi dell'alias
    private final Dominio dominio;

    /**
     * Inizializza this come un alias con {@link nome}, Dominio {@link dominio} e con la lista delle parti locali {@link lista}
     * @param nome il nome dell'alias
     * @param dominio il dominio degli indirizzi email
     * @param lista la lista delle parti locali
     * @throws NullPointerException se nome, dominio oppure lista è null
     * @throws IllegalArgumentException se il nome è vuoto 
     */
    public AliasNormale(final String nome,Dominio dominio,List<Locale> lista){
        if(!(Objects.requireNonNull(nome).length()>0)) throw new IllegalArgumentException("Il nome dell'alias non può essere vuoto");
        
        Objects.requireNonNull(lista);

        super.nome = nome;
        this.dominio = Objects.requireNonNull(dominio);
        elenco = lista.isEmpty() ? new HashSet<>() : new HashSet<>(lista);

        assert repOk();
    }
    /**
     * Inizializza this come un alias con {@link nome}, {@link dominio} e 
     * @param nome
     * @param dominio
     */
    public AliasNormale(final String nome, Dominio dominio){
        this(nome, dominio, new LinkedList<Locale>());
    }

    //RI
    public boolean repOk() {
        return nome != null && nome.length()>0 && dominio != null && elenco != null;
    }


    @Override
    public boolean add(Locale l) {
        return elenco.add(Objects.requireNonNull(l));
    }

    @Override
    public boolean remove(Locale l) {
        return elenco.remove(Objects.requireNonNull(l));
    }

    @Override
    public boolean contains(Indirizzo i) {
        Objects.requireNonNull(i);
        if(!dominio.equals(i.dominio())) return false;
        return elenco.contains(i.locale());
    }

    @Override
    public boolean contains(Locale l) {
        return elenco.contains(Objects.requireNonNull(l));
    }

    @Override
    public Dominio dominio() {
        return dominio;
    }

    @Override
    public Iterator<Indirizzo> iterator() {
        
        return new Iterator<Indirizzo>() {
            private final Iterator<Locale> it = elenco.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Indirizzo next() {
                return new Indirizzo(dominio, it.next());
            }
            
        };
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Alias "+nome+" =");

        for(Locale l : elenco){
            str.append("\t"+new Indirizzo(dominio, l)+"\n");
        }
        
        return str.toString();
    }
}
