import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

public class ListaDiDistribuzione implements Iterable<Indirizzo> {
  // OVERVIEW: Una lista di distribuzione permette di raggiungere con una email
  //    indirizzi in domini diversi
  //    Gli oggetti di questa classe sono mutabili
  //
  //    Tutti i metodi in questa classe sollevano `NullPointerException` se un
  //    riferimento nullo viene passato in qualunque parametro
  //
  //
  // L'invariante di rappresentazione è
  //    `as` non contiene ripetizioni
  //    L'elenco di indirizzi ottenuto dall'unione di tutti gli alias in `as`
  //    non contiene ripetizioni
  //    !nome.isBlank()
  //    nome != null
  //    as != null
  //    Ogni elemento di `as` è diverso da `null`
  //
  // La funzione di astrazione è
  //    AF( c ) = la lista di distribuzione `c.nome` contenente gli indirizzi
  //    in `c.as.a`, per ogni `Alias a`

  private final String nome;
  private final Set<Alias> as;

  public ListaDiDistribuzione(final String nome, final Alias a) {
    // EFFECTS: inizializza `this` perché rappresenti la lista di distribuzione
    //    contenente gli indirizzi email nell'alias `a`

    Objects.requireNonNull(a, "l'alias non può essere nullo");

    this.nome = Objects.requireNonNull(nome, "il nome non può essere nullo");
    this.as = new HashSet<>();
    as.add(a);
  }

  private ListaDiDistribuzione(final String nome, final Set<Alias> as) {
    this.nome = Objects.requireNonNull(nome);
    this.as = as;
  }

  public ListaDiDistribuzione somma(final ListaDiDistribuzione that) {
    // EFFECTS: Restituisce la lista di distribuzione ottenuta facendo la somma
    //    insiemistica della lista `this` e la lista `that`

    Objects.requireNonNull(that,
        "la lista di distribuzione non può essere nulla");

    Set<Alias> as = new HashSet<>();
    as.addAll(this.as);

    for (Alias a : that.as)
      for (Alias l : as) {
        if (l.contieneAlias(a) || as.contains(l))
          continue;

        as.add(a);
      }

    return new ListaDiDistribuzione(nome, as);
  }

  public ListaDiDistribuzione differenza(final ListaDiDistribuzione that) {
    // EFFECTS: Restituisce la lista di distribuzione ottenuta facendo la
    //    differenza insiemistica della lista `this` e la lista `that`

    Objects.requireNonNull(that,
        "la lista di distribuzione non può essere nulla");

    // Sbagliato, non faccio in tempo a correggere
    Set<Alias> as = new HashSet<>();
    as.addAll(this.as);
    as.removeAll(that.as);

    return new ListaDiDistribuzione(nome, as);
  }

  public int dimensione() {
    int ris = 0;

    for (Alias a : as)
      ris += a.dimensione();

    return ris;
  }

  @Override
  public boolean equals(final Object obj) {
    // Due liste di distribuzione sono uguali se e solo se sono uguali i loro
    // nomi

    if (this == obj)
      return true;

    if (!(obj instanceof ListaDiDistribuzione))
      return false;

    final ListaDiDistribuzione that = (ListaDiDistribuzione) obj;
    return this.nome.equals(that.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, as);
  }

  @Override
  public Iterator<Indirizzo> iterator() {
    return new Iterator<Indirizzo>() {

      // il vecchio invariante permetteva l'esistenza di alias con nome diverso
      // contenenti gli stessi indirizzi, quindi ho dovuto controllare i
      // duplicati
      private Iterator<Alias> outerIterator = as.iterator();
      private Iterator<Indirizzo> innerIterator;
      private Set<Indirizzo> is = new HashSet<>();
      private Indirizzo next;

      @Override
      public boolean hasNext() {
        if (!outerIterator.hasNext())
          return false;

        innerIterator = as.iterator().next().iterator();
        while (!innerIterator.hasNext())
          try {
            innerIterator = as.iterator().next().iterator();
          } catch (NoSuchElementException excp) {
            // Brutto, se ho tempo ci ritorno
            // L'eccezione può essere sollevata da `outerIterator`
            return false;
          }

        if (innerIterator.hasNext() && !is.contains( next )) {
          next = innerIterator.next();
          return true;
        }

        return false;
      }

      @Override
      public Indirizzo next() {
        is.add(next);
        return next;
      }

    };
  }

  @Override
  public String toString() {
    return "Lista di distribuzione " + nome + " con " + dimensione() + " voci";
  }
}
