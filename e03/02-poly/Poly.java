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

    private Poly(int n){
        this.terms = new int[n+1];
        this.deg = n;
    }

    public int degree(){
        return this.deg;
    }

    public int coeff(int d){
        if(this.terms.length<d) throw new IndexOutOfBoundsException("Ceofficient doesn't exists.");
        return this.terms[d];
    }

    public Poly add(Poly q){
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

    public Poly sub(Poly q){
        Poly newpoly = new Poly(q.deg);
        for(int i = 0; i<=q.deg;i++){
            newpoly.terms[i] = -(q.terms[i]);
        }
        return add(newpoly);
    }

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
        for (int i : terms) {
            if(i==0) continue;
            else output += i+"x^"+this.deg;
        }
        return output;
    }
}
