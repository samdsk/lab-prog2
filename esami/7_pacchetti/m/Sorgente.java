import java.nio.charset.Charset;

public interface Sorgente {
    /**
     * Overview:    Interfaccia stabilisce li contratto per il sorgente.
     */

    /**
     * Permette di inviare/trasmettere il dato messagio a un destinatore
     * @param messaggio il messaggio da trasmettere
     * @throws NullPointerException se il messaggio == null
     * @throws IllegalArgumentException se il messaggio è vuoto
     */
    void trasmetti(String messaggio) throws NullPointerException, IllegalArgumentException;

    /**
     * Permette di convertire il messaggio con il dato charset in un array di byte
     * @param messaggio il messaggio da convertire in un array di byte
     * @param charset la codifica da utilizzare
     * @return restituisce un array di byte ottenuto convertendo il dato messaggio
     */
    byte[] encode(String messaggio, Charset charset);
  }