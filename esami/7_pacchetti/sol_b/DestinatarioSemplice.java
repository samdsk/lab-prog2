import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DestinatarioSemplice implements Destinatario{
    /**
     * Overview: Classe concreta implementa interfaccia Destinatario come un Destinatario Semplice in cui 
     *           i pacchetti vengono ricevuti in ordine solo una volta (Caso elementare della traccia)
     * RI: params != null
     *     pacchetti != null
     * 
     * AF: la stringa ottenuto decodificando la lista dei pacchetti
     */

    //parametri di comunicazione
    private final Parametri params;
    //lista di pacchetti ricevuti
    private final List<Pacchetto> pacchetti;
    //la lunghezza del messaggio ricevuto
    private int size;

    public DestinatarioSemplice(Parametri p){
        params = Objects.requireNonNull(p,"Parametri p non può essere null.");
        pacchetti = new ArrayList<>();
        size = 0;
    }

    @Override
    public Parametri parametri() {
        return params;
    }

    @Override
    public boolean ricevi(Pacchetto pacchetto) {
        if (pacchetto == null) {
            stampa();
            return true;
        }

        pacchetti.add(pacchetto);
        size += pacchetto.contenuto().length;
        return true;

    }
    
    //ce un errore nella decodifica non stampa stringa prevista
    @Override
    public String decodifica() {
        byte[] bytes = new byte[size];
        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            for (final Pacchetto p : pacchetti)
                baos.write(p.contenuto());
            return new String(baos.toByteArray(), params.charset());
            //bytes = baos.toByteArray();
        } catch (IOException cantHappen) {
            // Errore da gestire
        }
        return "";
        //return new String(bytes, params.charset());
    }

    @Override
    public String toString() {
        return decodifica();
    }

    @Override
    public void stampa(){
        System.out.println(this.toString());
    }
    
}
