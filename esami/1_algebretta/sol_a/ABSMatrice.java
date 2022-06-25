public abstract class ABSMatrice implements Matrice {

    /**
     * Restituisce true se gli indici i e j sono entrambi validi
     * @param i indice della i-esima riga
     * @param j indice della j-esima colonna
     * @return true se indici sono validi false altrimenti
    */
    protected boolean checkIndex(final int i,final int j){
        if(i >= dim() || i<0 || j >= dim() || j <= 0) return false; 
        return true;
    }


    /**
     * Restituisce true se questa matrice e matrice m hanno la stessa dimensione
     * @param m la matrice con cui confrontare
     * @return true se hanno la stessa dimensione altrimenti false
     */
    protected boolean checkDim(Matrice m){
        if(this.dim() != m.dim()) return false;
        return true;
    }

        /**
     * Restituisce true se questa matrice e vettore v hanno la stessa dimensione
     * @param v il vettore con cui confrontare
     * @return true se hanno la stessa dimensione altrimenti false
     */
    protected boolean checkDim(Vettore v){
        if(this.dim() != v.dim()) return false;
        return true;
    }
}
