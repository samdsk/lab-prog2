import java.util.zip.Checksum;

//OVERVIEW: un oggetto Destinatario è un'entità in grado di ricevere un messaggio codificato e decodificarlo.
public interface Destinatario {
    Parametri parametri();

    boolean ricevi(Pacchetto pacchetto);

    String decode(Parametri parametro, byte[] bytes);

    public long checksum(final byte[] bytes, final Checksum algo);
}

