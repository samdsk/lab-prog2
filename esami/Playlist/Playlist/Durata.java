package Playlist;


import java.util.Objects;

public class Durata {
    private int hh;
    private int mm;
    private int ss;

    public Durata(){
        hh = 0;
        mm = 0;
        ss = 0;
    }

    public Durata(String s){
        if(s == null) throw new NullPointerException("Input string can't be null");

        String[] data = s.split(":");
        int len = data.length;
        if(len>3 || len<2) throw new IllegalArgumentException("Wrong format durata must be hh:mm:ss or mm:ss");
        
        mm = Integer.parseInt(data[len-2]);
        ss = Integer.parseInt(data[len-1]);

        if(len==3) hh = Integer.parseInt(data[0]);
        else hh = 0;

        assert repOk();
    }

    public Durata(Durata d){
        if(d == null) throw new NullPointerException("Input durata can't be null");
        hh = d.getHours();
        mm = d.getMin();
        ss = d.getSec();

        assert repOk();
    }

    public boolean repOk(){
        return hh>=0 && mm>= 0 && ss >= 0;
    }

    public int getSec(){
        return ss;
    }
    public int getMin(){
        return mm;
    }
    public int getHours(){
        return hh;
    }

    public void add(Durata d){
        if(d == null) throw new NullPointerException("Input Durata can't be null");

        int temp = ss + d.getSec();
        ss = temp % 60;

        temp = mm + d.getMin() + temp / 60;
        mm = temp % 60;

        hh = hh + temp / 60;

        assert repOk();
    }


    public void sub(Durata d){
        //da finire
        if(d == null) throw new NullPointerException("Input Durata can't be null");

        hh = hh - d.getHours();
        mm = mm - d.getMin();
        ss = ss - d.getSec();

        assert repOk();
    }

    @Override
    public String toString() {
        String o = "(";
        if(hh !=  0){
            o += hh+":";
        }

        o += mm+":"+ss+")";

        return o;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Durata)) return false;

        Durata d = (Durata) o;

        if(
            hh == d.getHours() &&
            mm == d.getMin() &&
            ss == d.getSec() 
        ) return true;

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hh,mm,ss);
    }

}
