package advisor.entitie;

public class Playlist {
    String name;
    String link;

    public Playlist(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public String toString() {
        return name + "\n" + link + "\n";
    }
}
