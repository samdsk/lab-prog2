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
     * Le istanze di questa classe sono immutabili e concorrono a rappresentare
     * un alias, ovvero un insieme di indirizzi email i quali condividono stesso dominio
     * associati a un nome.
     */

    // attributi
    public final String nome; // noem dell'alias
    private final Set<Locale> set; // insieme di parti locali dell'alias
    private final Dominio dominio; // dominio dell'alias
    
    //Costruttori
    /**
     * Effects: inizializza un nuovo alias a partire da un nome, un insieme di locali
     * e da un dominio.
     * @param nome dell'alias
     * @param set contenente i locali
     * @param dominio comune ai vari indirizzi
     */
    public Alias(String nome, Set<Locale> set, Dominio dominio) {
        if (nome.isEmpty()) throw new IllegalArgumentException("Il nome dell'alias non può essere vuoto");
        this.nome = Objects.requireNonNull(nome);
        this.set = new HashSet<>(Objects.requireNonNull(set));
        this.dominio = new Dominio(dominio);
    }

    /**
     * Effects: inizializza un Alias vuoto
     */
    public Alias() {
        nome = "";
        set = new HashSet<>();
        dominio = new Dominio();
    }

    /**
     * Effects: inizializza un alias a partire da una rappresentazione testuale
     * @param s rappresentazione testuale dell'alias
     */
    public Alias(String s) {
        String[] parti = s.split(" ");
        if (parti.length != 5) throw new IllegalArgumentException();
        Indirizzo ind = new Indirizzo(parti[0]);
        nome = new String(ind.locale().toString());
        dominio = new Dominio(ind.dominio());
        set = new HashSet<>();
        for (int i = 2; i < parti.length; i++) {
            set.add(new Locale(parti[i].substring(0, parti[i].length() - 1)));
        }
    }

    /**
     * Effects: resisuisce il nome dell'alias
     * @return il nome dell'alias
     */
    public String nome() {
        return nome;
    }

    /**
     * Modifies: this
     * Effects: aggiunge un locale
     * @param l
     */
    public void aggiungi(Locale l) {
        set.add(Objects.requireNonNull(l));
    }

    /**
     * Modifies: this
     * Effects: rimuove il locale l da this: restituisce true
     * se l'operazione è stata completata con successo, false altrimenti.
     * @param l locale da rimuovere
     * @return true se il locale è stato rimosso con successo, false altrimenti
     */
    public boolean rimuovi(Locale l) {
        return set.remove(Objects.requireNonNull(l));
    }

    /**
     * Modifies: this
     * Effects: effettua una sottrazione (insiemistica) tra this e l'alias a, togliendo
     * gli indirizzi di a da this (se condividono lo stesso dominio)
     * @param a alias da sottrarre a this
     * @return true se l'operazione è stata completata con successo, false altrimenti
     */
    public boolean sottrazione(Alias a) {
        if (!a.dominio.equals(this.dominio)) return false;
        for (Locale l : a.set) this.set.remove(l);
        return true;
    }

    /**
     * Modifies: this
     * Effects: effettua una somma tra this e a, aggiungendo gli indirizzi di a dentro this
     * (se condividono lo stesso dominio)
     * @param a alias da rimuovere a this
     * @return true se l'operazione è stata completata con successo, false altrimenti
     */
    public boolean addizione(Alias a) {
        if (!a.dominio.equals(this.dominio)) return false;
        for (Locale l : a.set) this.set.add(l);
        return true;
    }

    /**
     * Effects: determina se this contiene l'indirizzo i
     * @param i indirizzo da cercare dentro this
     * @return true se this contiene l'indirizzo i, false altrimenti
     */
    public boolean contiene(Indirizzo i) {
        if (!i.dominio().equals(this.dominio)) return false;
        return set.contains(i.locale());
    }

    /**
     * Effects: determina se this e l'indirizzo i condividono lo stesso dominio
     * @param i indirizo i da confrontare con this
     * @return true se this e l'indirizzo i condividono lo stesso dominio, false altrimenti
     */
    public boolean stessoDominio(Indirizzo i) {
        return i.dominio().equals(this.dominio);
    }

    /**
     * Effects: determina se this.dominio corrisponde al dominio i
     * @param i dominio i da confrontare con this
     * @return true se il dominio di this corrisponde al dominio i, false altrimenti
     */
    public boolean stessoDominio(Dominio i) {
        return i.equals(this.dominio);
    }

    /**
     * Effectgs: determina se this e l'alias other condividono lo stesso dominio
     * @param other alias da confrontare con this
     * @return true se il dominio di this corrisponde al dominio di other, false altrimenti
     */
    public boolean stessoDominio(Alias other) {
        return other.dominio.equals(this.dominio);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Alias)) return false;
        Alias c = (Alias) obj;
        for (Locale s : c.set) {
            if (!(this.set.contains(s))) {
                return false;
            }
        }
        return c.dominio.equals(dominio) && c.nome.equals(nome);
    }

    // itera sugli indirizzi in ordine di lettera
    @Override
    public Iterator<Indirizzo> iterator() {
        final List<Indirizzo> l = new ArrayList<>();
        for (Locale loc : set) l.add(new Indirizzo(loc, this.dominio));
        Collections.sort(
            l,
            new Comparator<Indirizzo>() {
                @Override
                public int compare(Indirizzo i1, Indirizzo i2) {
                    return i1.toString().compareTo(i2.toString());
                }
            }
        );
        return l.iterator();
    }
}
