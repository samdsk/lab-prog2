////OVERVIEW: un oggetto Canale è un'entità in grado di ricevere e inoltrare un pacchetto.
public interface Canale {
    Parametri parametri();

    boolean inoltra(Pacchetto pacchetto);
}