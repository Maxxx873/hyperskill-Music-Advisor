package advisor;

import advisor.app.MusicAdvisor;
import advisor.app.AppParameters;

public class Main {
    public static void main(String[] args) {
        MusicAdvisor.startMusicAdvisor(AppParameters.getParameters(args));
    }
}
