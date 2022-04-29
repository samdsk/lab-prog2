import java.nio.charset.Charset;
import java.util.Objects;
import java.util.zip.Checksum;

record Parametri(int dimensione, Charset charset, Checksum algoritmo) {
    /**Overview:    Classe immutabile rappresenta Parametri neccessari per comunicare 
     *              tra sorgente e destinatario tramite il canale
     *              è composto da: 
     *              dimensione: dimensione del contenuto del pacchetto da comunicare
     *              charset: la modalità di codifica utilizzato per codifcare/decodificare il messaggio
     *              algoritmo: l'algoritmo di utilizzato per generare il checksum
     * 
     * RI:  dimensione > 0 &&
     *      charset != null &&
     *      algoritmo != null
    */

    /**
     * Inizializza this con dimensione, charset e algoritmo
     * @param dimensione dimensione del contenuto del pacchetto da comunicare
     * @param charset codifica utilizzato 
     * @param algoritmo l'algoritmo di utilizzato per generare il checksum
     * @throws NullPointerException se charset == null oppure se algoritmo == null
     */
    public Parametri(int dimensione, Charset charset, Checksum algoritmo){
        this.dimensione = dimensione;
        this.charset = Objects.requireNonNull(charset,"Charset non può essere null.");
        this.algoritmo = Objects.requireNonNull(algoritmo,"L'algoritmo non può essere null.");

        assert repOk();
    }

    public boolean repOk(){
        return  dimensione > 0 && charset != null && algoritmo != null;
    }
}