import java.io.ByteArrayInputStream;
import java.util.Objects;

//OVERVIEW: gli oggetti di tipo Pacchetto sono utili a rappresentare un pacchetto di dati, con la relativa posizione nell'insieme 
// dei pacchetti di dati e un relativo checksum (funzione ), utile a capire se due pacchetti sono uguali.

record Pacchetto(int sequenza, byte[] contenuto, long checksum) {

    //costruttore
    public Pacchetto(int sequenza, byte[] contenuto, long checksum) {
        if (sequenza < 0)
            throw new IllegalArgumentException("Il pacchetto non può avere una posizione negativa.");
        Objects.requireNonNull(contenuto);
        if (contenuto.length == 0)
            throw new IllegalArgumentException("Il contenuto del pacchetto non può essere vuoto.");
        //controllo a checksum?

        this.sequenza = sequenza;
        this.contenuto = contenuto;
        /*
        if (checksum == checksum(contenuto)) {
            this.checksum = checksum;
        } else {
            //TODO
            //gestire caso di checksum sbagliato rifare ricevuta?
            this.checksum = -1;
        }
        assert repOk();
        */
        this.checksum = checksum;

        assert repOk();
    }

    /**
     * IR: sequenza >= 0, contenuto non null, contenuto non vuoto, checksum = checksum(contenuto).
     */
    public boolean repOk() {
        if (sequenza >= 0 && contenuto != null && contenuto.length > 0) //&& checksum(contenuto) == checksum)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return new String(this.contenuto);
    }
}
