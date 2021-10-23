package advisor.entitie;

public class Release {
    private String name;
    private String artist;
    private String link;

    public Release(String name, String artist, String link) {
        this.name = name;
        this.artist = artist;
        this.link = link;
    }

    @Override
    public String toString () {
        return name + "\n" + artist + "\n" + link + "\n";
    }

}
