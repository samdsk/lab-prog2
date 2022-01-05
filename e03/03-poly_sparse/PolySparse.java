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

    /**
     * Individua il polinomio con grado piu alto = large e basso = small, 
     * Appendo la lista large alla lista small, e faccio un sort in ordine crescente.
     * Scorro la lista per coppie First e Second, 
     * Se hanno gradi uguali controllo se hanno la somma 0, 
     *      se la somma è diversa da 0 lo aggiungo alla lista Output la somma
     * Se hanno gradi diversi aggiungo First alla lista,
     * Alla fine della lista aggiungo First che è l'ultimo elemento della lista Small
     */

    public PolySparse add(PolySparse q){
        PolySparse small = this;
        PolySparse large = this;
        if(large.deg < q.deg) large = q;
        else small = q;

        PolySparse output = new PolySparse();

        small.terms.addAll(large.terms);        
        small.terms.sort(new TermCompare());

        output.deg = large.deg;

        Iterator<Term> it = small.terms.iterator();
        Term first = it.next();

        while(true){            
            
            if(it.hasNext()){
                Term second = it.next();
                if(first.degree() == second.degree()){
                    if(first.coef()+second.coef() != 0){                        
                        try{
                            output.terms.add(new Term(first.coef()+second.coef(),first.degree()));                            
                        }catch(NegativeExponentException e){
                            System.out.println("Error: Negative exponent found!");
                        }
                        
                    }
                }else{
                    output.terms.add(first);
                }
                first = second;
            }else{
                output.deg = first.degree();
                output.terms.add(first);
                break;
            }

            
        }
        return output;        
    }

    public PolySparse sub(PolySparse q){
        return this.add(q.minus());
    }

    public PolySparse minus(){
        PolySparse output = new PolySparse();
        Iterator<Term> it = this.terms.iterator();

        while(it.hasNext()){
            Term t = it.next();
            try{
                output.terms.add(new Term( -t.coef(),t.degree()));
            }catch(NegativeExponentException e){
                System.out.println("Error: Negative exponent found!");
            }           
        }

        return output;
    }
}
