import java.util.zip.Checksum;

//OVERVIEW: un oggetto Sorgente è un'entità in grado di trasmettere un messaggio dopo averlo codificato.
public interface Sorgente {
    void trasmetti(String messaggio);

    Pacchetto[] encode(Parametri parametro, String s);

    long checksum(final byte[] bytes, final Checksum algo);
}
