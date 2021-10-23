package advisor.entitie;

public class Features {
    private String name;
    private String link;

    public Features(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public String toString() {
        return name + "\n" + link + "\n";
    }
}
