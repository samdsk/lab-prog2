//OVERVIEW: le istanze di questa classe rappresentano polinomi 
//          gli oggetti di questo tipo NON sono mutabili
//esempio di polinomio: 4x^2 + 5x^1 -4
public class Poly {
    
    /** Array contenente i coefficienti del polinomio */
    private final int[] terms;

    /** Grado del poliniomio */
    private final int degree;
    
    //COSTRUTTORI

    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio a zero
     */
    public Poly() {
        degree = 0;
        terms = new int[degree+1];
    }   

    /**
     * Post-condizioni: inizializza this affinche' rappresenti il polinomio cx^n;
     *                  solleva un'eccezione di tipo NegativeExponentException se 
     *                  se n e' negativo; 
     */
    public Poly(int c, int n) {
        if (n < 0) throw new NegativeExponentException("Costruttore Poly(int, int).");
        if (c == 0) { //se il coeff e' uguale a 0, allora uscira' sempre 0
            degree = 0;
        } else {
            degree = n;
        }
        terms = new int[degree + 1]; //es: degree = 4, array[0,1,2,3,4]
        terms[degree] = c; //vado a mettere il coefficiente dove vi e' il grado
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
        if (degree > q.degree) {
            max = this;
            min = q;
        } else {
            min = this;
            max = q;
        }
        int newdegree = max.degree;

        if (degree == q.degree) { //trailing zeros, liskov
            for (int k = degree; k > 0; k--) {
                if (terms[k] + q.terms[k] != 0) {
                    break;
                } else {
                    newdegree--;
                }
            }
        }

        int i;
        Poly result = new Poly(max.terms[newdegree], newdegree); //creo nuovo poly
        for (i = 0; i<= min.degree && i <= newdegree; i++) {
            result.terms[i] = min.terms[i] + max.terms[i]; //penso di andare outofbounds sull'array piu piccolo
        }

        for (int j = i; j <= newdegree; j++) {
            result.terms[j] = max.terms[j];
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
        if ((q.degree == 0 && q.terms[0] == 0) || (degree == 0 && terms[0] == 0)) {
            return new Poly();
        }

        //un errore qua
        Poly result = new Poly (1, degree + q.degree);

        result.terms[degree + q.degree] = 0;

        for (int i = 0; i <= degree; i++) {
            for (int j = 0; j <= q.degree; j++) {
                result.terms[i+j] = result.terms[i+j] + terms[i] * q.terms[j]  ; //gestisco sia addizione ai vecchi membri che moltiplicazione con nuovi membri
            }
        }

        return result;
    }

    /**
     * Post-condizioni: restituisce il polinomio -this;
     */
    public Poly minus() {
        Poly result = new Poly(- terms[degree], degree);
        for (int i = 0; i < degree; i++) {
            result.terms[i] = - terms[i];
        }
        return result;
    }

    /**
     * Post-condizioni: restituisce il grado del polinomio this
     */
    public int degree() {
        return degree;
    }

    /**
     * Post-condizioni: restituisce il coefficiente del grado d
     */
    public int coeff(int d) {
        if (d < 0 || d > degree) {
            return 0;
        } else {
            return terms[d];
        }
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


}

//array di struct per il prossimo