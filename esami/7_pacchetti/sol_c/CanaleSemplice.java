import java.util.Objects;

/**
 * Un CanaleSemplice è un oggetto che
 * trasmettere al destinatario i pacchetti sorgente 
 * nell'ordine corretto di ricezione e senza alterazione dei dati.
 * Questa è una classe immutabile e implementa l'intefaccia Canale
 */
public class CanaleSemplice implements Canale {
    
    /**
     * Invariante di rappresentazione:
     * destinatario != null;
     * 
     * Funzione di Astrazione
     * Rappresenta un canale che inoltra pacchetti ad un destinatario
     * 
     */

    private final Destinatario destinatario;

    /**
     * Costruisce un nuovo CanaleSemplice che inoltra pacchetti 
     * a un Destinatario semplice
     * @param d destinatario dei pacchetti
     * @throws NullPointerException nel caso il destinatario sia null
     */
    public CanaleSemplice(final Destinatario d) {
        destinatario = Objects.requireNonNull(d, "Il destinatario è null");
    }
    
    /**
     * Inoltra al destinatario il pacchetto
     * 
     * @param pacchetto da inviare
     * @return False
     */
    public boolean inoltra(final Pacchetto p) {
        destinatario.ricevi(p);
        return false;
    }

    /**
     * Restituisce i parametri di trasmissione di un pacchetto
     */
    public Parametri parametri() {
        return destinatario.parametri();
    }
}
