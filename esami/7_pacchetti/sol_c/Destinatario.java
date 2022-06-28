/**
 * Questa intefaccia rappresenta le funzionalità necessarie
 * ad un destinatario in uno scambio di messaggi
 */
public interface Destinatario {
    /**
     * Restituisce i parametri di trasmissione per la decodifica
     * di un pacchetto
     */
    Parametri parametri();

    /**
     * Riceve un pacchetto da una sorgente
     * @param pacchetto da decodificare
     * @return True nel caso ci sia stata una alterazione del pacchetto 
     * durante la ricezione, False altrimenti
     */
    boolean ricevi(Pacchetto pacchetto);
}
