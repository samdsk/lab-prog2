import java.util.ArrayList;

public class Poly {

    private final int[] terms;
    private final int deg;
    
    public Poly(){
        this.terms = new int[1];
        this.deg = 0;
    }

    public Poly(int c,int n) throws NegativeExponentException{
        if(n<0) throw new NegativeExponentException("Exponent must be positive.");

        if(c==0) this.deg = 0;
        else this.deg = n;

        this.terms = new int[deg+1];
        this.terms[deg] = c;
    }

    public int degree(){
        return this.deg;
    }

    public int coeff(int d){
        if(this.terms.length<d) throw new IndexOutOfBoundsException("Ceofficient doesn't exists.");
        return this.terms[d];
    }

    public Poly add(Poly q){
        int newdeg = this.deg;
        if()
    }

    /** AF */
    @Override
    public String toString(){
        String output = "";
        for (int i : terms) {
            if(i==0) continue;
            else output += i+"x^"+this.deg;
        }
        return output;
    }
}
