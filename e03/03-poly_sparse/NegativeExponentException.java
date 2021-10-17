
//OVERVIEW: Eccezione sollevata nel caso di esponente negativo
public class NegativeExponentException extends RuntimeException {
    public NegativeExponentException() {
        super(); //equivale a RuntimeException();
    }

    public NegativeExponentException(String m) {
        super(m);
    }
    
}