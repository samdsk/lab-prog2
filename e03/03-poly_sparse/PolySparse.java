import java.security.spec.ECFieldF2m;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class PolySparse {
    //OVerview PolySparse is immutable class rapresents Sparse Polynomial
    private final ArrayList<Term> terms;
    private int deg;

    public PolySparse(){
        this.terms = new ArrayList<>();
        this.deg = 0;
    }

    public PolySparse(Term t){
        this.terms = new ArrayList<Term>();
        if(t.coef()==0) this.deg = 0;
        else{
            this.deg = t.degree();
            this.terms.add(t);            
        }
    }

    public int degree(){
        return this.deg;
    }

    public int ceoff(int d) throws Exception{
        if(d<0) throw new NegativeExponentException("Exponent must be positive.");
        Iterator<Term> it = terms.iterator();
        
        while(it.hasNext()){
            Term t = it.next();
            if(t.degree() == d) return t.coef(); 
        }        
        
        throw new Exception("Term not found.");        
    }

    private class TermCompare implements Comparator<Term>{
        public int compare(Term p, Term q){
            Integer p_deg = p.degree();
            Integer q_deg = q.degree();
            return p_deg.compareTo(q_deg);
        }
    }

    public PolySparse add(PolySparse q){
        PolySparse small = this;
        PolySparse large = this;
        if(large.deg < q.deg) large = q;
        else small = q;

        int newdeg = large.deg;
        PolySparse output = new PolySparse();

        output.terms.addAll(small.terms);
        output.terms.addAll(large.terms);
        output.terms.sort(new TermCompare());

        output.deg = large.deg;

        Iterator<Term> it = output.terms.iterator();

        while(it.hasNext()){
            Term first = it.next();
            int index = output.terms.indexOf(first);
            if(it.hasNext()){
                Term second = it.next();
                if(first.degree() == second.degree()){
                    if(first.coef()+second.coef() == 0){
                        output.terms.remove(first);
                        output.terms.remove(second);
                        output.deg--;
                    }else{
                        try{
                            output.terms.set(index, new Term(first.coef()+second.coef(),first.degree()));
                        }catch(NegativeExponentException e){
                            System.out.println("Error: Negative exponent found!");
                        }
                        
                    }
                }
            }
        }

        
    }
}
