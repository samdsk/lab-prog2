import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class AliasEsteso extends ABSAlias{
    private final Set<Locale> elenco_locale;
    private final Set<Alias> elenco_alias;    
    private final Dominio dominio;

    public AliasEsteso(String nome,Dominio dominio){
        if(!(Objects.requireNonNull(nome).length()>0)) throw new IllegalArgumentException("Il nome della lista non può essere vuoto");
        this.nome = nome;
        this.dominio = Objects.requireNonNull(dominio);
        elenco_alias = new HashSet<>();
        elenco_locale = new HashSet<>();
    }

    @Override
    public Iterator<Indirizzo> iterator() {
        return new Iterator<Indirizzo>() {
            Iterator<Locale> itl = elenco_locale.iterator();
            Iterator<Alias> ita = elenco_alias.iterator();
            Iterator<Indirizzo> it = ita.hasNext() ? ita.next().iterator() : null;
            @Override
            public boolean hasNext() {
                if(itl.hasNext())
                    return itl.hasNext();
                else if(it.hasNext()){
                    return it.hasNext();
                }else{
                    if(ita.hasNext()){
                        it = ita.next().iterator();
                        return it.hasNext();
                    }
                    else return false;
                }
            }

            @Override
            public Indirizzo next() {
                if(itl.hasNext()) return new Indirizzo(dominio,itl.next());
                else{
                    return it.next();
                }
            }
            
        };
    }

    @Override
    public boolean add(Locale l) {
        return elenco_locale.add(Objects.requireNonNull(l));
    }

    public boolean add(Alias a) {
        if(!Objects.requireNonNull(a).dominio().equals(this.dominio))
            return false;

        return elenco_alias.add(a);
    }

    @Override
    public boolean remove(Locale l) {
        return elenco_locale.remove(Objects.requireNonNull(l));
    }

    public boolean remove(Alias a){
        if(!Objects.requireNonNull(a).dominio().equals(this.dominio))
            return false;

        return elenco_alias.remove(a);
    }

    @Override
    public boolean contains(Indirizzo i) {
        if(!Objects.requireNonNull(i).dominio().equals(this.dominio)) 
            return false;

        return contains(i.locale());
    }

    @Override
    public boolean contains(Locale locale) {

        if(elenco_locale.contains(Objects.requireNonNull(locale))) return true;
        else{
            for(Alias a : elenco_alias){
                if(a.contains(locale)) return true;
            }
        }

        return false;
    }

    @Override
    public Dominio dominio() {
        return dominio;
    }
}
