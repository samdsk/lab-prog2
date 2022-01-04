public class Term{
    private int coef;
    private int deg;

    public Term(){
        this.coef = 0;
        this.deg = 0;
    }

    public Term(int coef, int deg) throws NegativeExponentException{
        
        if(deg<0) throw new NegativeExponentException("Exponent must be positive.");
        this.coef = coef;
        this.deg = deg;
        
    }
    
    public int coef(){
        return this.coef;
    }

    public int degree(){
        return this.deg;
    }
}