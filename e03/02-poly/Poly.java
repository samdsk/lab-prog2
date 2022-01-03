public class Poly {
    //Overview Poly class is immutable, creates dense polynomial with integer ceffiecients.
    //es. c_0 + c_1 * x + ... + c_n x^n

    //an array of integer coefficients of length degree+1
    private final int[] terms;
    //the degree of the polynomial
    private final int deg;

    //constructors

    /**
     * Initialize a empty polynomial
     */
    public Poly(){
        this.terms = new int[1];
        this.deg = 0;
    }

    /**
     * Initialize a polynomial of degree N with coefficient C, es. cx^n
     */
    public Poly(int c,int n) throws NegativeExponentException{        
        if(n<0) throw new NegativeExponentException("Exponent must be positive.");

        if(c==0) this.deg = 0;
        else this.deg = n;

        this.terms = new int[deg+1];
        this.terms[deg] = c;
    }

    /**
     * Initialize a polynomial of degree N with all coefficients = 0.
     */
    private Poly(int n){
        this.terms = new int[n+1];
        this.deg = n;
    }

    //methods

    /**
     * Requires: _
     * Modifies: _
     * Effeects: Returns the degree of the poly
     */
    public int degree(){
        return this.deg;
    }
    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns the coefficient of the given term D.
     *          Throws IllegalArgumentException if exponent is negative.
     */
    public int coeff(int d){
        if(d<0) throw new IllegalArgumentException("Exponent must be positive");
        if(d>this.deg) return 0;
        return this.terms[d];
    }

    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns a new polynomial produced by performing polynomial addition between This and Q.
     *          Throws NullPointerException if q == null.
     */
    public Poly add(Poly q){
        if(q == null) throw new NullPointerException("Polynomial provided is null.");
        Poly large = this;
        Poly small = this;

        if(large.deg < q.deg) large = q;
        else small = q;

        int newdeg = large.deg;
        if(small.deg==newdeg){
            for (int i = newdeg; i>0; i--) {
                if(small.terms[i] + large.terms[1] != 0) break;
                else newdeg--;
            }
        }

        Poly output = new Poly(newdeg);
        int count;
        for(count = 0;count<=small.deg && count<=newdeg;count++){
            output.terms[count] = small.terms[count]+large.terms[count];
        }

        for(int i = count; i<=newdeg;i++){
            output.terms[i] = large.terms[i];
        }
        return output;

    }
    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns a new polynomial produced by performing polynomial subtraction between This and Q.
     */
    public Poly sub(Poly q){        
        return add(q.minus());
    }
    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns a identical polynomial with all terms negated.
     */
    public Poly minus(){
        Poly output= new Poly(this.deg);
        for(int i = 0; i<=this.deg;i++){
            output.terms[i] = -(this.terms[i]);
        }
        return output;
    }

    /**
     * Requires: _
     * Modifies: _
     * Effects: Returns a new polynomial produced by performing polynomial multiplication between This and Q.
     */
    public Poly mul(Poly q){
        if(this.deg == 0 && this.terms[0] == 0 || q.deg == 0 && q.terms[0] == 0){ 
            return new Poly();
        }
        
        Poly output = new Poly(this.deg + q.deg);

        for(int i=0;i<=this.deg;i++){
            for(int j=0;j<=q.deg;j++){
                output.terms[i+j] += this.terms[i] * q.terms[j]; 
            }
        }

        return output;
    }

    /** AF */
    @Override
    public String toString(){
        String output = "";
        for (int i = this.deg;i>0;i--) {
            output += this.terms[i]+"x^"+i;
        }
        return output;
    }
}
