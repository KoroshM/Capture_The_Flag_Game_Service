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



        //Test 1 - normal path
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



        //Test 2 - error condition
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


        //Test 3 - normal path
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

        //Test 4 - error condition
        username = "nayana"; //must be existing user
        password = "123"; //must be incorrect password

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

                int check = response.compareTo("Incorrect Password.");

                if(check == 0) {
                    System.out.println("Test 4 is successful.");
                } else {
                    System.out.println("Test 4 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 5 - error condition
        username = "palmer"; //must be user that does not currently exist
        password = "password";

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

                int check = response.compareTo("User does not exist.");

                if(check == 0) {
                    System.out.println("Test 5 is successful.");
                } else {
                    System.out.println("Test 5 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 6 - normal path
        int userId = 0; //user is logged in and is not part of another active session

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/new_room?user_id=" + userId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Player joined new session.");

                if(check == 0) {
                    System.out.println("Test 6 is successful.");
                } else {
                    System.out.println("Test 6 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 7 - error condition
        userId = 1; //user is logged in and is part of another active session

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/new_room?user_id=" + userId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Player cannot join new session.");

                if(check == 0) {
                    System.out.println("Test 7 is successful.");
                } else {
                    System.out.println("Test 7 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 8 - normal path
        userId = 1; //user is logged in, session exists, and session is not full
        int sessionId = 123;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/join_room?user_id=" + userId
                            + "&session_id=" + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Player joined " + sessionId + ".");

                if(check == 0) {
                    System.out.println("Test 8 is successful.");
                } else {
                    System.out.println("Test 8 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 9 - error condition
        userId = 1; //user is logged in, session exists, and session is not full
        int sessionId = 123;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/join_room?user_id=" + userId
                            + "&session_id=" + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Player joined " + sessionId + ".");

                if(check == 0) {
                    System.out.println("Test 8 is successful.");
                } else {
                    System.out.println("Test 8 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

    }
}
