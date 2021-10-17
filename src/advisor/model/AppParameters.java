package advisor.model;


public class AppParameters {
    private static String[] parametersNames = {"-access", "-resource", "-page"};
    final static String ACCESS = "https://accounts.spotify.com";
    final static String RESOURCE = "https://api.spotify.com";
    final static String PAGE = "5";
    private static String[] parametersValues = {ACCESS, RESOURCE, PAGE};

    public static String[] getParameters(String[] args) {
        if(args.length == 0) {
            return parametersValues;
        }

        if(args.length >= 2 && args[0].equals(parametersNames[0])) {
            parametersValues[0] = args[1];
        }
        if(args.length >= 4 && args[2].equals(parametersNames[1])) {
            parametersValues[1] = args[3];
        }
        if(args.length >= 6 && args[4].equals(parametersNames[2])) {
            parametersValues[2] = args[5];
        }

        return parametersValues;
    }

}
