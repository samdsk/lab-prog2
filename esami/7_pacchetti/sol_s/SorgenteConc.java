import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.Checksum;
import java.util.Arrays;

public class SorgenteConc implements Sorgente {

    //attributi di istanza
    private final Canale canale;

    //costruttore
    public SorgenteConc(Canale canale) {

        Objects.requireNonNull(canale);

        this.canale = canale;
        
        assert repOk();
    }

    //metodi

    /**
     * Post-condizioni: trasmette il messaggio "messaggio" dopo averlo codificato al canale this.canale.
     */
    @Override
    public void trasmetti(String messaggio) {
        Pacchetto[] messaggioCifrato = encode(this.canale.parametri(), messaggio);

        for (int i = 0; i < messaggioCifrato.length; i++) {
            boolean sent = this.canale.inoltra(messaggioCifrato[i]);
            if (!sent) { // in caso di errore rinvio in loop??? non penso abbia molto senso
                this.canale.inoltra(messaggioCifrato[i]);
            }
        }
    }

    /**
     * Post-condizioni: restiuisce un array di Pacchetto composto dal messaggio s codificato secondo i parametri "parametro"
     */
    @Override
    public Pacchetto[] encode(Parametri parametro, String s) {

        int dimensione = parametro.dimensione();
        Charset charset = parametro.charset();

        Pacchetto[] pacchetti = new Pacchetto[dimensione / s.length() + 1];

        byte[] bytes = s.getBytes(charset);

        for (int i = 0; i < dimensione / s.length() + 1; i++) {
            byte[] subbytes = Arrays.copyOfRange(bytes, dimensione * i, dimensione * (i + 1) - 1); //da javadoc: to - the final index of the range to be copied, exclusive. (This index may lie outside the array.), perciò non dovrebbe sollevare outofbounds
            pacchetti[i] = new Pacchetto(i, subbytes, checksum(subbytes, canale.parametri().algoritmo())); 
        }
        
        return pacchetti;
    }
 
    /**
     * IR = canale non null, parametri del destinatario del canale devono essere validi.
     */
    public boolean repOk() {
        return (canale != null && canale.parametri().repOk());
    }

    /**
     * Post-condizioni: restituisce il checksum dell'array di bytes "bytes" usando
     * l'algoritmo algo.
     */
    public long checksum(final byte[] bytes, final Checksum algo)
            throws NullPointerException, IllegalArgumentException {

        Objects.requireNonNull(bytes);

        Checksum algorithm = algo;
        algorithm.reset();
        algorithm.update(bytes);
        return algorithm.getValue();
    }

    /**
     * 
     */
}
