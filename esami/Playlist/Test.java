package Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        List<Album> lista_album = new ArrayList<>();
        List<Playlist> lista_playlist = new ArrayList<>();

        while(reader.hasNext()){
            String input = reader.nextLine();
            String[] data = input.split(" ");

            if(data.length>0){

                String title = null;

                switch (data[0]){
                    case "ALBUM":
                        title = input.substring(data[0].length());
                        List<String> lista_brani = new ArrayList<>();

                        while(reader.hasNext()){
                            input = reader.nextLine();
                            if(input.equals(".")) break;
                            lista_brani.add(input);
                        }

                        Album temp_album = new Album(lista_brani,title);                        
                        lista_album.add(temp_album);
                        temp_album.toString();

                        break;

                    case "PLAYLIST":
                        title = input.substring(data[0].length());
                        Playlist playlist = new Playlist(title);

                        while(reader.hasNext()){
                            input = reader.nextLine();
                            if(input.equals(".")) break;
                            String[] data_brani = input.split(" ");

                            int pos_album = Integer.parseInt(data_brani[0]);
                            int pos_brano = Integer.parseInt(data_brani[1]);
                            Album temp_album_1 = lista_album.get(pos_album);

                            playlist.add(temp_album_1.getBrano(pos_brano),temp_album_1.getTitolo());
                        }
                        lista_playlist.add(playlist);
                        playlist.toString();
                        break;
                    default:
                        break;
                }
            }
            break;
            
        }
    }
}
