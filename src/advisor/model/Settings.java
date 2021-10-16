package advisor.model;

public interface Settings {
    String access = "https://accounts.spotify.com";
    String resource = "https://api.spotify.com";
    Integer page = 5;

    Settings handle();

    SettingsType getType();

}
