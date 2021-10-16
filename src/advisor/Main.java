package advisor;

import advisor.app.MusicAdvisor;

public class Main {
    public static void main(String[] args) {
        final int DEFAULT_DISPLAY_ENTRIES_PER_PAGE = 5;
        final int PARAMETERS_COUNT = 6;
        if(args.length >= PARAMETERS_COUNT) {
            if(args[0].equals("-access") && args[2].equals("-resource") && args[4].equals("-page")){
                MusicAdvisor.startMusicAdvisor(args[1], args[3], Integer.getInteger(args[5]));
            }
        } else {
            MusicAdvisor.startMusicAdvisor("https://accounts.spotify.com",
                    "https://api.spotify.com",DEFAULT_DISPLAY_ENTRIES_PER_PAGE);
        }
    }
}
