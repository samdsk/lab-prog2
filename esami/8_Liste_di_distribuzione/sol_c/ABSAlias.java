public abstract class ABSAlias implements Alias{
    protected String nome;

    public String nome(){
        return this.nome;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Alias)) return false;
        
        Alias that = (Alias) o;

        return this.nome().equals(that.nome());

    }
}
