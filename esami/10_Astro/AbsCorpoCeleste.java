public abstract class AbsCorpoCeleste implements CorpoCeleste{
    protected String nome;
    protected Punto posizione;
    protected Punto velocita;

    public long energia(){
        return energiaPotenziale()*energiaCinetica();
    }
    
    public long energiaPotenziale(){
        //System.out.println("EP:"+posizione.norma());
        return posizione.norma();
    }

    public long energiaCinetica(){
        //System.out.println("EC: "+velocita.norma());
        return velocita.norma();
    }

    public Punto posizione(){
        return new Punto(posizione);
    }

    public Punto velocita(){
        return new Punto(velocita);
    }

    public String nome(){
        return nome;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof CorpoCeleste)) return false;

        CorpoCeleste c = (CorpoCeleste) o;
        return this.nome().equals(c.nome()); 
    }
}
