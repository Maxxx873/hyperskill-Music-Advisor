package advisor;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Authentication {
    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String REDIRECT_URI = "http://localhost:8088/";
    public static String CLIENT_ID = "9706330c886b4d65917f082f0b72baea";
    public static String CLIENT_SECRET = "6b2c63a8fc574e8c9ed07f82450cf43e";
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_CODE = "";

    public static void getAccessCode(String serverPath) {
        SERVER_PATH = serverPath;
        String uri =
                SERVER_PATH + "/authorize?client_id=" +
                CLIENT_ID + "&response_type=code&redirect_uri=" +
                REDIRECT_URI;
        System.out.println("use this link to request the access code:");
        System.out.println(uri);
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8088), 0);
            server.start();
            server.createContext("/",
                    exchange -> {
                        String query = exchange.getRequestURI().getQuery();
                        String request;
                        if (query != null && query.contains("code")) {
                            ACCESS_CODE = query.substring(5);
                            System.out.println("code received");
                            System.out.println(ACCESS_CODE);
                            request = "Got the code. Return back to your program.";
                        } else {
                            request = "Authorization code not found. Try again.";
                        }
                        exchange.sendResponseHeaders(200, request.length());
                        exchange.getResponseBody().write(request.getBytes());
                        exchange.getResponseBody().close();
                    });
            System.out.println("waiting for code...");
            Thread.sleep(1000L);
            System.out.println("code received");
            server.stop(1);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void getAccessToken() {
        System.out.println("making http request for access_token...");
        System.out.println("response:");

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(SERVER_PATH + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code"
                                + "&code=" + ACCESS_CODE
                                + "&client_id=" + CLIENT_ID
                                + "&client_secret=" + CLIENT_SECRET
                                + "&redirect_uri=" + REDIRECT_URI))
                .build();

        try {

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
