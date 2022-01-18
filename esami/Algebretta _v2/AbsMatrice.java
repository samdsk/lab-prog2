public abstract class AbsMatrice implements Matrice{
    /**
     * AF
     */
    @Override
    public String toString() {
        String output = "[";

        for (int i = 0; i < dim(); i++) {
            output += val(i).vettoreToString();
            if(i<dim()-1) output += "; ";
        }

        return output += "]";
    }

}
