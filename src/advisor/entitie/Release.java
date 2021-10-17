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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void print () {
        System.out.println(name + "\n" + artist + "\n" + link + "\n");
    }

}
