import java.util.List;
import java.util.ArrayList;

//OVERVIEW: le istanze di questa classe rappresentano polinomi 
//          gli oggetti di questo tipo NON sono mutabili
//esempio di polinomio: 4x^2 + 5x^1 -4
public class Poly {
    
    /** Array contenente i coefficienti del polinomio */
    private List<Pair> terms;
    
    //COSTRUTTORI

    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio a zero
     */
    public Poly() {
        terms = new ArrayList<>();
        Pair coppia = new Pair(0, 0);
        terms.add(coppia);
    }   

    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio cx^n;
     *                  solleva un'eccezione di tipo NegativeExponentException se 
     *                  se n e' negativo; 
     */
    public Poly(int c, int n) {
        if (n < 0) throw new NegativeExponentException("Costruttore Poly(int, int).");
        if (c == 0) { //se il coeff e' uguale a 0, allora uscira' sempre 0
            n = 0;
        }
        Pair coppia = new Pair(c, n);
        terms = new ArrayList<>();
        terms.add(coppia);
    }

    //METODI

    /**
     * Effetti collaterali: this e' modificato
     * Post-condizioni: restituisce il polinomio this + q;
     *                  solleva un'eccezione di tipo NullPointerException 
     *                  se q punta a null
     */
    public Poly add(Poly q) {
        if (q == null) throw new NullPointerException();
        Poly max, min;

        if (this.degree() > q.degree()) {
            max = this;
            min = q;
        } else {
            min = this;
            max = q;
        }

        int newdegree = max.degree();

        if (this.degree() == q.degree()) {
            for (int k = this.degree(); k > 0; k--) {
                if (terms.get(k).exp + q.terms.get(k).exp != 0) {
                    break;
                } else {
                    newdegree--;
                }
            }
        }

        int i;
        int newdegreecoeff = 0;
        for (i = 0; i < max.terms.size(); i++) {
            if (max.terms.get(i).exp == newdegree) {
                newdegreecoeff = max.terms.get(i).coeff;
            }
        }
        
        Poly result = new Poly(newdegreecoeff, newdegree); 

        for (i = 0; i < this.terms.size(); i++) {
            for (int j = 0; j < q.terms.size(); j++) {
                if (this.terms.get(i).exp == q.terms.get(j).exp) {
                    int coefftemp = terms.get(i).coeff + terms.get(i).coeff;
                    Pair coppia = new Pair(coefftemp, terms.get(i).exp);
                    result.terms.add(coppia);
                } else if (this.terms.get(i).exp != 0) {
                    Pair coppia = new Pair(this.terms.get(i).coeff, this.terms.get(i).exp);
                    result.terms.add(coppia);
                } else if (q.terms.get(j).exp != 0) {
                    Pair coppia = new Pair(q.terms.get(i).coeff, q.terms.get(i).exp);
                    result.terms.add(coppia);
                }
            }
        }

        return result;
    }

    /**
     * Post-condizioni: restituisce il polinomio this / q;
     *                  solleva un'eccezione di tipo NullPointerException 
     *                  se q punta a null
     */
    public Poly sub(Poly q) {
        Poly result = new Poly();

        result = add(q.minus());
        
        return result;
    }

    /**
     * Post-condizioni: restituisce il polinomio this * q;
     *                  solleva un'eccezione di tipo NullPointerException 
     *                  se q punta a null
     */
    public Poly mul(Poly q) {
        return new Poly();
    }

    /**
     * Post-condizioni: restituisce il polinomio -this;
     */
    public Poly minus() {
        Poly result = new Poly(- terms.get(this.degree()).coeff, this.degree());

        for (int i = 0; i < this.terms.size(); i++) {
            int coefftemp = - terms.get(i).coeff;
            Pair coppia = new Pair(coefftemp, terms.get(i).exp);
            result.terms.add(coppia);
        }

        return result;
    }

    /**
     * Post-condizioni: restituisce il grado del polinomio this
     */
    public int degree() {
        int maxdegree = 0;
        for (int i = 0; i < terms.size(); i++) {
            if (terms.get(i).exp > maxdegree) {
                maxdegree = terms.get(i).exp;
            }
        }
        return maxdegree;
    }

    /**
     * Post-condizioni: restituisce il coefficiente del grado d
     */
    public int coeff(int d) {
        if (d < 0 || d > degree()) {
            return 0;
        } else {
            for (int i = 0; i < terms.size(); i++) {
                if (d == terms.get(i).exp) {
                    return terms.get(i).coeff;
                }
            }
        }
        return 0;
    }

    /**
     * Post-condizioni: restituisce una rappresentazione testuale di this 
     */
    /*
    @Override
    public String toString() {
        //todo
    }
    */

    public static void main(String[] args) {
        Poly p = new Poly(1, 4);
        //System.out.println(p.terms.get(0).coeff);
        Poly q = new Poly(2, 3);
        //System.out.println(q.terms.get(0).coeff);
        //System.out.println(q.degree());
        Poly r = q.add(new Poly(5, 16));
        Poly r2 = r.add(new Poly(25, 104));
        System.out.println(r2.terms.get(0).coeff);
        System.out.println(r2.terms.get(0).exp);
        System.out.println(r2.terms.get(1).coeff);
        System.out.println(r2.terms.get(1).exp);
        System.out.println(r2.terms.get(2).coeff);
        System.out.println(r2.terms.get(2).exp);
    }
}
