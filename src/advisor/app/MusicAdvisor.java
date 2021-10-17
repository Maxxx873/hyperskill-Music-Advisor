package advisor.app;

import advisor.entitie.Category;
import advisor.entitie.Features;
import advisor.entitie.Playlist;
import advisor.entitie.Release;
import advisor.view.StateViewer;
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
        Viewer viewer = new ViewerImpl<>();

        String command = new String();
        Scanner scanner = new Scanner(System.in);
        boolean provide = false;
        while(!viewer.getState().equals(StateViewer.EXIT)) {
            command = scanner.nextLine();
            switch (command) {
                case "auth":
                    Authentication.setAccessCode();
                    Authentication.setAccessToken();
                    provide = true;
                    System.out.println("Success!");
                    break;
                case "featured":
                    List<Features> features = new ArrayList<>();
                    if (provide) {
                        api.getFeaturedPlaylists(features);
                        viewer.viewerSet(features, entriesPerPage, scanner);
                        while (viewer.getState().equals(StateViewer.FIRST) ||
                                viewer.getState().equals(StateViewer.INTERMEDIATE) ||
                                viewer.getState().equals(StateViewer.LAST)) {
                            viewer.handle();
                        }
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "new":
                    List<Release> releases = new ArrayList<>();
                    if (provide) {
                        api.getNewReleases(releases);
                        viewer.viewerSet(releases, entriesPerPage, scanner);
                        while (viewer.getState().equals(StateViewer.FIRST) ||
                                viewer.getState().equals(StateViewer.INTERMEDIATE) ||
                                viewer.getState().equals(StateViewer.LAST)) {
                            viewer.handle();
                        }
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "categories":
                    if (provide) {
                        List<Category> categories = new ArrayList<>();
                        api.getAllCategories(categories);
                        viewer.viewerSet(categories, entriesPerPage, scanner);
                        while (viewer.getState().equals(StateViewer.FIRST) ||
                                viewer.getState().equals(StateViewer.INTERMEDIATE) ||
                                viewer.getState().equals(StateViewer.LAST)) {
                            viewer.handle();
                        }

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
                                List<Playlist> playlists = new ArrayList<>();
                                String categoryId = categories
                                        .stream()
                                        .filter(category -> category.getName().equals(categoryName))
                                        .findFirst()
                                        .get()
                                        .getId();
                                api.getPlaylists(categoryId, playlists);
                                viewer.viewerSet(playlists, entriesPerPage, scanner);
                                while (viewer.getState().equals(StateViewer.FIRST) ||
                                        viewer.getState().equals(StateViewer.INTERMEDIATE) ||
                                        viewer.getState().equals(StateViewer.LAST)) {
                                    viewer.handle();
                                }
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
