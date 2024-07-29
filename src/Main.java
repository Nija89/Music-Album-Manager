import java.util.ArrayList;
import java.util.ListIterator;

public class Main{
    public static void main(String[] args) {
        Album myNewAlbum = new Album();
        myNewAlbum.addSong(new Song("A", 4.12));
        myNewAlbum.addSong(new Song("B", 3.12));
        myNewAlbum.addSong(new Song("C", 2.59));
        myNewAlbum.addSong(new Song("D", 5.01));
        myNewAlbum.addSong(new Song("A", 4.12));

        myNewAlbum.controller();
    }
}