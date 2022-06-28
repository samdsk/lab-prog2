import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ListeDiDistribuzione implements Iterable<Indirizzo> {
    /**
     * Le istanze di questa classe sono mutabili (in quanto si prevede
     * di poter aggiungere / rimuovere Alias) e concorrono a rappresentare
     * liste di distribuzione, quindi insiemi di alias con nome.
     */
    // attributi
    private final Set<Alias> set; // insieme di alias
    public String nome; // nome della lista, è stato deciso di rendere public il nome
    // in quanto le stringhe non sono modificabili
    
    // costruttori
    /**
     * Effects: inizializza la lista vuota (con un insieme vuoto)
     */
    public ListeDiDistribuzione() {
        set = new HashSet<>();
    }
    
    /**
     * Effects: inizializza la lista a partire dal nome
     * @param nome della lista
     */
    public ListeDiDistribuzione(String nome) {
        set = new HashSet<>();
        this.nome = nome;
    }

    /**
     * Effects: inizializza la lista a partire da un'altra lista (costruttore copia)
     * @param other da copiare dentro this
     */
    public ListeDiDistribuzione(ListeDiDistribuzione other) {
        set = new HashSet<>(Objects.requireNonNull(other.set));
    }

    /**
     * Effects: inizializza this a partire da un singolo alias
     * @param a Alias da inserire come unico elemento dell'insieme
     */
    public ListeDiDistribuzione(Alias a) {
        set = new HashSet<>();
        set.add(a);
    }

    /**
     * Effects: inizializza this a partire da un insieme di alias
     * @param s insieme di alias
     */
    public ListeDiDistribuzione(Set<Alias> s) {
        set = new HashSet<>(Objects.requireNonNull(s));
    }

    /**
     * Effects: aggiunge a this un indirizzo
     * @param i indirizzo da aggiungere
     */
    public void aggiungi(Indirizzo i) {
        for (Alias a : set) {
            if (a.stessoDominio(i)) {
                a.aggiungi(i.locale());
                return;
            }
        }
        Alias nuovo = new Alias("Alias: " + i.dominio().toString(), new HashSet<>(), i.dominio());
        nuovo.aggiungi(i.locale());
    }

    /**
     * Effects: aggiunge un alias a this
     * @param a alias da aggiungere
     */
    public void aggiungi(Alias a) {
        set.add(Objects.requireNonNull(a));
    }

    public boolean rimuovi(Indirizzo i) {
        for (Alias a : set) {
            if (a.stessoDominio(i)) {
                return a.rimuovi(i.locale());
            }
        }
        return false;
    }

    // metodi

    /**
     * Effects: restituisce una nuova istanza di ListeDiDistribuzione
     * come risultato della somma tra this e la lista l
     * @param s nome da assegnare alla nuova lista risultante
     * @param l lista da sommare a this
     * @return una nuova lista somma
     */
    public ListeDiDistribuzione Somma(String s, ListeDiDistribuzione l) {
        this.nome = new String(Objects.requireNonNull(s));
        Set<Alias> nuovo = new HashSet<>();
        for (Alias a : this.set)
            nuovo.add(a);
        for (Alias a : l.set)
            nuovo.add(a);
        return new ListeDiDistribuzione();
    }

    /**
     * Effects: restituisce una nuova istanza di ListeDiDistribuzione
     * come risultato della sottrazione tra this e la lista l
     * @param s nome da assegnare alla nuova lista risultante
     * @param l lista da sottrarre a this
     * @return una nuova lista sottrazione
     */
    public ListeDiDistribuzione Sottrazione(String s, ListeDiDistribuzione l) {
        this.nome = new String(Objects.requireNonNull(s));
        ListeDiDistribuzione nuovo = new ListeDiDistribuzione(this);
        for (Alias a : nuovo.set) {
            for (Alias a2 : l.set) {
                if (a.stessoDominio(a2)) {
                    a.sottrazione(a2);
                }
            }
        }
        return nuovo;
    }

    /**
     * Effects: determina se this contiene l'indirizzo i
     * @param i indirizzo da cercare dentro this
     * @return true se this contiene i, false altrimenti
     */
    public boolean contieneIndirizzo(Indirizzo i) {
        for (Alias a : set) {
            if (a.contiene(i)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof ListeDiDistribuzione)) return false;
        ListeDiDistribuzione l = (ListeDiDistribuzione) obj;
        LinkedList<String> indirizzi1 = new LinkedList<>();
        LinkedList<String> indirizzi2 = new LinkedList<>();
        for (Alias a : set) {
            Iterator<Indirizzo> i = a.iterator();
            while(i.hasNext())
                indirizzi1.add(i.next().toString());
        }
        for (Alias a : l.set) {
            Iterator<Indirizzo> i = a.iterator();
            while(i.hasNext())
                indirizzi2.add(i.next().toString());
        }
        Collections.sort(indirizzi1);
        Collections.sort(indirizzi2);
        return indirizzi1.equals(indirizzi2);
    }

    @Override
    public Iterator<Indirizzo> iterator() {
        final List<Indirizzo> l = new ArrayList<>();
        for (Alias a : set) {
            Iterator<Indirizzo> i = a.iterator();
            while(i.hasNext()) {
                l.add(i.next());
            }
        }
        return l.iterator();
    }
}
