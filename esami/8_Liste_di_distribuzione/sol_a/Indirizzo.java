import java.util.Objects;

public class Indirizzo {
  // OVERVIEW: Un indirizzo email è una stringa costituita da una parte locale
  //    e da un dominio
  //    Un tipico indirizzo è `massimo.santini@unimi.it` dove
  //    `massimo.santini` è la parte locale e `unimi.it` è il dominio
  //    Gli oggetti di questa classe sono immutabili
  //
  //
  // L'invariante di rappresentazione è
  //    l != null
  //    d != null
  //
  // La funzione di astrazione è
  //    AF( c ) = l'indirizzo email c.l@c.d

  private final Locale l;
  private final Dominio d;

  public Indirizzo(final Locale l, final Dominio d) {
    // EFFECTS: inizializza `this` perché rappresenti l'indirizzo con
    //    parte locale = `locale` e dominio = `dominio`
    //    Solleva `NullPointerException` se `locale == null` oppure
    //    `dominio == null`

    this.l = Objects.requireNonNull(l);
    this.d = Objects.requireNonNull(d);
  }

  public Locale locale() {
    // EFFECTS: Restituisce la parte locale dell'indirizzo `this`
    return l;
  }

  public Dominio dominio() {
    // EFFECTS: Restituisce il dominio dell'indirizzo `this`
    return d;
  }

  @Override
  public boolean equals(final Object obj) {
    // Due indirizzi sono uguali se e solo se se e solo se sono rispettivamente
    // uguali le loro parti locali e il loro dominio

    if (this == obj)
      return true;

    if (!(obj instanceof Indirizzo))
      return false;

    final Indirizzo that = (Indirizzo) obj;
    return that.d.equals( d ) && that.l.equals( l );
  }

  @Override
  public int hashCode() {
    return Objects.hash(d, l);
  }

  @Override
  public String toString() {
    return "" + d + "@" + l;
  }

}
