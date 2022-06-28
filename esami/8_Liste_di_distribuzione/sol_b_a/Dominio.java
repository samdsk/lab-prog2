import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

// *Package-visible*
class Dominio {
  // OVERVIEW:
  //    Un tipico dominio è `unimi.it`
  //    Gli oggetti di questa classe sono immutabili
  //
  // L'invariante di rappresentazione è
  //    `dominio` non contiene *white space*
  //    !dominio.isBlank()
  //
  // La funzione di astrazione è
  //    AF( c ) = c.dominio

  private final static String er = "^[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
  private final static Predicate<String> VALIDO = Pattern.compile(er).asPredicate();
  private final String dominio;

  public Dominio( final String dominio ) {
    // EFFECTS: inizializza `this` perché rappresenti il dominio `dominio`
    //    Solleva `IllegalArgumentException` se `dominio` non soddisfa
    //    l'espressione regolare
    //    `^[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$`
    //    Solleva `NullPointerException` se `dominio == null`

    Objects.requireNonNull(dominio, "il dominio non può essere nullo");

    if (!VALIDO.test(dominio))
      throw new IllegalArgumentException("il dominio deve soddisfare l'espressione regolare");

    this.dominio = dominio;
  }

  public String dominio() {
    // EFFECTS: Restituisce il dominio rappresentato da `this`

    return dominio;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;

    if (!(obj instanceof Dominio))
      return false;

    final Dominio that = (Dominio) obj;
    return that.dominio.equals( dominio );
  }

  @Override public int hashCode() { return Objects.hash(dominio); }

}
