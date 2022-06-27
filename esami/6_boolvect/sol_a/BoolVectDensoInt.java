import java.util.Objects;

public class BoolVectDensoInt implements BoolVect {
    /**
     * Overview: BoolVectDeso è una classe concreta mutabile
     * che rappresenta un vettore a valori booleani denso
     * implementando l'interfaccia BoolVect
     * 
     * scelgo come la taglia 10000 
     * 
     * AF = un vettore di valori booleani dove ogni valore è rappresentato da V se True F se False
     * 
     * RI = vettore != null 
     *      && vettore.length == 10000
     *      && pos_last_true >= -1
     */

    final private boolean[] vettore;
    private int pos_first_true;
    final private int taglia = 10000;


    /**
     * Costruisce un BoolVectDensoInt a partire dall'array di booleani arr
     * @param arr array di booleani
     * @throws NullPointerException se l'array arr è null
     * @throws IllegalArgumentException se il numero di elementi dell'array supera la taglia
     */
    public BoolVectDensoInt(final boolean[] arr)throws NullPointerException, IllegalArgumentException{
        Objects.requireNonNull(arr,"Array arr non può essere null!");
        if(arr.length > taglia) throw new IllegalArgumentException("Array arr supera la taglia massima!");
        
        vettore = new boolean[taglia];
        
        for (int i = arr.length-1; i>= 0; i--) {
            vettore[i] = arr[i];
        }
        pos_first_true = findPos();
        
        assert repOk();
    }

    /**
     * Requires: _
     * Modifies: _
     * Effects: Restituisce la posizione del valore booleano che ha valore maggiore
     */
    private int findPos(){
        for(int i = vettore.length; i>=0; i--){
            if(vettore[i]){ 
                return i;
            }
        }

        return -1;
    }

    //RI
    private boolean repOk() {
       if(  vettore != null &&
            vettore.length == taglia &&
            pos_first_true >= 0
       ) return true;

       return false;
    }

    @Override
    public int dim() {        
        return pos_first_true + 1;
    }

    @Override
    public int taglia() {
        return taglia;
    }

    @Override
    public void scrivi(boolean b, int pos) throws IndexOutOfBoundsException {
        if(pos < 0 || pos >= taglia()) 
            throw new IndexOutOfBoundsException("Posizione non valida!");

        vettore[pos] = b;
        pos_first_true = findPos();
        assert repOk();
    }

    @Override
    public boolean leggi(int pos) {
      if(pos<0) throw new IndexOutOfBoundsException("Posizione non può essere negativa!");

      try{
        return vettore[pos];
      }catch(IndexOutOfBoundsException e){
        return false;
      }
    }

    @Override
    public void and(BoolVect B) throws NullPointerException{
        Objects.requireNonNull(B,"BoolVect B non può essere null!");
        //non ce bisogno di controllare perchè valori piu grandi saranno tutti false
        //if(this.taglia < B.taglia()) throw new IllegalArgumentException("BoolVect ha una taglia maggiore di questo BoolVect");        
        int max = this.dim() < B.dim() ? B.dim() : this.dim();

        for(int i = 0;i<max;i++) vettore[i] = vettore[i] & B.leggi(i);
        pos_first_true = findPos();
        assert repOk();
    }

    @Override
    public void or(BoolVect B) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(B,"BoolVect B non può essere null!");

        if(this.taglia < B.taglia()) throw new IllegalArgumentException("BoolVect ha una taglia maggiore di questo BoolVect");        
        
        int max = this.dim() < B.dim() ? B.dim() : this.dim();
        
        for(int i = 0;i<max;i++) vettore[i] = vettore[i] | B.leggi(i);
        
        pos_first_true = findPos();
        assert repOk();
    }

    @Override
    public void xor(BoolVect B) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(B,"BoolVect B non può essere null!");

        if(this.taglia < B.taglia()) throw new IllegalArgumentException("BoolVect ha una taglia maggiore di questo BoolVect");        

        int max = this.dim() < B.dim() ? B.dim() : this.dim();
        
        for(int i = 0;i<max;i++) vettore[i] = vettore[i] ^ B.leggi(i);

        pos_first_true = findPos();

        assert repOk();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = pos_first_true;i>=0;i--){
            str.append(vettore[i] ? "V" : "F");
        }

        return str.toString();
    }
    
}
