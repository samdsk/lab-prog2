import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class ListaDiDistribuzione implements Iterable<Indirizzo>{
    /**
     * Overview: Lista di Distribuzione è un oggetto immutabile 
     * e rappresenta un elenco di email con domini diversi
     * 
     * AF(nome,elenco) = 
     *      List nome = 
     *              locale_0@dominio
     *              locale_1@dominio
     *                  .
     *                  .
     *                  .
     *              locale_n@dominio
     * 
     * RI = elenco != null && nome != null && nome.length()>0 && elenco non abbia duplicati
     */

     //Nome della lista
    private final String nome;
    //L'elenco degli indirizzi email
    private final Set<Indirizzo> elenco;

    /**
     * Inizializza this come una lista di distribuzione con {@link nome} e l'alias {@link alias}
     * @param nome il nome di questa lista di distribuzione
     * @param alias l'alias con cui inizializzare
     * @throws NullPointerException se nome o alias è null
     * @throws IllegalArgumentException se il nome è una stringa vuota
     */
    public ListaDiDistribuzione(final String nome,final Alias alias){
        if(!(Objects.requireNonNull(nome).length()>0)) throw new IllegalArgumentException("Il nome non può essere vuoto!");
        this.nome = nome;

        elenco = new HashSet<>();

        for(Indirizzo i : alias){
            elenco.add(i);
        }
    }
    /**
     * Inizializza this con il dato {@link nome} e con la {@link lista} di indirizzi email
     * @param nome il nome della lista di distribuzione
     * @param lista elenco di indirizzi email
     * @throws NullPointerException se il nome o lista è null
     * @throws IllegalArgumentException se il nome è una stringa vuota
     */
    private ListaDiDistribuzione(final String nome, Set<Indirizzo> lista){
        if(Objects.requireNonNull(nome).length()>0) throw new IllegalArgumentException("Il nome non può essere vuoto!");
        this.nome = nome;
        elenco = new HashSet<>(Objects.requireNonNull(lista));
    }

    /**
     * Restituisce una nuova ListaDiDistribuzione ottenuto sommando this e {@link lista}
     * @param nome il nome della nuova Lista di Distribuzione
     * @param lista la seconda da sommare 
     * @return una nuova ListaDiDistribuzione 
     * @throws NullPointerException se nome o lista è null
     */
    public ListaDiDistribuzione somma(final String nome,final ListaDiDistribuzione lista){

        for(Indirizzo i : Objects.requireNonNull(lista)){
            if(!elenco.contains(i)) elenco.add(i);
        }

        return new ListaDiDistribuzione(nome, this.elenco);
    }

    /**
     * Restituisce una nuova ListaDiDistribuzione facendo la differenza tra this e {@link lista}
     * @param nome il nome della nuova Lista di Distribuzione
     * @param lista la seconda da sottrarre  
     * @return una nuova ListaDiDistribuzione 
     * @throws NullPointerException se nome o lista è null
     */
    public ListaDiDistribuzione differenza(final String nome,final ListaDiDistribuzione lista){

        for(Indirizzo i : elenco){
            if(!Objects.requireNonNull(lista).contains(i)) elenco.add(i);
        }

        return new ListaDiDistribuzione(nome, this.elenco);
    }

    /**
     * Verifica se l'indirizzo {@link i} è contenuto in questa lista
     * @param i indirizzo da cercare
     * @return true se l'indirizzo contenuto in questa lista altrimenti false
     * @throws NullPointerExcpetion se indirizzo {@link i} è null
     */
    public boolean contains(Indirizzo i){
        return elenco.contains(Objects.requireNonNull(i));
    }

    /**
     * Restituisce il nome di questa lista di distribuzione
     * @return nome
     */
    public String nome(){
        return this.nome;
    }

    @Override
    public Iterator<Indirizzo> iterator() {
        return elenco.iterator();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("List "+nome+" =");

        for(Indirizzo i : elenco){
            str.append("\t"+i+"\n");
        }
        
        return str.toString();
    }

    @Override
    public boolean equals(Object o){
        if(!( o instanceof ListaDiDistribuzione)) return false;

        ListaDiDistribuzione that = (ListaDiDistribuzione) o;

        return this.nome.equals(that.nome);
    }
}
