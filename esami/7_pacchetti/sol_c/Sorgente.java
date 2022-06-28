/**
 * L'intefaccia Sorgette rappresenta le funzionalità
 * necessarie ad un sorgente per trasmettere un messaggio
 */
public interface Sorgente {

    /**
     * Trasmette un messaggio al destinatario
     * @param messaggio da inviare
     */
    void trasmetti(String messaggio);
}