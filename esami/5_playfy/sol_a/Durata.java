import java.util.Objects;

public class Durata{
    final private int secondi;

    public Durata(final int sec){
        if(sec<0) throw new IllegalArgumentException("La durata non può essere negativa.");

        this.secondi = sec;

        assert repOk();
    }


    public boolean repOk() {
        return secondi >= 0;
    }

    public int getSecondi(){
        return secondi;
    }

    public Durata sum(Durata B){
        return new Durata(secondi + Objects.requireNonNull(B).getSecondi());
    }

    public Durata diff(Durata B){
        Objects.requireNonNull(B);
        if(secondi - B.getSecondi() < 0) throw new IllegalArgumentException("Durata negativa!");
        return new Durata(secondi - B.getSecondi());
    }

    public static Durata parseDurata(final String durata){
        Objects.requireNonNull(durata,"la durata non può essere null.");

        
        String[] str = durata.trim().split(":");
        if(str.length <= 0 || str.length > 3) throw new IllegalArgumentException("Stringa non valida");

        return new Durata(convertToSec(str));
    }


    private static int convertToSec(String[] str) {
        try{
            int hh = 0;
            int mm;
            int ss;
            if(str.length == 3){
                hh = Integer.parseInt(str[0]);
                mm = Integer.parseInt(str[1]);
                ss = Integer.parseInt(str[2]);
            }else if(str.length == 2){
                mm = Integer.parseInt(str[0]);
                ss = Integer.parseInt(str[1]);
            }else throw new IllegalArgumentException("Stringa non valida");

            return 3600*hh + 60*mm + ss;
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Stringa non valida");
        }
    }
}