import java.text.ParseException;
import java.util.Objects;

public class Durata {
    //Overview: Durata è una classe mutabile, è misurato in secondi, 
    //          permette di effettuare somme e sotrazioni fra durate
    
    /*
     * RI = durata >= 0
     * 
     * AF(HH,mm,ss) =   HH:mm:ss se HH>0
     *                  mm:ss altrimenti
     */

    private int durata;

    /**
     * Costruisce una nuova Durata a partire dall'array di interi tempo
     * @param tempo un array di stringhe di lunghezza massima 3 (HH:mm:ss)
     * @throws NullPointerException se array tempo è null
     * @throws IllegalArgumentsException se array tempo ha una lunghezza maggiore di 3
     *         oppure se c'è un errore durante la conversione ad intero
     */
    public Durata(String[] tempo){
        Objects.requireNonNull(tempo,"Array non può essere null!");
        if(tempo.length>3) throw new IllegalArgumentException("Array di interi può essere al massimo di lunghezza 3");
        try{
            if(tempo.length == 3){
                durata = Integer.parseInt(tempo[2]);
                durata += Integer.parseInt(tempo[1])*60;
                durata += Integer.parseInt(tempo[0])*60*60;
            }else if(tempo.length == 2){
                durata = Integer.parseInt(tempo[1]);
                durata += Integer.parseInt(tempo[0])*60;
            }else{
                durata = Integer.parseInt(tempo[0]);
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Contiene non numeri "+e.getMessage());
        }

        assert repOk();
    }
    /**
     * Costruisce una nuova Durata a partire dai secondi forniti
     * @param secondi il numero di secondi con cui inizializzare this
     * @throws IllegalArgumentException se il numero di secondo fornito è negativo
     */
    public Durata(int secondi){
        if(secondi<0) throw new IllegalArgumentException("Deve essere un numero positivo!");
        durata = secondi;
        assert repOk();
    }

    public int getDurata(){
        return durata;
    }

    /**
     *  Modifies: modifica la this.durata sommando con la durata "d.durata" data
     *  Effects: Throws NullPointException se la durata "d" fornito è null
     */
    public void somma(Durata d){
        Objects.requireNonNull(d,"Durata non può essere null!");
        this.durata += d.durata;
        assert repOk();
    }

    /**
     *  Modifies: modifica la this.durata sottraendo con la durata "d" data
     *  Effects: Throws NullPointException se la durata "d" fornito è null
     *           Throws IllegalArgumentException se la durata "d.durata" fornito è >= this.durata
     */
    public void sottrazione(Durata d){
        Objects.requireNonNull(d,"Durata non può essere null!");
        if(this.durata - d.durata >= 0){
            this.durata -= d.durata;
        }else{
            throw new IllegalArgumentException("Durata da sottrarre è troppo grande!");
        }

        assert repOk();
    }
    //RI
    public boolean repOk(){
        return durata>=0;
    }

    //AF
    @Override
    public String toString(){
        int ss = durata%60;
        int mm = (durata/60)%60;
        int hh = durata/3600;
        if(hh == 0) return new String(String.format("%2d:%2d",mm,ss));
        return new String(String.format("%2d:%2d:%2d",hh,mm,ss));
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Durata)) return false;

        Durata d = (Durata) o;

        return this.durata == d.durata;
    }
}
