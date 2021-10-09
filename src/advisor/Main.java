package advisor;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")) {
            MusicAdvisor.startMusicAdvisor(args[1]);
        } else {
            MusicAdvisor.startMusicAdvisor("https://accounts.spotify.com");
        }
    }
}
