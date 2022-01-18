public abstract class AbsVettore implements Vettore{
    /**AF */
    @Override
    public String toString() {
        String output = "(";
        for (int i = 0; i < dim(); i++) {
            output += val(i);
            if(i<dim()-1) output+= ", ";
        }

        return output += ")";
    }

    /**
     * Effects: restituisce una String di formato vettore[0],...,vettore[i] dove i 0<=i<dim
     */
    public String vettoreToString(){
        String output = "";
        for (int i = 0; i < dim(); i++) {
            output += val(i);
            if(i<dim()-1) output+= ", ";
        }

        return output;
    }
}
