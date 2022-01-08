public class Term{
    //Overview Term is mutable data structure which contains a coefficient and degree of polynomial term.
    // Term(3,2) -> 3x^2 

    //fields
    // Coeffient
    private int coef;
    // Degree of the polynomial term
    private int deg;

    /**
     * Inizialize this as P = 0;
     */
    public Term(){
        this.coef = 0;
        this.deg = 0;
    }
    /**
     * Inizialize this as P = cx^d
     * where c = coef and d = deg
     */
    public Term(int coef, int deg){
        this.coef = coef;
        this.deg = deg;        
    }
    /**
     * Modifies: this.coef
     * Effects: sets this.coef = c
     */
    public void setCoef(int c){
        this.coef = c;
    }

    /**
     * Modifies: this.deg
     * Effects: sets this.deg = d
     */
    public void setDeg(int d){
        this.deg = d;
    }
    
    /**
     * Returns coefficient of the term
     * @return this.coef
     */
    public int coef(){
        return this.coef;
    }
    /**
     * Returns degree of the term
     * @return this.deg
     */
    public int degree(){
        return this.deg;
    }

    /**
     * AF 
     * AF(t) = cx^d 
     *         where c = t.ceof, d = t.deg
     * 
     * RI t.deg >= 0
     * 
     */
    public boolean equals(Term t){
        if(t.deg < 0) return false;
        if(this.coef == t.coef && this.deg == t.deg) return true;
        return false;
    }

    public boolean equals(Object o){       
        if(!(o instanceof Term)) return false;
        return equals((Term) o);
    }

    public boolean repOk(Term t){
        return t.deg>=0;
    }
    @Override
    public String toString(){        
        return this.coef+"x^"+this.deg;
    }
}