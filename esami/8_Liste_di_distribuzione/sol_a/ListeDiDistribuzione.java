import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ListeDiDistribuzione implements Iterable<Indirizzo>{
    /**
     * Overview: ListaDiDistribuzione è una classe immutabile 
     * rappresenta la lista di distrubuzione in cui ci possono 
     * essere gli indirizzi email di dominio diversi
     * 
     * AF = 
     * 
     * RI = nome != null && nome.length() > 0 && eleneco != null 
     *      && elenco non deve avere duplicati 
     */

    final private String nome;
    final private Set<Indirizzo> elenco;

    public ListeDiDistribuzione(final String nome) throws NullPointerException,IllegalArgumentException{
        if(!(Objects.requireNonNull(nome,"Il nome della lista non può essere null!").length()>0))
            throw new IllegalArgumentException("Il nome della lista non può essere vuoto!");

        this.nome = nome;
        elenco = new HashSet<>();

        assert repOk();
    }

    public ListeDiDistribuzione(final String nome,Alias a) throws NullPointerException,IllegalArgumentException{
        Objects.requireNonNull(a,"Alias a non può essere null!");
        if(!(Objects.requireNonNull(nome,"Il nome della lista non può essere null!").length()>0))
            throw new IllegalArgumentException("Il nome della lista non può essere vuoto!");

        this.nome = nome;
        elenco = new HashSet<>();

        for(Indirizzo i : a)
            elenco.add(i);

        assert repOk();
    }

    public ListeDiDistribuzione(final String nome, final Set<Indirizzo> lista) throws NullPointerException,IllegalArgumentException{
        if(!(Objects.requireNonNull(nome,"Il nome della lista non può essere null!").length()>0))
            throw new IllegalArgumentException("Il nome della lista non può essere vuoto!");

        Objects.requireNonNull(lista, "La list degli indirizzi non può essere vuoto!");

        this.nome = nome;
        elenco = new HashSet<>(lista);

        assert repOk();
        
    }

    private boolean repOk() {
        return nome != null && nome.length() > 0 && elenco != null;
    }

    public String getName(){
        return this.nome;
    }

    public ListeDiDistribuzione sum(final String nome,ListeDiDistribuzione ldd){
        if(!(Objects.requireNonNull(nome,"Il nome della lista non può essere null!").length()>0))
            throw new IllegalArgumentException("Il nome della lista non può essere vuoto!");

        Objects.requireNonNull(ldd,"Lista di Distribuzione non può essere null!");

        Set<Indirizzo> res = new HashSet<>(elenco);

        for(Indirizzo i : ldd) 
            res.add(i);
        
        return new ListeDiDistribuzione(nome, res);
    }

    public ListeDiDistribuzione diff(final String nome, ListeDiDistribuzione ldd){
        if(!(Objects.requireNonNull(nome,"Il nome della lista non può essere null!").length()>0))
        throw new IllegalArgumentException("Il nome della lista non può essere vuoto!");

        Objects.requireNonNull(ldd,"Lista di Distribuzione non può essere null!");

        Set<Indirizzo> res = new HashSet<>(elenco);

        for(Indirizzo i : ldd) 
            res.remove(i);
        
        return new ListeDiDistribuzione(nome, res);
    }

    @Override
    public Iterator<Indirizzo> iterator() {
        final List<Indirizzo> list = new ArrayList<>(elenco);

        Collections.sort(list,
            new Comparator<Indirizzo>() {

                @Override
                public int compare(Indirizzo o1, Indirizzo o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            }
        );

        return Collections.unmodifiableList(list).iterator();
    }
}
