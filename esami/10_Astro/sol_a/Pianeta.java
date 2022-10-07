import java.util.Objects;

public class Pianeta extends AbsCorpoCeleste{
    /**
     * Overview: le istanze di qeusta classe sono mutabili e rappresenta un corpo celeste di tipo pianta
     * 
     * AF(nome,posizione,velocita) = Pianeta: nome, pos: posizione, vel: velocita
     * 
     * RI = nome != null && nome.length()>0 && posizione != null && velocita != null
     */
    public Pianeta(String nome, Punto p){
        if(!(Objects.requireNonNull(nome).length()>0))
            throw new IllegalArgumentException("il nome del pianeta non può essere vuota!");
        Objects.requireNonNull(p);

        this.nome = nome;
        this.posizione = new Punto(p.getX(),p.getY(),p.getZ());
        this.velocita = new Punto();
    }

    @Override
    public void aggiornaVelocita(CorpoCeleste c){
        Objects.requireNonNull(c);
        //System.out.println("aggiorna velocita:\n\t"+this+"\n\t"+c);

        if(posizione.getX() != c.posizione().getX()){
            if(posizione.getX() > c.posizione().getX()) velocita.updateX(-1);
            else velocita.updateX(1);
        }
        
        if(posizione.getY() != c.posizione().getY()){
            if(posizione.getY() > c.posizione().getY()) velocita.updateY(-1);
            else velocita.updateY(1);
        }
        
        if(posizione.getZ() != c.posizione().getZ()){
            if(posizione.getZ() > c.posizione().getZ()) velocita.updateZ(-1);
            else velocita.updateZ(1);
        }

        //System.out.println("- aggiornato velocita:"+this);
        
    }
    @Override
    public void aggiornaPosizione(){
        //System.out.println("aggiorna posizione:"+this);
        posizione.updateX(velocita.getX());
        posizione.updateY(velocita.getY());
        posizione.updateZ(velocita.getZ());
        //System.out.println("- aggiornato posizione:"+this);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Pianeta, nome: "+nome+", pos: "+posizione+", vel: "+velocita);
        return str.toString();
    }
}
