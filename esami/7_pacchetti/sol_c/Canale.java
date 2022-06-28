/**
 * Questa interfaccia racchiude le funzionalità
 * necessarie a inoltrare un pacchetto dalla sorgente al destinatario
 */
public interface Canale {
    /**
     * Restituisce i parametri di trasmissione di un pacchetto
     */
    Parametri parametri();

    /**
     * Inoltra al destinatario il pacchetto
     * @param pacchetto da inviare
     * @return True nel caso ci sia stata una alterazione del pacchetto 
     * durante la trasmissione, False altrimenti
     */
    boolean inoltra(Pacchetto pacchetto);
}

