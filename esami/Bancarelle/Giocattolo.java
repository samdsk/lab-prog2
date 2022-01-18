package Bancarelle;

public class Giocattolo {
    private final String nome;
    private final String materiale;

    public Giocattolo(String n,String m){
        if(n==null || m==null) 
            throw new NullPointerException("Nome del giocattolo e materiale non possono essere nulli.");
        nome = n;
        materiale = m;

        assert repOk();
    }

    public String getNome(){
        return nome;
    }

    public String getMateriale(){
        return materiale;
    }

    public boolean repOk(){
        if(nome==null || materiale==null) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Giocattolo)) return false;
        Giocattolo g = (Giocattolo) o;

        if(nome != g.getNome() || materiale != g.getMateriale())
        return false;

        return true;
    }

    @Override
    public String toString() {
        return nome+" di "+materiale;
    }
}
