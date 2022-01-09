import java.util.Objects;

public class Rational {
    //Overview Rational is immutable, rappresents rational numbers es. n/d

    /**
     * AF(r) = r.n/r.d se d != 1
     *         r.n se d == 1
     * 
     * RI(r) = d > 0 
     *      
     */

    //fields
    /**numeratore n del numero razionale n/d */
    private final int n;
    /**denominatore d del numero razionale n/d */
    private final int d;

    //constructors

    /**
     * Initialize this as zero == 0/1    
     */
    public Rational(){
        n = 0;
        d = 1;
        assert repOk();
    }

    /**
     * Initialize this as n/d 
     * throws DividedByZeroException if d = 0
     */
    public Rational(int nu, int de){
        if(de==0) throw new ArithmeticException("Denominatore == 0!");
        if(de<0){
            nu = -nu;
            de = -de;
        }
        this.n = nu;
        this.d = de;
        assert repOk();
    }

    /**
     * Effects: Returns a new rational number obtained by performing addition between this and r
     */
    public Rational add(Rational r){
        if(d==r.d) return new Rational(n+r.n,d);
        
        int new_d = d*r.d;
        int new_n = n*r.d + r.n*d;
        
        return new Rational(new_n,new_d);
    }
    /**
     * Effects: Returns a new rational number obtained by performing subtraction between this and r
     */
    public Rational sub(Rational r){
        return add(r.minus());
    }

    /**
     * Effects: Returns a new rational number obtained by negating this
     * Es. n/d -> -n/d
     */
    public Rational minus(){
        return new Rational(-n,d);
    }

    /**
     * Effects: Returns a new rational number obtained by performing multiplication between this and r
     */
    public Rational mul(Rational r){
        return(new Rational(n*r.n,d*r.d));
    }

    /**
     * Effects: Returns a new rational number obtained by performing division between this and r
     */
    public Rational div(Rational r){
        return mul(r.reciprocal());
    }
    /**
     * Effects: Returns the reciprocal of this
     */
    public Rational reciprocal(){
        return new Rational(d,n);
    }
    /**
     * Effects: Returns a new semplified rational number 
     * Es. 12/15 -> 4/5
     */
    public Rational semplify(){
        int cur = 2;
        int divid = 1;
        while(cur<=d/2){
            if(n%cur == 0 && d%cur == 0)
                divid = cur;
            cur++;  
        }

        if(divid == 1) return this;
        return new Rational(n/divid,d/divid);
    }
    /**AF */
    @Override
    public String toString(){
        if(d==1) return ""+n;
        return ""+n+"/"+d;
    }
    /**RI */
    public boolean repOk(){
        if(d>0) return true;
        return false;
    }

    /** */
    public boolean equals(Object o){
        if(!(o instanceof Rational)) return false;

        Rational r = (Rational) o;
        if(n!=r.n || d!=r.d) return false;
        return true;
    }

    @Override
    public int hashCode() {
      return Objects.hash(n, d);
    }
}
