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



        //Test 1
        String username = "korosh"; //must be new user
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

                int check = response.compareTo("User created.");

                if(check == 0) {
                    System.out.println("Test 1 is successful.");
                } else {
                    System.out.println("Test 1 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }



        //Test 2
        username = "nayana"; //must be existing user
        password = "password";

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

                int check = response.compareTo("User already exists.");

                if(check == 0) {
                    System.out.println("Test 2 is successful.");
                } else {
                    System.out.println("Test 2 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }


        //Test 3
        username = "nayana"; //must be existing user
        password = "password"; //must be correct password

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/login?username=" + username
                            + "&password=" + password))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Successfully logged in.");

                if(check == 0) {
                    System.out.println("Test 3 is successful.");
                } else {
                    System.out.println("Test 3 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

    }
}
