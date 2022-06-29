import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Istruzione {
    /**
     * Overview: Istruzione una class immutabile,
     * rappresenta un'istruzione di un straight-line program
     * 
     * es: Y Funzione arg_0, ... ,arg_n
     * 
     * 
     * RI = argomenti != null && funzione != null 
     * && argomenti.length == funzione.numeroArgomenti()
     * && variabile_output != null && funzioni != null
     * 
     */

    //argomenti della funzione
    final private Funzione[] argomenti;

    //funzione a cui applicare argomenti
    final private Funzione funzione;

    //variabile a cui assegnare il risultato
    final private String variabile_output;

    //lista delle funzioni create mi permette di tenere traccia 
    //delle funzioni fino ora costruite e quali variabili sono stati assegnati
    final Map<String, Funzione> funzioni;    

    /**
     * Costruisce un istruzione dato dalla stringa e una mappa di funzioni
     * @param input stringa di istruzione
     * @param map mappa delle funzioni già create
     * @throws NullPointerExpcetion se stringa input oppure map è null
     */
    public Istruzione(final String input, final Map<String, Funzione> map) {
        Objects.requireNonNull(input, "Stringa input non può essere null.");
        Objects.requireNonNull(map, "map non può essere null.");
        if (!(input.length() > 0))
            throw new IllegalArgumentException("String input non può essere vuota.");

        String[] data = Objects.requireNonNull(input).split(" +");

        if (data.length > 28)
            throw new IllegalArgumentException("Istruzione non valida.");

        funzione = createFunzione(data[1]);

        if (data.length - 2 > funzione.numeroArgomenti())
            throw new IllegalArgumentException("Istruzione non valida.");

        argomenti = new Funzione[data.length - 2];


        for (int i = 2; i < data.length; i++) {
            if (map.containsKey(data[0]))
                argomenti[i - 2] = map.get(data[0]);
            else {
                try {
                    int temp = Integer.parseInt(data[i]);
                    argomenti[i - 2] = Funzione.costante(temp);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Istruzione non valida " + e.getMessage());
                }
            }
        }

        applyArgomenti(funzione,argomenti);

        variabile_output = data[0];

        map.put(variabile_output, funzione);

        funzioni = new HashMap<String, Funzione>(map);

    }
    
    /**
     * Costruisce la funzione corrispondente al il nome della funzione dato
     * @param nome il nome della funzione
     * @return la Funzione
     * @throws IllegalArgumentException se la funzione non è stata riconosciuta
     */
    private static Funzione createFunzione(final String nome) {
        switch (Objects.requireNonNull(nome)) {
            case "SUM":
                return new FunzioneSum();
            case "MUL":
                return new FunzioneMul();
            case "MAX":
                return new FunzioneMax();
            case "NEG":
                return new FunzioneNeg();
            default:
                throw new IllegalArgumentException("Funzione non riconosciuta.");

        }
    }
    
    /**
     * Assegna gli argomenti dati alla data funzione
     * @param funzione la funzione a cui applicare le argomenti
     * @param args argomenti da assegnare alla funzione
     * @throws NullPointerException se funzione oppure args è null
     */
    private static void applyArgomenti(Funzione funzione, Funzione[] args) {
        Objects.requireNonNull(funzione);
        Objects.requireNonNull(args);

        for (int i = 0; i < args.length; i++) {
            funzione.argomento(args[i], i);
        }

    }
    
    /**
     * Restituisce l'array di argomenti della funzione di questo istruzione
     * @return array di funzioni
     */
    public Funzione[] argomenti() {
        return argomenti.clone();
    }

    /**
     * Restituisce la funzione di questo istruzione
     * @return la funzione
     */
    public Funzione funzione() {
        return funzione;
    }

    /**
     * Restituisce il variabile a cui assegnare il risultato della funzione
     * @return il variabile a cui assegnare il risultato
     */
    public String variabile_output() {
        return variabile_output;
    }


    /**
     * Restituisco una mappa String, Funzione che tiene traccia delle funzioni fino ora costruite
     * @return una mappa String -> Funzione
     */
    public Map<String, Funzione> funzioni() {
        return new HashMap<>(funzioni);
    }

}
