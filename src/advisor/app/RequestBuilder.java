package advisor.app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestBuilder {

    public static HttpResponse<String> requestBuild(String resource, Authentication authentication, String parameter) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + authentication.getAccessToken())
                .uri(URI.create(resource + parameter))
                .GET()
                .build();
        try {
            HttpClient client = HttpClient.newBuilder().build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
