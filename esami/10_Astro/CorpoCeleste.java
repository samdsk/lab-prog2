public interface CorpoCeleste{
    void aggiornaVelocita(CorpoCeleste c);

    void aggiornaPosizione();

    long energiaPotenziale();

    long energiaCinetica();

    long energia();

    String nome();

    Punto posizione();

    Punto velocita();

}