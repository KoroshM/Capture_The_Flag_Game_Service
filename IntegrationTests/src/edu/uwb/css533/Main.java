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
        userId = 1; //user is logged in, session exists, and session is full
        sessionId = 123;

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

                int check = response.compareTo("Player cannot join " + sessionId + ". The session is full.");

                if(check == 0) {
                    System.out.println("Test 9 is successful.");
                } else {
                    System.out.println("Test 9 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 10 - normal path
        sessionId = 123; //at least two players are in the session

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/start_game?session_id=" + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Game has begun. Here is your flag and target codes.");

                if(check == 0) {
                    System.out.println("Test 10 is successful.");
                } else {
                    System.out.println("Test 10 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 11 - error condition
        sessionId = 123; //session does not have at least two players

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/start_game?session_id=" + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Game could not begin. Please add more players");

                if(check == 0) {
                    System.out.println("Test 11 is successful.");
                } else {
                    System.out.println("Test 11 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 12 - normal path
        int QRCodeId = 12345; //code matches flag being played

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/find_feature?code_id=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Feature successfully found.");

                if(check == 0) {
                    System.out.println("Test 12 is successful.");
                } else {
                    System.out.println("Test 12 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        //Test 13 - error condition
        QRCodeId = 12345; //code does not match flag being played

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/find_feature?code_id=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                int check = response.compareTo("Incorrect feature.");

                if(check == 0) {
                    System.out.println("Test 13 is successful.");
                } else {
                    System.out.println("Test 13 is not successful.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

    }
}
