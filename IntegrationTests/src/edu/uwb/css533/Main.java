package edu.uwb.css533;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        // write your code here
        HttpClient HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        String username = "nayana";
        String password = "password";


        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/new_user?username=" + username
                            + "&password=" + password))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

    }
}
