package part2;

import java.util.ArrayList;
import java.util.List;

class Song {
    private final String title;
    private final String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}

interface Iterator {
    boolean hasNext();
    Song next();
}

interface Playlist {
    Iterator createIterator();
    void addSong(Song song);
}
class PlaylistImpl implements Playlist {
    private final List<Song> songs;

    public PlaylistImpl() {
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Iterator createIterator() {
        return new SongIterator(songs);
    }
}
class SongIterator implements Iterator {
    private final List<Song> songs;
    private int position;

    public SongIterator(List<Song> songs) {
        this.songs = songs;
        position = 0;
    }

    public boolean hasNext() {
        return position < songs.size();
    }

    public Song next() {
        return hasNext() ? songs.get(position++) : null;
    }
}

public class Main {
    public static void main(String[] args) {
        PlaylistImpl myPlaylist = new PlaylistImpl();
        myPlaylist.addSong(new Song("Imagine", "John Lennon"));
        myPlaylist.addSong(new Song("Bohemian Rhapsody", "Queen"));

        Iterator iterator = myPlaylist.createIterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());
        }
    }
}