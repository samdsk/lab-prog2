import java.util.Objects;

public class CanaleSemplice implements Canale{
    /**
     * Overview: Classe concreta implementa interfaccia Canale
     * 
     * RI: dest != null && 
     *     params != null
     */

    //Parametri di comunicazione
    private final Parametri params;
    //Destinatario a cui inoltrare i pacchetti;
    private final Destinatario dest;

    /**
     * Inizilizza this con il Destinatario d
     * @param d il destinatario a cui inoltrare i pacchetti
     * @throws NullPointerException se Destinatario d == null
     */
    public CanaleSemplice(Destinatario d){
        dest = Objects.requireNonNull(d, "Destinatario non può essere null.");
        params = d.parametri();
    }

    @Override
    public Parametri parametri() {
        return params;
    }

    @Override
    public boolean inoltra(Pacchetto pacchetto){
        return dest.ricevi(pacchetto);
    }
    
}
