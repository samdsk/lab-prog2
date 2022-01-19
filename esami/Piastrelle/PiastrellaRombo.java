package Piastrelle;

public class PiastrellaRombo implements Piastrella {

    /**
     * RI = diagonali d1, d2 e costo >0 
     */

    private final int d1;    
    private final int d2;
    private final int costo;

    public PiastrellaRombo(int d1,int d2,int c){
        this.d1 = d1;
        this.d2 = d2;
        this.costo = c;
    }

    @Override
    public int superficie() {
        return d1*d2 / 2;
    }

    @Override
    public int costo() {
        return costo;
    }

    @Override
    public String toString() {
        return "Piastrella a forma romboidali con superficie: "+superficie()+"+ e costo:"+costo;
    }
    
}
