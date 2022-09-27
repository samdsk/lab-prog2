import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class Istruzione {
    private String variabile_output;
    private Funzione f;
    private final String[] args;    

    public Istruzione(final String istruzione){
        if(!(Objects.requireNonNull(istruzione).length()>0)) 
            throw new IllegalArgumentException("L'istruzione non può essere una stringa vuota!");
        
        String data[] = istruzione.split(" +");

        if(data.length<3 || data.length>4) throw new IllegalArgumentException("Istruzione sconosciuta!");

        variabile_output = data[0];

        f = trovaFunzione(data[1]);

        args = Arrays.copyOfRange(data, 2, data.length);
    }

    private Funzione trovaFunzione(String nome){
        switch (nome) {
            case "SUM":
            //System.out.println("creating sum");
                return new FunzioneSum();
            case "MAX":
            //System.out.println("creating max");
                return new FunzioneMax();
            case "MUL":
            //System.out.println("creating mul");
                return new FunzioneMul();
            case "NEG":
            //System.out.println("creating neg");
                return new FunzioneNeg();
            default:
                throw new IllegalArgumentException("Funzione "+nome+" è sconosciuta!");
        }
    }

    public void setArgs(final Map<String,Funzione> elenco_variabili){
        Objects.requireNonNull(elenco_variabili);
        
        //System.out.println("setting args to: "+f);

        for (int i = 0; i < args.length; i++) {
            int valore;

            try {
                valore = Integer.parseInt(args[i]);
                //System.out.println("Valore:"+valore);
                f.argomento(Funzione.costante(valore), i);

            } catch (NumberFormatException e) {
                if(!elenco_variabili.containsKey(args[i])) throw new IllegalArgumentException("Variabile "+args[i]+" non è stato trovato!");
                //System.out.println("### setting arg: "+args[i]+" in posizione "+i+" funzione: "+elenco_variabili.get(args[i]));
                f.argomento(elenco_variabili.get(args[i]), i);
            }
        }

        elenco_variabili.put(variabile_output, f);
    }

    public int valuta(){
        return f.valuta();
    }
}
