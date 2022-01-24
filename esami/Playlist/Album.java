package Playlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Album implements Musica, Iterable<Musica>{

    private final String titolo;
    private final Durata durata;
    private final List<Brano> lista_brani;

    public Album(){
        titolo = "";
        durata = new Durata();
        lista_brani = new ArrayList<>();

        assert repOk();
    }

    public Album(List<String> l,String t){
        if(l == null || t == null) throw new NullPointerException("The list of songs or album title is null");

        titolo = t;
        lista_brani = new ArrayList<>();
        durata = new Durata();
        for (String s : l) {
            String[] data = s.split(" - ");
            if(data.length==2){
                Durata temp_durata = new Durata(data[0]);
                lista_brani.add(new Brano(data[1],temp_durata));
                durata.add(temp_durata);
            }
            else throw new IllegalArgumentException("Durata - Titolo format doesn't match.");
        }

        assert repOk();
    }

    public Album(Brano b,String t){
        if(b == null || t == null) throw new NullPointerException("The brano or album title is null");
        titolo = t;
        durata = new Durata(b.getDurata());
        lista_brani = new ArrayList<>();
        lista_brani.add(b);
    }

    public class Brano implements Musica{

        private final String titolo;
        private final Durata durata;

        public Brano(String t,Durata d){
            titolo = t;
            durata = d;
        }

        public boolean repOk(){
            return titolo != null && durata != null;
        }

        @Override
        public Durata getDurata() {
            return durata;
        }

        @Override
        public String getTitolo() {
            return titolo;
        }

        @Override
        public int hashCode() {
            return Objects.hash(titolo,durata);
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Brano)) return false;
            Brano b = (Brano) o;

            return titolo.equals(b.getTitolo()) && durata.equals(b.getDurata());
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            output.append("\"");
            output.append(titolo);
            output.append("\"");
            output.append(durata);
            return output.toString();
        }
        
    }

    public Brano getBrano(String t){
        if(t == null) throw new NullPointerException("Title can't be null");

        for (Brano b : lista_brani) {
            if(b.getTitolo().equals(t)) return b;
        }
        throw new NoSuchElementException("Couldn't find the specified song.");
    }

    public Brano getBrano(int pos){        
        if((pos>=lista_brani.size()) && (pos<0))
            throw new IllegalArgumentException("Position out of bounds.");
        return lista_brani.get(pos);
    }

    public int getPosition(String t){
        int count = 0;
        for (Brano b : lista_brani) {
            if(b.getTitolo().equals(t))
                return count;
            count++;
        }
        throw new NoSuchElementException("Couldn't find the specified song.");
    }

    public boolean contains(Brano b){
        if(b == null) throw new NullPointerException("The brano is null");
        return lista_brani.contains(b);
    }

    @Override
    public Durata getDurata() {
        return durata;
    }

    @Override
    public String getTitolo() {
        return titolo;
    }

    public boolean repOk(){
        if(
            titolo != null &&
            durata != null &&
            lista_brani != null
        ) return true;

        return false;
    }

    public int getSize(){
        return lista_brani.size();
    }

    @Override
    public String toString() {
        StringBuilder o = new StringBuilder();
        o.append("Titolo album: "+titolo+"\n");

        int count=0;
        for (Brano b : lista_brani) {
            o.append((++count)+" - " + b.toString() + "\n");
        }
        o.append("Durata totale: "+ durata.toString( )+"\n");
        return o.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(!( o instanceof Album)) return false;

        Album a = (Album) o;

        if( titolo.equals(a.getTitolo()) &&
            durata.equals(a.getDurata()) &&
            lista_brani.size() == a.getSize()
        ){
            for(int i= 0;i<lista_brani.size();i++){
                if (!getBrano(i).equals(a.getBrano(i))) return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titolo,durata,lista_brani);
    }

    @Override
    public Iterator<Musica> iterator() {
        
        return new Iterator<Musica>() {
            Iterator<Brano> it = lista_brani.iterator();
            @Override
            public boolean hasNext() {                
                return it.hasNext();
            }

            @Override
            public Brano next() {
                return it.next();
            }
            
        };
    }
}
