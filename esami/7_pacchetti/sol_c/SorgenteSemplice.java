import java.util.Objects;
//import java.util.zip.Checksum;
import java.util.LinkedList;
import java.util.List;

/**
 * Questa classe rappresenta una sorgente semplice
 * ovvero che invia al destinatario, attraverso un canale
 * ciascun pacchetto una sola volta e nell'ordine corretto.
 * E' una classe immutabile e implementa l'intefaccia Sorgente
 */
public class SorgenteSemplice implements Sorgente {

    /**
     * Invariante di Rappresentazione:
     * canale != null
     * 
     * Funzione di Astrazione:
     * Rappresenta una sorgente collegata ad uno specifico canale di trasmissione
     */
    
    private final Canale canale;

    /**
     * Costruisce una nuova SorgenteSemplice dato un canale 
     * dove trasmettere i messaggi
     * @param canale di trasmissione
     * @throws NullPointerException nel caso in cui canale sia null
     */
    public SorgenteSemplice(final Canale canale) {
        this.canale = Objects.requireNonNull(canale, "Il canale non può essere null");
    }

    /**
     * Trasmette un messaggio al destinatario
     * @param messaggio da inviare
     * @throws NullPointerException nel caso in cui il messaggio sia null
     */
    public void trasmetti(final String messaggio) {
        for (final Pacchetto p : encode(messaggio)) {
            canale.inoltra(p);
        }
        canale.inoltra(null);
    }

    /*
    private List<Pacchetto> spezza(final String messaggio) {
        LinkedList<Pacchetto> res = new LinkedList<>();
        Parametri p = canale.parametri();
        byte[] msg = messaggio.getBytes(p.charset());
        
        byte[] buff = new byte[p.dimensione()];
        int i = 0, j = 0;
        while( j < msg.length) {
            buff[i] = msg[i + j];
            if (i % p.dimensione() == 0) {
                Checksum algo = p.checksum();
                algo.reset();
                algo.update(buff);
                res.add(new Pacchetto(j, buff, algo.getValue()));
                j++;
                i = 0;
            }
            i++;
        }
        if (i > 0) {
            Checksum algo = p.checksum();
            algo.reset();
            algo.update(buff);
            res.add(new Pacchetto(j, buff, algo.getValue()));
        }
        return res;
    }
    */

    private List<Pacchetto> encode(final String messaggio) {
        LinkedList<Pacchetto> res = new LinkedList<>();
        Parametri p = canale.parametri();
        byte[] msg = messaggio.getBytes(p.charset());
        
        byte[] buff = new byte[p.dimensione()];

        int j = 0;
        for (int i = 0; i < msg.length; i++) {
            buff[j] = msg[i];
            j++;
            if (j % p.dimensione() == 0) {
                res.add(new Pacchetto(0, buff, 0));
                j = 0;
                buff = new byte[p.dimensione()];
            }
        }
        if (j > 0) {
            byte[] tmp = new byte[j];
            for (int i = 0; i < j; i++) {
                tmp[i] = buff[i];
            }
            res.add(new Pacchetto(0, tmp, 0));
        }
        return res;
    }
}
