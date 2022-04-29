public interface Canale {
    /**
     * Overview:    Interfaccia stabilisce il contratto per il canale.
     */

    /**
     * Restituisce i parametri del pacchetto che si sta trasmettendo
     * @return i parametri del pacchetto
     */
    Parametri parametri();

    /**
     * Trasmette il dato pacchetto al destinatario
     * @param pacchetto il pacchetto da trasmettere
     * @return true se è stato inoltrato al destinatario senza problemi altrimenti false
     */
    boolean inoltra(Pacchetto pacchetto);
  }