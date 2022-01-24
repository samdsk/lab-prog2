package Playlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Playlist.Album.Brano;

public class Playlist implements Musica, Iterable<Album>{

    private final String titolo;
    private final Durata durata;
    private final List<Album> playlist;

    public Playlist(String t){
        if(t == null) throw new NullPointerException("Playlist name can't be null");

        titolo = t;
        durata = new Durata();
        playlist = new ArrayList<>();

        assert repOk();
    }

    public void add(Brano b, String album_title){
        if(b == null || album_title == null) throw new NullPointerException("Brano and Album title can't be null");
        Album a = new Album(b,album_title);
        if(!playlist.contains(a)){
            playlist.add(a);
            durata.add(b.getDurata());
            assert repOk();           
        }
    }

    public void add(Album a){

    }

    public void remove(Brano b){
        if(b == null) throw new NullPointerException("Brano can't be null");
        for (Album a : playlist) {
            if(a.contains(b)){
                playlist.remove(a);
                durata.sub(b.getDurata());
                assert repOk();                
            }
        }

    }

    public int getSize(){
        return playlist.size();
    }


    public int position(Brano b){
        int count = 0;
        for (Album a : playlist) {
            if(a.contains(b)){
                return count;
            }
            count++;
        }
        return -1;
    }

    public boolean contains(Brano b){
        for (Album a : playlist) {
            if(a.contains(b)){
                return true;
            } 
        }
        return false;
    }

    public void fuse(Playlist p){
        if(p == null) throw new NullPointerException("Playlist can't be null");

        for (Album a : p) {
            if(!playlist.contains(a))
                playlist.add(a);
        }

        assert repOk();

    }

    public boolean repOk(){
        if(titolo != null && durata != null && playlist != null) return true;
        return false;
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
    public Iterator<Album> iterator() {
        return new Iterator<Album>() {
            Iterator<Album> it = playlist.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Album next() {
                return it.next();
            }
        };
    }
    
}
