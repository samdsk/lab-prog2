import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.zip.Checksum;

public class SorgenteSemplice implements Sorgente {
    /**
     * Overview:    Classe concreta implementa interfaccia Sorgente come un Sorgente Semplice in cui 
     *              i pacchetti vengono inviati in ordine solo una volta (Caso elementare della traccia)
     * 
     * RI: params != null && canale != null
     */



    //il messaggio codificato in un array di bytes
    private byte[] bytes;
    //i parametri di comunicazione
    private Parametri params;
    //il canale di comunicazione
    private Canale canale;

    /**
     * Inizializza this con il Canale c
     * @throws NullPointerException se Canale c == null
     */
    public SorgenteSemplice(Canale c) {
        canale = Objects.requireNonNull(c, "Canale non può essere null.");
        params = c.parametri();
    }

    //Errore nella lettura 
    @Override
    public void trasmetti(String messaggio) throws NullPointerException, IllegalArgumentException {
        if(Objects.requireNonNull(messaggio, "Messaggio non può essere null.").length()==0)
            throw new IllegalArgumentException("Messaggio non può essere vuoto.");
        
        bytes = encode(messaggio, params.charset());
        int count = 0;
        byte[] buffer = new byte[params.dimensione()];
        
        try{
            ByteArrayInputStream b = new ByteArrayInputStream(bytes);
            //int count = 0;
            while(b.available()>0){
                b.read(buffer);

                Checksum algorithm = params.algoritmo();
                algorithm.reset();
                algorithm.update(buffer);
                long checksum =  algorithm.getValue();

                Pacchetto p = new Pacchetto(count++, buffer, checksum);
                canale.inoltra(p);
            }

        }catch(IOException e){
            //Errore da gestire
        }

        canale.inoltra(null);
    }

    @Override
    public byte[] encode(String messaggio, Charset charset) {
        return messaggio.getBytes(charset);
    }

    
    
}
