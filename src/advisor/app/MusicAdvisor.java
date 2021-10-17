package advisor.app;

import advisor.entitie.Category;
import advisor.entitie.Release;
import advisor.view.Viewer;
import advisor.view.ViewerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicAdvisor {
    public static void startMusicAdvisor(String[] parameters) {
        String access = parameters[0];
        String resource = parameters[1];
        String entriesPerPage = parameters[2];
        Authentication authentication = new Authentication(access);
        Api api = new Api(resource, authentication);

        Scanner scanner = new Scanner(System.in);
        boolean provide = false;
        for (;;) {
            String command = scanner.nextLine();
            switch (command) {
                case "auth":
                    Authentication.setAccessCode();
                    Authentication.setAccessToken();
                    provide = true;
                    System.out.println("Success!");
                    break;
                case "featured":
                    if (provide) {
                        api.getFeaturedPlaylists();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "new":
                    List<Release> releases = new ArrayList<>();
                    if (provide) {
                        api.getNewReleases(releases);
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "categories":
                    if (provide) {
                        List<Category> categories = new ArrayList<>();
                        api.getAllCategories(categories);
                        Viewer viewer = new ViewerImpl(categories, entriesPerPage);
                        viewer.printPage(1);
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                default:
                    String keyWord = "playlists ";
                    if(command.indexOf(keyWord) == 0) {
                        if (provide) {
                            String categoryName = command.substring(keyWord.length());
                            List<Category> categories = new ArrayList<>();
                            api.getAllCategories(categories);
                            if (categories.stream()
                                    .map(category -> category.getName())
                                    .noneMatch(categoryName::equals)) {
                                System.out.println("Unknown category name.");
                            } else {
                                String categoryId = categories
                                        .stream()
                                        .filter(category -> category.getName().equals(categoryName))
                                        .findFirst()
                                        .get()
                                        .getId();
                                api.getPlaylists(categoryId);
                            }

                        } else {
                            System.out.println("Please, provide access for application.");
                        }
                    }

                    break;
                case "exit":
                    return;
            }
        }
    }
}
