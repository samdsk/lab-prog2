import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

// *Package-visible*
class Locale {
  // OVERVIEW:
  //    Una tipica parte locale è `massimo.santini`
  //    Gli oggetti di questa classe sono immutabili

  private final static String er = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*$";
  private final static Predicate<String> VALIDO = Pattern.compile(er).asPredicate();
  private final String locale;

  // L'espressione regolare esclude stringhe vuote

  public Locale( final String locale ) {
    // EFFECTS: inizializza `this` perché rappresenti
    //    la parte locale = `locale`
    //    Solleva `IllegalArgumentException` se `locale` non soddisfa
    //    l'espressione regolare `^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*$`
    //    Solleva `NullPointerException` se `locale == null`

    Objects.requireNonNull(locale, "la parte locale non può essere nulla");

    if (!VALIDO.test(locale))
      throw new IllegalArgumentException("la parte locale non soddisfa l'espressione regolare");

    this.locale = locale;
  }

  public String locale() {
    // EFFECTS: Restituisce la parte locale rappresentata da `this`

    return locale;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;

    if (!(obj instanceof Locale))
      return false;

    final Locale that = (Locale) obj;
    return that.locale.equals( locale );
  }

  @Override
  public int hashCode() {
    return Objects.hash(locale);
  }

}
