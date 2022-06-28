import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Questa classe implementa l'intefaccia Destinatario.
 * Permette la gestione della ricezione di pacchetti codificati
 * secondo certi parametri e ne calcola il messaggio ricevuto
 */
public class DestinatarioSemplice implements Destinatario {

    /**
     * Invariante di rappresentazione:
     * ultimo != null;
     * buff != null;
     * param != null;
     * 
     * Funzione di Astrazione:
     * rappresenta l'insieme dei pacchetti ricevuti, i parametri di decodifica di questi
     * e l'ultimo messaggio decodificato
     */
    
    private String ultimo;
    private LinkedList<Pacchetto> buff;
    private Parametri param;


    /**
     * Costruisce un nuovo DestinatarioSemplice 
     * che riceve pacchetti secondo certi parametri specificati
     * @param p parametri di trasmissione
     * @throws NullPointerException nel caso i parametri siano null
     */
    public DestinatarioSemplice(final Parametri p) {
        param = Objects.requireNonNull(p, "Parametri non può essere null");
        buff = new LinkedList<>();
        ultimo = "";
    }

    /**
     * Riceve un pacchetto da una sorgente
     * Nel caso in cui il pacchetto in input sia null,
     * questo indica la fine ricezione del messaggio
     * 
     * @param pacchetto da decodificare
     * @return false
     */
    public boolean ricevi(final Pacchetto p) {
        if (p == null) {
            decode();
            buff = new LinkedList<>();
            return false;
        }
        buff.add(p);
        return false;
    }

    /**
     * Ritorna l'ultimo messaggio ricevuto
     * o una stringa vuota nel caso non sia stato ricevuto nessun 
     * messaggio prima
     */
    @Override
    public String toString() {
        return ultimo;
    }

    /**
     * Restituisce i parametri di trasmissione che questo oggetto
     * usa per la decodifica dei pacchetti
     */
    public Parametri parametri() {
        return param;
    }
    
    private void decode() {
        byte[] bytes;
        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            for (final Pacchetto p : buff){
                baos.write(p.contenuto());
            }
            bytes = baos.toByteArray();
        } catch (IOException cantHappen) {
            bytes = null;
        }
        ultimo = new String(bytes, param.charset());
    }
}
