
public abstract class AbsBoolVect implements BoolVect{
    //Overview,  BoolVect è una classe mutabile, rappresenta un vettore booleano, che può essere sia denso che sparso,
    //ha una taglia che rappresenta la grandezza massima che può raggiungere
    
    //dim è la dimensione del vettore cioè la posizione del valore True più grande
    protected int dim;
    //la taglia del vettore
    protected int taglia;

    public AbsBoolVect(int size){
        dim = 0;        
        taglia = size;
    }

    protected abstract void aggiornaDimesione();

    public int getDim(){
        return this.dim;
    }

    

}