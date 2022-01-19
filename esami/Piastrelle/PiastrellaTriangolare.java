package Piastrelle;

public class PiastrellaTriangolare implements Piastrella{

    /**
     * RI = base,altezza e costo > 0
     */

    private final int base;
    private final int altezza;
    private final int costo;

    public PiastrellaTriangolare(int b,int h,int c){
        base = b;
        altezza = h;
        costo = c;
    }

    @Override
    public int superficie() {
        return base*altezza/2;
    }

    @Override
    public int costo() {
        return costo;
    }

    @Override
    public String toString() {
        return "Piastrella a forma triangolare con superficie: "+superficie()+"+ e costo:"+costo;
    }
    
}
