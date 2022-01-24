package Playlist;

import java.util.ArrayList;
import java.util.List;
import Playlist.Album.Brano;

public class Playlist implements Musica{

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
        playlist.add(new Album(b,album_title));
        durata.add(b.getDurata());
    }

    public void remove(Brano b){
        for (Album a : playlist) {
            if(a.contains(b)){
                playlist.remove(a);
                durata.sub(b.getDurata());
            }
        }
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
    
}
