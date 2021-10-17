//OVERVIEW: Eccezione sollevata nel caso di insieme vuoto
public class EmptyException extends RuntimeException {
    public EmptyException() {
        super(); //equivale a RuntimeException();
    }

    public EmptyException(String m) {
        super(m);
    }
    
}