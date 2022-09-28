import java.util.Objects;

public class CanaleConc implements Canale {

    //attributi di istanza

    private final DestinatarioConc destinatario;

    //costruttore
    public CanaleConc(DestinatarioConc destinatario) {

        Objects.requireNonNull(destinatario);

        this.destinatario = destinatario;

        assert repOk();
    }

    //metodi

    /**
     * Post-condizioni: restituisce i parametri del canale.
     */
    @Override 
    public Parametri parametri() {
        return destinatario.parametri();
    }

    /**
     * Post-condizioni: inoltra il pacchetto "pacchetto" al destinatario "this.destinatario.". Se il checksum svolto dal 
     * destinatario è corrispondente a quello del pacchetto, restituisce true. Restituisce false altrimenti.
     */
    @Override
    public boolean inoltra(Pacchetto pacchetto) {
        /*
        if (destinatario.checksum(pacchetto.contenuto(), destinatario.parametri().algoritmo()) == pacchetto.checksum())
            return true;
        return false;
        */

        return destinatario.ricevi(pacchetto);
    }
    
    /**
     * IR = destinatario non null, parametri del destinatario devono essere validi.
     */
    public boolean repOk() {
        return (destinatario != null && destinatario.parametri().repOk());
    }
    
}
