import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Locale {
    /**
     * Le istanze di questa classe sono immutabili e concorrono a rappresentare una parte locale
     * di un indirizzo email.
     * AF: N -> parte
     * IR: parte deve essere conforme
     */
    // attributi
    public String parte; // rappresentazione

    //Costruttori
    /**
     * Effects: inizializza un nuovo locale a partire dalla rappresentazione testuale
     * @param s rappresentazione testuale del locale
     */
    public Locale(String s) {
        //System.out.println(s);
        if (!EConforme(s)) throw new IllegalArgumentException("La parte locale non E conforme");
        parte = new String(s);
    }

    /**
     * Effects: inizializza un nuovo locale a partire da un altro (costruttore copia)
     * @param other locale da copiare dentro this
     */
    public Locale(Locale other) {
        parte = new String(other.parte);
    }

    /**
     * Effects: determina se la stringa è conforme oppure no
     * @param s stringa da valutare
     * @return true se la stringa è valida, false altrimenti
     */
    public boolean EConforme(String s) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*$");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    @Override
    public String toString() {
        return parte;
    }
}
