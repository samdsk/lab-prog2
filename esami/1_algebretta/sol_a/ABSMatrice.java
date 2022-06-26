public abstract class ABSMatrice implements Matrice {
    /**
     * Overview: Classe astratta che implementa parzialmente l'interfaccia Matrice, 
     * aggiunge dei metodi utili che permette di verificare la correttazza
     */

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

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();

        output.append("[");

        for(int i=0;i<dim();i++){
            for(int j=0;j<dim();j++){
                if(j<dim()-1)
                    output.append(val(i,j)+", ");
                else
                    output.append(val(i,j)+"; ");
            }
        }
        output.append("]");
        return output.toString();
    }
}
