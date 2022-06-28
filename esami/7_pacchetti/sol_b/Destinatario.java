public interface Destinatario {
    /**
     * Overview:    Interfaccia stabilisce li contratto per un destinatario.
     */


    /**
     * Restituisce i parametri del messaggio che ha ricevuto
     * @return i parametri del pacchetto
     */
    Parametri parametri();

    /**
     * Riceve il dato pacchetto dal canale
     * @param pacchetto il pacchetto da ricevere
     * @return true se è stato ricevuto senza problemi altrimenti false
     *
     */
    boolean ricevi(Pacchetto pacchetto);

    /**
     * Decodifica la lista dei pachetti ricevuti
     * @return restituisce la stringa del messaggio ricevuto
     */
    String decodifica();

    /** Stampa a video (System.out) il messaggio ricevuto */
    void stampa();
  }