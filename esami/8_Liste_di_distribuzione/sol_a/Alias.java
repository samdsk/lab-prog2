import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class Alias implements Iterable<Indirizzo> {
  // OVERVIEW: Un alias è un elenco di parti locali di indirizzi email a cui è
  //    associato un nome e un dominio
  //    Un tipico alias è `studenti`, con dominio `studenti.unimi.it`
  //    contenente gli indirizzi locali `mario.rossi`, `carla.bruni` e
  //    `giuseppe.verdi`
  //    Gli oggetti di questa classe sono mutabili
  //
  //    Tutti i metodi in questa classe sollevano `NullPointerException` se un
  //    riferimento nullo viene passato in qualunque parametro
  //
  //
  // L'invariante di rappresentazione è
  //    `nome` non contiene *white space*
  //    `ls` non contiene ripetizioni
  //    !nome.isBlank()
  //    nome != null
  //    d != null
  //    ls != null
  //    l :: ls != null
  //
  // La funzione di astrazione è
  //    AF( c ) = l'alias di nome `c.nome`, dominio `c.d` contenente le parti
  //    locali in `c.ls`


  private final String nome;
  private final Dominio d;
  private final Set<Locale> ls = new HashSet<>();


  public Alias( final String nome, final Dominio d ) {
    // EFFECTS: inizializza `this` perché rappresenti l'alias vuoto con
    //    nome `nome` e dominio `d`
    //    Solleva `IllegalArgumentException` se `nome` contiene *white space*

    Objects.requireNonNull(nome, "il nome non può essere nullo");
    if ( nome.contains( " " ) )
      throw new IllegalArgumentException("il nome non può contenere *white space*");

    this.nome = nome;
    this.d = Objects.requireNonNull(d, "il dominio non può essere nullo");
  }

  public Alias(final Alias that) {
    // EFFECTS: inizializza `this` perché rappresenti l'alias uguale a `that`

    this.nome = that.nome; // Nomi condivisi, ma stringhe immutabili
    this.d = that.d; // Come sopra per i domini

    ls.addAll(that.ls); // Elenco di locali non condiviso
  }

  public void aggiungiLocale(final List<Locale> ls) {
    // MODIFIES: `this`
    // EFFECTS: Aggiunge le parti locali `ls` all'alias `this`
    //    Solleva `NullPointerException` se `l :: ls` è `null`

    Objects.requireNonNull(ls, "l'elenco di locali non può essere nullo");

    int ix = 0;
    for (Locale l : ls) {
      Objects.requireNonNull(l, "il locale in posizione " + ix + " è nullo");
      ++ix;
    }

    this.ls.addAll(ls);
  }

  public void aggiungiLocale(final Locale l) {
    // MODIFIES: `this`
    // EFFECTS: Aggiunge la parte locale `l` all'alias `this`

    Objects.requireNonNull(l, "il locale non può essere nullo");
    ls.add(l);
  }

  public void rimuoviLocale(final List<Locale> ls) {
    // MODIFIES: `this`
    // EFFECTS: Rimuove le parti locali `ls` dall'alias `this`
    //    Solleva `NullPointerException` se `l :: ls` è `null`

    Objects.requireNonNull(ls);

    int ix = 0;
    for (Locale l : ls) {
      Objects.requireNonNull(l, "il locale in posizione " + ix + " è nullo");
      ++ix;
    }

    this.ls.removeAll(ls);
  }

  public void rimuoviLocale(final Locale l) {
    // MODIFIES: `this`
    // EFFECTS: Rimuove la parte locale `l` dall'alias `this`

    Objects.requireNonNull(l, "il locale non può essere nullo");
    ls.remove(l);
  }

  public boolean contieneAlias(final Alias that) {
    // EFFECTS: Restituisce `true` se l'alias `this` contiene l'alias `that`,
    //    `false` altrimenti

    for (Indirizzo i : that)
      if (!ls.contains(i.locale()))
        return false;

    return true;
  }

  public boolean contieneIndirizzo(final Indirizzo i) {
    // EFFECTS: Restituisce `true` se l'alias `this` contiene l'indirizzo `i`,
    //    `false` altrimenti

    Objects.requireNonNull(i);
    if (!(i.dominio().equals(d)))
      return false;
    return ls.contains(i.locale());
  }

  public int dimensione() {
    return ls.size();
  }

  @Override
  public boolean equals(final Object obj) {
    // Due alias sono uguali se e solo se i loro nomi sono uguali

    if (this == obj)
      return true;

    if (!(obj instanceof Alias))
      return false;

    final Alias that = (Alias) obj;
    return this.nome.equals( that.nome );
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, d, ls);
  }

  @Override
  public Iterator<Indirizzo> iterator() {
    return new Iterator<Indirizzo>() {

      private Iterator<Locale> it = ls.iterator();

      @Override
      public boolean hasNext() {
        return it.hasNext();
      }

      @Override
      public Indirizzo next() {
        final Indirizzo i = new Indirizzo(it.next(), d);
        return i;
      }

    };
  }

  @Override
  public String toString() {
    return "Alias " + nome + " con dominio " + d + " e " + ls.size() + " voci";
  }

}
