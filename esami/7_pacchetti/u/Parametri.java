import java.util.Objects;
import java.util.zip.Checksum;
import java.nio.charset.Charset;

/**
 * Overview:
 * Questo è un oggetto immutabile che rappresenta
 * i parametri di trasmissione utilizzati per l'invio e la ricezione 
 * di un pacchetto dalla sorgente al destinatario
 */
public record Parametri(int dimensione, Charset charset, Checksum algoritmo) {

    /**
     * Invariante di rappresentazione:
     * dimensione > 0;
     * charset != null;
     * algoritmo != null
     * 
     * Funzione di Astrazione:
     * Rappresenta i parametri, quindi la dimensione, il charset e l'algoritmo di checksum usati
     * per la trasmissione di un pacchetto
     */
    
    
    /**
     * Costruisce un nuovo oggetto parametri
     * @param dimensione la dimensione del pacchetto (il suo numero di byte)
     * @param charset usato per codificare il messaggio
     * @param algoritmo di checksum
     * @throws IllegalArgumentException nel caso in cui la dimensione sia negativa o pari a zero
     * @throws NUllPointerException nel caso in cui charset o algoritmo siano null
     */
    public Parametri(int dimensione, Charset charset, Checksum algoritmo) {
        if (dimensione <= 0) {
            throw new IllegalArgumentException("La dimensione deve essere maggiore di zero");
        }
        this.dimensione = dimensione;
        this.charset = Objects.requireNonNull(charset, "charset è null");
        this.algoritmo = Objects.requireNonNull(algoritmo, "l'algoritmo è null");
    }
}