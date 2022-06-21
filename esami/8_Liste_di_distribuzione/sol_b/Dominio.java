import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dominio {
    /**
     * Le istanze di questa classe sono immutabili e concorrono a rappresentare una parte dominio
     * di un indirizzo email.
     * AF: N -> parte
     * IR: parte deve essere conforme
     */
    // attributi
    public String parte; // rappresentazione

    //Costruttori
    /**
     * Effects: inizializza un nuovo dominio a partire dalla rappresentazione testuale
     * @param s rappresentazione testuale del dominio
     */
    public Dominio(String s) {
        if (!EConforme(s)) throw new IllegalArgumentException("La parte locale non e conforme");
        parte = new String(s);
    }

    /**
     * Effects: inizializza un nuovo dominio a partire da un altro (costruttore copia)
     * @param other dominio da copiare dentro this
     */
    public Dominio(Dominio other) {
        parte = new String(other.parte);
    }

    /**
     * Effects: inizializza un nuovo dominio nullo
     */
    public Dominio() {
        parte = null;
    }
    
    /**
     * Effects: determina se la stringa è conforme oppure no
     * @param s stringa da valutare
     * @return true se la stringa è valida, false altrimenti
     */
    public boolean EConforme(String s) {
        Pattern pattern = Pattern.compile("^[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    @Override
    public String toString() {
        return parte;
    }
}
