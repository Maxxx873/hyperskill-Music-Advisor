package advisor.app;

import advisor.entitie.Category;
import advisor.json.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Api {
    private String resource;
    private Authentication authentication;

    public Api(String resource, Authentication authentication) {
        this.resource = resource;
        this.authentication = authentication;
    }

    public void getAllCategories(List<Category> categories) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + authentication.getAccessToken())
                .uri(URI.create(resource + "/v1/browse/categories"))
                .GET()
                .build();
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONParser.parseCategories(response.body(), categories);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void getFeaturedPlaylists() {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + authentication.getAccessToken())
                .uri(URI.create(resource + "/v1/browse/featured-playlists"))
                .GET()
                .build();
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONParser.parseFeatures(response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void getNewReleases() {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + authentication.getAccessToken())
                .uri(URI.create(resource + "/v1/browse/new-releases"))
                .GET()
                .build();
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONParser.parseNewReleases(response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


    public void getPlaylists(String categoryId) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + authentication.getAccessToken())
                .uri(URI.create(resource + "/v1/browse/categories/" + categoryId + "/playlists"))
                .GET()
                .build();
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONParser.parsePlayLists(response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
