package advisor;

import advisor.app.MusicAdvisor;
import advisor.model.AppParameters;

public class Main {
    public static void main(String[] args) {
       /* final int DEFAULT_DISPLAY_ENTRIES_PER_PAGE = 5;
        final Integer PARAMETERS_COUNT = 6;
        if(args.length > 5&& args[0].equals("-access") && args[2].equals("-resource") && args[4].equals("-page")){
                MusicAdvisor.startMusicAdvisor(AppParameters.getParameters(args));
            } else {*/
            MusicAdvisor.startMusicAdvisor(AppParameters.getParameters(args));
       // }
    }
}
