package advisor;

import java.util.Scanner;

public class MusicAdvisor {
    public static void startMusicAdvisor(String access) {

        Scanner scanner = new Scanner(System.in);
        boolean provide = false;
        for (;;) {
            switch (scanner.nextLine()) {
                case "auth":
                    Authentication.getAccessCode(access);
                    Authentication.getAccessToken();
                    provide = true;
                    System.out.println("---SUCCESS---");
                    break;
                case "featured":
                    if (provide) {
                        System.out.println("---FEATURED---");
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "new":
                    if (provide) {
                        System.out.println("---NEW RELEASES---");
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "categories":
                    if (provide) {
                        System.out.println("---CATEGORIES---");
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "playlists Mood":
                    if (provide) {
                        System.out.println("---MOOD PLAYLISTS---");
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "exit":
                    System.out.printf("---GOODBYE!---");
                    return;
            }
        }
    }
}
