public interface Matrice {
    
    public int dim();
    public int val(final int i, final int j);
    public Vettore val(int i);    
    public Matrice piu(final Matrice m);
    public Matrice per(final int alpha);
    public Matrice per(final Matrice m);

}
