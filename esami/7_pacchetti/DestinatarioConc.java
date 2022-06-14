import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.zip.Checksum;

public class DestinatarioConc implements Destinatario {
    
    //attributi di istanza
    private final Parametri parametri;
    private ArrayList<Pacchetto> pacchetti = new ArrayList<>(); 

    //costruttore
    public DestinatarioConc(Parametri parametri) {

        Objects.requireNonNull(parametri);

        this.parametri = parametri;

        assert repOk();
    }

    //metodi

    /**
     * Post-condizioni: restituisce i parametri di this.parametri.
     */
    @Override
    public Parametri parametri() {
        return parametri;
    }

    /**
     * Effetti collaterali: modifica this aggiungendo un pacchetto a "pacchetti".
     * Post-condizioni: aggiunge un pacchetto a this se il checksum del pacchetto corrisponde al checksum calcolato nel metodo e restituisce true. Non aggiunge il pacchetto e 
     * restituisce false altrimenti. Solleva NullPointerException se il pacchetto è null.
     */
    @Override
    public boolean ricevi(Pacchetto pacchetto) {

        Objects.requireNonNull(pacchetto);
        
        if (pacchetto.checksum() == checksum(pacchetto.contenuto(), parametri.algoritmo())) {
            pacchetti.add(pacchetto);

            pacchetti.sort(new Comparator<Pacchetto>() {
                @Override
                public int compare(Pacchetto a, Pacchetto b) {
                    if (a.sequenza() < b.sequenza())
                        return -1;
                    else if (a.sequenza() > b.sequenza())
                        return 1;
                    else
                        return 0;
                }
            });

            assert repOk();

            System.out.println(pacchetto);

            return true;
        }
        //richiedere poi il rinvio
        return false;
    }

    /**
     * Post-condizioni: restituisce la stringa decodificata dall'array bytes con charset parametro.charset()
     */
    @Override
    public String decode(Parametri parametro, byte[] bytes) {
        return new String(bytes, parametro.charset());
    }
 
    /**
     * Post-condizioni: restituisce il checksum dell'array di bytes "bytes" usando l'algoritmo algo.
     */
    public long checksum(final byte[] bytes, final Checksum algo)
            throws NullPointerException, IllegalArgumentException {

        Objects.requireNonNull(bytes);

        Checksum algorithm = algo;
        algorithm.reset();
        algorithm.update(bytes);
        return algorithm.getValue();
    }

    /**
     * IR = parametri non null, i parametri devono essere validi.
     */
    public boolean repOk() {
        return (parametri != null && parametri.repOk() && pacchetti != null);
    }
    

}