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
        Scanner scanner = new Scanner(System.in);
        Viewer viewer = new ViewerImpl<>(scanner);
        boolean provide = false;
        while(!viewer.getState().equals(StateViewer.EXIT)) {
            switch (viewer.getState()) {
                case INPUT_COMMAND:
                    viewer.handle();
                    break;
                case AUTH:
                    Authentication.setAccessCode();
                    Authentication.setAccessToken();
                    provide = true;
                    System.out.println("Success!");
                    viewer.setState(StateViewer.INPUT_COMMAND);
                    viewer.handle();
                    break;
                case FEATURED:
                    List<Features> features = new ArrayList<>();
                    if (provide) {
                        api.getFeaturedPlaylists(features);
                        viewer.viewerSet(features, entriesPerPage, scanner);
                        viewer.handle();
                    } else {
                        System.out.println("Please, provide access for application.");
                        viewer.setState(StateViewer.INPUT_COMMAND);
                    }
                    break;
                case NEW:
                    List<Release> releases = new ArrayList<>();
                    if (provide) {
                        api.getNewReleases(releases);
                        viewer.viewerSet(releases, entriesPerPage, scanner);
                        viewer.handle();
                    } else {
                        System.out.println("Please, provide access for application.");
                        viewer.setState(StateViewer.INPUT_COMMAND);
                    }
                    break;
                case CATEGORIES:
                    if (provide) {
                        List<Category> categories = new ArrayList<>();
                        api.getAllCategories(categories);
                        viewer.viewerSet(categories, entriesPerPage, scanner);
                        viewer.handle();
                    } else {
                        System.out.println("Please, provide access for application.");
                        viewer.setState(StateViewer.INPUT_COMMAND);
                    }
                    break;
                case PLAYLISTS:
                        if (provide) {
                            List<Category> categories = new ArrayList<>();
                            api.getAllCategories(categories);
                            if (categories.stream()
                                    .map(Category::getName)
                                    .noneMatch(viewer.getCategoryName()::equals)) {
                                System.out.println("Unknown category name.");
                            } else {
                                List<Playlist> playlists = new ArrayList<>();
                                String categoryId = categories
                                        .stream()
                                        .filter(category -> category.getName().equals(viewer.getCategoryName()))
                                        .findFirst()
                                        .get()
                                        .getId();
                                api.getPlaylists(categoryId, playlists);
                                viewer.viewerSet(playlists, entriesPerPage, scanner);
                                viewer.handle();
                            }


                        } else {
                            System.out.println("Please, provide access for application.");
                            viewer.setState(StateViewer.INPUT_COMMAND);
                        }

                    break;
                case EXIT:
                    scanner.close();
                    return;
            }
        }
    }
}
