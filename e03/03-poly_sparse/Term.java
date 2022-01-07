
public class Term{
    private int coef;
    private int deg;

    public Term(){
        this.coef = 0;
        this.deg = 0;
    }

    public Term(int coef, int deg){
        this.coef = coef;
        this.deg = deg;        
    }

    public void setCoef(int c){
        this.coef = c;
    }

    public void setDeg(int d){
        this.deg = d;
    }
    
    public int coef(){
        return this.coef;
    }

    public int degree(){
        return this.deg;
    }
}