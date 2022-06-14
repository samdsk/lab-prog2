import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Album implements Iterable<Album.Brano>{
    //Overview: Album è una classe immutabile, rappresentato da un elenco ordinato, 
    //          non vuoto e senza ripetizioni di brani con un titolo
    //          e una durata complessiva

    public class Brano{
        //Overview: Brano è una classe immutabile, rappresentato da un titolo,
        //          una durata e l'album a cui appartiene

        /**
         * AF(titolo,durata) : titolo + " : " + durata
         * 
         * RI = titolo != null && durata > 0 && album != null
         */
        private final String titolo;
        private final Durata durata;
        private final Album album;

        /**
         * Inizializza Brano con il titolo "t" e la durata "d" 
         * @param t il titolo del brano
         * @param d la durata del brano
         * @throws NullPointExpection se titolo "t" oppure durata "d" è null
         */
        public Brano(String t, Durata d){
            Objects.requireNonNull(t,"Il titolo non può essere null!");
            Objects.requireNonNull(d,"La durata non può essere null!");

            titolo = t;
            durata = new Durata(d.getDurata());
            album = Album.this;

            assert repOk();
        }

        /**
         * Effects: Restituisce il titolo del brano
         */
        public String getTitolo(){
            return this.titolo;
        }

        /**
         * Effects: Restituisce la durata del brano
         */
        public Durata getDurata(){
            return new Durata(durata.getDurata());
        }

        /**
         * Effects: restituisce il titolo del album a cui appartiene il brano
         */
        public String getAlbum(){
            return album.titolo;
        }

        //RI
        public boolean repOk(){
            return titolo != null && durata.getDurata() > 0 && album != null;
        }

        //AF
        @Override
        public String toString(){
            return titolo +" : ("+ durata +")";
        }

    }


    private SortedSet<Brano> brani;
    private Durata durata;
    private String titolo;


    public Album(String titolo, String[] titoli_brani, String[] durate_brani){
        Objects.requireNonNull(titolo,"Titolo dell'album non può essere null");
        Objects.requireNonNull(titoli_brani,"L'array di titoli dei brani non può essere null");
        Objects.requireNonNull(durate_brani,"L'array di durate non può essere null");

        if(titoli_brani.length != durate_brani.length) throw new IllegalArgumentException("array di titoli e durate devono la stessa lunghezza");
        
        brani = new TreeSet<>();
        for(int i = 0; i<titoli_brani.length;i++){
            String[] tempi = durate_brani[i].split(":");

            brani.add(new Brano(titoli_brani[i], new Durata(tempi)));
        }
    }


    public Brano getBrano(String titolo) {

    }

    public Brano getBrano(String posizione){

    }

    public int posizione(Brano b){

    }

    @Override
    public Iterator<Brano> iterator() {
        SortedSet<Brano> l = Collections.unmodifiableSortedSet(brani);
        return l.iterator();
    }

    @Override
    public Iterator<Album.Brano> iterator(){
        return new Iterator<Album.Brano>(){
            Iterator<Album.Brano> it = Collections.unmodifiableSortedSet(brani).iterator();

            Brano next = null;

            @Override
            public boolean hasNext() {
                if(next != null) return true;

                while(it.hasNext()){
                    next = it.next();
                    return true;
                }
                next = null;
                return false;

            }

            @Override
            public Album.Brano next() {
                if(!hasNext()) throw new NoSuchElementException();
                
            }

        };
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Album)) return false;

        Album a = (Album) o;

        return a.titolo.equals(a.titolo) 
            && durata.equals(a.durata)
            && brani.equals(a.brani);

    }
}