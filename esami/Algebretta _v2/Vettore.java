public interface Vettore {
    public int dim();
    public int val(final int i);
    public Vettore per(int alpha);
    public Vettore piu(Vettore v);
    public String vettoreToString();
}

