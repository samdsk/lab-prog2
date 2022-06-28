import java.util.Objects;

/**
 * Overview:
 * Questo è un oggetto immutabile che rappresenta un pacchetto di dati, 
 * ovvero una parte del messaggio, inviato dalla sorgente al destinatario
 */
public record Pacchetto(int sequenza, byte[] contenuto, long checksum) {

    /**
     * Invariate di Rappresentazione:
     * sequenza >= 0;
     * contenuto != null;
     * 
     * Funzione di Astrazione:
     * rappresenta il pacchetto, dato il numero di sequenza, il suo contenuto e il suo valore
     * di controllo (o checksum)
     */

    /**
     * Costruisce un nuovo pacchetto 
     * @param sequenza, numero identificativo che indica il posto nella sequenza di pacchetti
     * @param contenuto, contenuto in byte del messaggio
     * @param checksum del contenuto 
     * @throws IllegalArgumentException nel caso in cui il numero di sequenza sia negativo
     * @throws NullPointerException nel caso in cui il contenuto sia pari a null
     */
    public Pacchetto(int sequenza, byte[] contenuto, long checksum) {
        if (sequenza < 0) {
            throw new IllegalArgumentException("numero di sequenza negativa");
        }
        this.sequenza = sequenza;
        this.contenuto = Objects.requireNonNull(contenuto, "contenuto uguale a null");
        this.checksum = checksum;
    }
}