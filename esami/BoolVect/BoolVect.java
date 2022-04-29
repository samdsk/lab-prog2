public interface BoolVect {

    public boolean leggi(int pos);
    public void scrivi(int pos,boolean b) throws IllegalArgumentException;
    public void and(BoolVect b);
    public void or(BoolVect b);
    public void xor(BoolVect b);
    public int getDim();
    
}
