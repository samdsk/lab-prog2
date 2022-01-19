package Piastrelle;

public class PiastrellaQuadrato implements Piastrella{

    /**
     * RI = lato e costo > 0
     */

    private final int lato;
    private final int costo;

    public PiastrellaQuadrato(int l,int c){
        lato = l;
        costo = c;
    }

    @Override
    public int superficie() {
        return lato*lato;
    }

    @Override
    public int costo() {
        return costo;
    }

    @Override
    public String toString() {
        return "Piastrella a forma quadratica con superficie: "+superficie()+"+ e costo:"+costo;
    }
    
}
