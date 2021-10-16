package advisor.model;

public class AppParameters {
    private static String[] parametersLine = {"-access", "-resource", "-page"};
    final static String ACCESS = "https://accounts.spotify.com";
    final static String RESOURCE = "https://api.spotify.com";
    final static String PAGE = "5";
    private static String[] parametersValues = {ACCESS, RESOURCE, PAGE};

    public static String[] getParameters(String[] args) {
        if(args.length == 0) {
            return parametersValues;
        }




        return parametersValues;
    }

}
