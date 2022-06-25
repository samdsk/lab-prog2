import java.util.Objects;

public class VettoreDenso implements Vettore {
    /**
     * VettoreDenso è una classe concreta immutabile che implementa 
     * Vettore denso di interi di dimensione n
     * 
     * AF(vettore) = (vettore[0], ... , vettore[vettore.length-1])
     * 
     * RI = vettore.length > 0 && vettore != null
     * 
     */

    final private int[] vettore;


    /**
     * Inizializza this con arrat di interi arr
     * @param arr array di interi di dimensione > 0
     * @throws NullPointerException se arr è null
     * @throws IllegalArgumentException se arr non ha almeno un elemento
     */
    public VettoreDenso(final int[] arr){
        Objects.requireNonNull(arr,"l'array arr non può essere null!");
        if(arr.length <= 0) throw new IllegalArgumentException("L'array arr deve avere il meno un elemento");

        this.vettore = arr.clone();

        assert repOk();
    }

    //RI
    private boolean repOk() {
        return vettore.length > 0 && vettore != null;
    }

    @Override
    public int dim() {
       return vettore.length;
    }

    @Override
    public int val(int i) {        
        if(i < 0 ||i >= vettore.length) 
            throw new IndexOutOfBoundsException("indice non può essere >= dimensione del vettore oppure è negativo");
        return vettore[i];
    }

    @Override
    public Vettore per(int alpha) {
        if(alpha == 0) return new VettoreNullo(vettore.length);
        
        int[] arr = new int[this.vettore.length];
        for(int i =0; i < vettore.length; i++) arr[i] = alpha * vettore[i];
        return new VettoreDenso(arr);
    }

    @Override
    public Vettore piu(Vettore v) {
        Objects.requireNonNull(v,"Vettore v non può essere null!");
        if(v.dim() != this.dim()) throw new IllegalArgumentException("I vettori devono avere la stessa dimensione");
        int[] arr = new int[this.vettore.length];

        for(int i = 0; i<this.vettore.length;i++) arr[i] = this.vettore[i] + v.val(i);
        return new VettoreDenso(arr);
    }

    @Override 
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("(");

        for(int i=0 ; i<vettore.length-1;i++ ) output.append(vettore[i] + ", ");

        output.append(vettore[vettore.length-1] + ")");

        return output.toString();
    }
    
}
