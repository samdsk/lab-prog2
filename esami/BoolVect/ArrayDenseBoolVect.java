public class ArrayDenseBoolVect extends AbsBoolVect {
    //Overview, ArrayDenseBoolVect è un sottotipo di BoolVect di tipo denso
    //il quale utilizza un array di grandezza massima di taglia 1024

    /**
     * AF(vector,taglia,dim) = 
     * per dim != 0
     *      vector[i] dove taglia-dim-1 <= i < taglia, 
     *      i-simo elemento di vector[i] è rappresentato da V se True altrimenti F
     * 
     * se dim == 0  
     *      è una stringa vuota
     * 
     * RI = 
     *      vector != null && dim >= 0 && dim <= taglia &&
     *      dim deve coincidere con la posizione del valore True più grande + 1
     *      se vector contiene solo False dim deve essere == 0
     */

    //il vettore di  booleani
    protected boolean[] vector;
    
    public ArrayDenseBoolVect(){
        super(1024);
        assert repOk();
    }

    public boolean repOk(){
        if(vector != null 
        && dim >= 0 
        && dim <= taglia){

            int count = 0;
            for(int i = 0;i<taglia;i++){
                if(leggi(i)) count = i;
            }

            if(taglia-count == dim) return true;
        }

        return false;
    }

    @Override
    public boolean leggi(int pos){
        try{
            return vector[pos];
        }catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    @Override
    public void scrivi(int pos,boolean b) throws IllegalArgumentException{
        if(pos>dim && !b) return;

        try{
            vector[taglia-1-pos] = b;
            aggiornaDimesione();
            assert repOk();
             
        }catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Errore: posizione supera i limiti del vettore!");
        }
    }

    @Override
    protected void aggiornaDimesione() {
        
        for(int i = 0;i<taglia;i++){
            if(vector[i]) {dim = taglia - i; return;}
        }
        dim = 0;
    }

    @Override
    public void and(BoolVect b) {
        

        int small_dim = dim < b.getDim() ? this.dim:b.getDim();

        for(int i=0; i<small_dim;i++){
            scrivi(i, leggi(i)&&b.leggi(i));
        }

        
    }

    

    @Override
    public void or(BoolVect b) {
        

        int large_dim = dim > b.getDim() ? this.dim:b.getDim();

        for(int i=0; i<large_dim;i++){
            scrivi(i, leggi(i)||b.leggi(i));
        }

        
    }

    @Override
    public void xor(BoolVect b) {
        

        int large_dim = dim > b.getDim() ? this.dim:b.getDim();

        for(int i=0; i<large_dim;i++){
            scrivi(i, leggi(i)^b.leggi(i));
        }

        
    }

    
    @Override
    public String toString() {
        if(dim==0) return "";

        StringBuilder output = new StringBuilder();

        for(int i = taglia-dim; i<taglia;i++){
            if(leggi(i)) output.append("V");
            else output.append("F");
        }

        return output.toString();
    }
    
}
