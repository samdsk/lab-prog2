import java.util.Objects;

public class StellaFissa extends AbsCorpoCeleste{
    /**
     * Overview: le istanze di questo oggetto sono immutabile, rappresenta un corpo celeste di tipo
     * stella fissa quindi velocità pari a zero
     * 
     * RI = nome != null && nome.length()>0 && posizione != null && velocita != null && velocita == 0
     * AF(nome,posizione,velocita) = Stella Fissa: nome, pos: posizione, vel: velocita
     */

     /**
      * Inizializza un nuovo StellaFissa con il dato {@link nome} e posizione iniziale {@link p}
      * @param nome il nome del corpo celeste
      * @param p la posizione iniziale del corpo
      */
    public StellaFissa(String nome, Punto p){
        if(!(Objects.requireNonNull(nome).length()>0))
            throw new IllegalArgumentException("il nome della stella non può essere vuota!");
        Objects.requireNonNull(p);

        this.nome = nome;
        this.posizione = new Punto(p);
        this.velocita = new Punto();

        assert repOk();
    }

    @Override
    public void aggiornaVelocita(CorpoCeleste c){
        return;
    }

    @Override
    public void aggiornaPosizione() {
        return;
    }

    public boolean repOk(){
        return nome != null && 
        nome.length()>0 && 
        posizione != null && 
        velocita != null &&
        velocita.getX() == 0 &&
        velocita.getY() == 0 &&
        velocita.getZ() == 0;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Stella fissa, nome: "+nome+", pos: "+posizione+", vel: "+velocita);
        return str.toString();
    }
}
