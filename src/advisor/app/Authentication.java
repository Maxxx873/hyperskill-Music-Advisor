package advisor.app;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Authentication {
    public final static String REDIRECT_URI = "http://localhost:8088/";
    public final static String CLIENT_ID = "9706330c886b4d65917f082f0b72baea";
    public final static String CLIENT_SECRET = "1ff530d901db4296b8fd191117b97145";
    private static String serverPath;
    private static String accessToken;
    private static String accessCode;

    public Authentication(String serverPath) {
        this.serverPath = serverPath;
        accessCode = "";
        accessToken = "";
    }

    public String getServerPath() {
        return serverPath;
    }

    public static void setAccessCode() {
        String uri =
                Authentication.serverPath + "/authorize?client_id=" +
                        CLIENT_ID + "&response_type=code&redirect_uri=" +
                        REDIRECT_URI;
        System.out.println("use this link to request the access code:");
        System.out.println(uri);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8088), 0);
            server.start();
            server.createContext("/",
                    exchange -> {
                        String query = exchange.getRequestURI().getQuery();
                        String request;
                        if (query != null && query.contains("code")) {
                            accessCode = query.substring(5);
                            System.out.println("code received");
                            System.out.println(accessCode);
                            request = "Got the code. Return back to your program.";
                        } else {
                            request = "Authorization code not found. Try again.";
                        }
                        exchange.sendResponseHeaders(200, request.length());
                        exchange.getResponseBody().write(request.getBytes());
                        exchange.getResponseBody().close();
                    });
            System.out.println("waiting for code...");
            Thread.sleep(1500L);
            System.out.println("code received");
            //server.stop(5);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getAccessCode() {
        return accessCode;
    }

    public static void setAccessToken() {
        System.out.println("Making http request for access_token...");
        System.out.println("response:");

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(serverPath + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code"
                                + "&code=" + accessCode
                                + "&client_id=" + CLIENT_ID
                                + "&client_secret=" + CLIENT_SECRET
                                + "&redirect_uri=" + REDIRECT_URI))
                .build();

        try {

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            accessToken = response.body()
                    .substring(response.body().indexOf(":") + 2, response.body().indexOf(",") - 1);
            System.out.println(response.body());
            System.out.println(accessToken);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

}