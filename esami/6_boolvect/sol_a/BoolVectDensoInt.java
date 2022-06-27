import java.util.Objects;

public class BoolVectDensoInt implements BoolVect {
    /**
     * Overview: BoolVectDeso è una classe concreta 
     * che rappresenta un vettore a valori booleani denso
     * implementando l'interfaccia BoolVect
     * 
     * scelgo come la taglia 10000 
     * 
     * AF = un vettore di valori booleani dove ogni valore è rappresentato da V se True F se False
     * 
     * RI = vettore != null 
     *      && vettore.length <= 10000
     *      && pos_last_true >= 0
     */

    final private boolean[] vettore;
    final private int pos_first_true;
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

        vettore = arr.clone();
        pos_first_true = findPos();
        
        assert repOk();
    }

    /**
     * Requires: _
     * Modifies: _
     * Effects: Restituisce la posizione del valore booleano che sta piu a sinistra
     */
    private int findPos(){
        for(int i = 0; i<vettore.length; i++){
            if(vettore[i]){ 
                return i;
            }
        }

        return 0;
    }

    //RI
    private boolean repOk() {
       if(  vettore != null &&
            vettore.length <= taglia &&
            pos_first_true >= 0
       ) return true;

       return false;
    }

    /**
     * Restituisce true se la poszione fornita è una posizione valida
     * @param pos la posizione
     * @return true se posizione è valida false altrimenti
     * @throws IndexOutOFBoundsException se posizione non valida
     */
    private void isItValid(final int pos){
        if(pos < 0 || pos >= taglia() || pos >= vettore.length) 
            throw new IndexOutOfBoundsException("Posizione non valida!");

        return;
    }

    @Override
    public int dim() {
       return vettore.length - pos_first_true - 1;
    }

    @Override
    public int taglia() {
        return taglia;
    }

    @Override
    public BoolVect scrivi(boolean b, int pos) throws IndexOutOfBoundsException {
        isItValid(pos);
        boolean[] temp = vettore.clone();
        temp[pos] = b;
        
        return new BoolVectDensoInt(temp);
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
    public BoolVect and(BoolVect B) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(B,"BoolVect B non può essere null!");
        if(this.taglia != B.taglia()) throw new IllegalArgumentException("as");
        boolean[] res = new boolean[vettore.length];

        for(int i = 0;i<vettore.length;i++) res[i] = vettore[i] && B.leggi(i);

        return new BoolVectDensoInt(res);
    }

    @Override
    public BoolVect or(BoolVect B) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(B,"BoolVect B non può essere null!");
        boolean[] res = new boolean[vettore.length];

        for(int i = 0;i<vettore.length;i++) res[i] = vettore[i] || B.leggi(i);

        return new BoolVectDensoInt(res);
    }

    @Override
    public BoolVect xor(BoolVect B) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(B,"BoolVect B non può essere null!");
        boolean[] res = new boolean[vettore.length];

        for(int i = 0;i<vettore.length;i++) res[i] = vettore[i] ^ B.leggi(i);

        return new BoolVectDensoInt(res);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = pos_first_true;i<vettore.length;i++){
            str.append(vettore[i] ? "V" : "F");
        }

        return str.toString();
    }
    
}
