import java.nio.charset.Charset;
import java.util.Objects;
import java.util.zip.Checksum;

//OVERVIEW: gli oggetti di tipo Parametri rappresentano un insieme di dimensione, charset e algoritmo
// da associare ad una trasmissione per la sua codifica/decodifica

record Parametri(int dimensione, Charset charset, Checksum algoritmo) {

    //costruttore
    public Parametri(int dimensione, Charset charset, Checksum algoritmo) {
        if (dimensione <= 0)
            throw new IllegalArgumentException("Il pacchetto non può avere una dimensione negativa.");

        Objects.requireNonNull(charset);
        Objects.requireNonNull(algoritmo);

        this.dimensione = dimensione;
        this.charset = charset;
        this.algoritmo = algoritmo;

        assert repOk();
    }

    /**
     * IR: dimensione > 0, charset non null, algoritmo non null. 
    */
    public boolean repOk() {
        return (dimensione > 0 && charset != null && algoritmo != null);
    }
}