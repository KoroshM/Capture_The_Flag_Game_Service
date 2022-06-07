package edu.uwb.css533.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

//import edu.uwb.css533.service.core.GameListenerThread;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * GameService is the point of convergence for all data moving through the system
 * Each service interacts with the Game Service to provide functionality to the public-facing
 *   client interface
 * These tests will only pass if all services and interfaces are up and functioning correctly
 *
 * Integration tests for Capture The Flag game
 * Start database and services
 * To compile, javac Main.java
 * To run, java Main
 */

public class TestSuite {

    private static HttpClient HTTP_CLIENT;
    static int PORT;

    // Runner method for this suite
    public static void main(String[] args) {

        HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        PORT = 8080;

        int total = 0;
        int pass = 0;

        if (setup() == true) {
            total++;
            if(test1()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test2()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test3()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test4()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test5()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test6()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test7()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test8()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test9()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test10()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test11()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test12()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test13()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test14()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test15()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test16()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }

            total++;
            if(test17()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }
            total++;
            if(test18()) {
                System.out.println("Test " + total + " passed.");
                pass += 1;
            }



            System.out.println("\n==========================================================================\n");
            System.out.println("Tests: " + total);
            System.out.println("Passed: " + pass);
            System.out.println("Failed: " + (total - pass));

        }else {
            System.out.println("Setup failed.");
        }
    }

    /*
    Positive Test: Creating new user using login function
     */
    public static boolean test1() {
        String username = "korosh"; //must be new user
        String password = "password";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/login?" +
                            "username=" + username
                            + "&password=" + password))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return Integer.parseInt(response) >= 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Positive Test: Logging in existing user with correct password
     */
    public static boolean test2() {
        String username = "nayana"; //must be existing user
        String password = "password"; //must be correct password
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/login?" +
                            "username=" + username
                            + "&password=" + password))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return Integer.parseInt(response) >= 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Negative Test: Trying to log in existing user with incorrect password
     */
    public static boolean test3() {
        //LOGGER.info("Testing incorrect login.");
        String username = "nayana"; //must be existing user
        String password = "123"; //must be incorrect password

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/login?username=" + username
                            + "&password=" + password))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return Integer.parseInt(response) < 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    /*
    Postive Test: Creating new room for user with existing user ID
     */
    public static boolean test4() {
        int userId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/new_room?user_id=" + userId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return Integer.parseInt(response) >= 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Negative Test: Creating new room with a user ID that does not exist
     */
    public static boolean test5() {
        //LOGGER.info("Testing create room when user ID does not exist.");
        int userId = 4;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/new_room?user_id=" + userId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return Integer.parseInt(response) < 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Postive Test: Joining an existing session
     */
    public static boolean test6() {
        //LOGGER.info("Testing join existing room with availability.");
        int userId = 1;
        int sessionId = 0; //session exists
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/join_room?user_id=" + userId
                            + "&session_id=" + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return !response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Negative Test: Trying to join session that does not exist
     */
    public static boolean test7() {
        //LOGGER.info("Testing join session with invalid sessionId.");
        int userId = 1;
        int sessionId = 124; //session does not exist
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/join_room?user_id=" + userId
                            + "&session_id=" + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Positive Test: Starting game with existing session
     */
    public static boolean test8() {
        //LOGGER.info("Testing start game with valid room.");
        int sessionId = 0;
        int userId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/start_game?session_id=" + sessionId + "&user_id=" + userId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return !response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Negative Test: Starting game with nonexistent session
    */
    public static boolean test9() {
        //LOGGER.info("Testing start invalid session.");
        int sessionId = 123;
        int userId = 1;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/start_game?session_id=" + sessionId + "&user_id=" + userId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Positive Test: Code matches flag being played, correct QR code collected
    */
    public static boolean test10() {
        int QRCodeId = 1; //code matches flag being played
        int sessionId = 0;
        int userId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/check_feature?session_id="
                            + sessionId + "&user_id=" + userId + "&feature=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.equals("1") || response.equals("2") || response.equals("3");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Positive Test: Code matches flag being played, correct QR code collected
    */
    public static boolean test11() {
        //LOGGER.info("Testing correct QR code.");
        int QRCodeId = 2; //code matches flag being played
        int sessionId = 0;
        int userId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/check_feature?session_id="
                            + sessionId + "&user_id=" + userId + "&feature=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.equals("1") || response.equals("2") || response.equals("3");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Negative Test: Code does not match flag being played, incorrect QR code collected
    */
    public static boolean test12() {
        int QRCodeId = 5; //code does not match flag being played
        int sessionId = 0;
        int userId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/check_feature?session_id="
                            + sessionId + "&user_id=" + userId + "&feature=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



    /*
    Negative Test: Ending game before all QR codes are collected
    */
    public static boolean test13() {
        int sessionId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/end_game?session_id="
                            + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Positive Test: Code matches flag being played, correct QR code collected
    */
    public static boolean test14() {
        int QRCodeId = 0; //code matches flag being played
        int sessionId = 0;
        int userId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/check_feature?session_id="
                            + sessionId + "&user_id=" + userId + "&feature=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.equals("1") || response.equals("2") || response.equals("3");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Positive Test: Code matches flag being played, correct QR code collected
    */
    public static boolean test15() {
        int QRCodeId = 0; //code matches flag being played
        int sessionId = 0;
        int userId = 1;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/check_feature?session_id="
                            + sessionId + "&user_id=" + userId + "&feature=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.equals("1") || response.equals("2") || response.equals("3");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
  Positive Test: Code matches flag being played, correct QR code collected
  */
    public static boolean test16() {
        int QRCodeId = 1; //code matches flag being played
        int sessionId = 0;
        int userId = 1;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/check_feature?session_id="
                            + sessionId + "&user_id=" + userId + "&feature=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.equals("1") || response.equals("2") || response.equals("3");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
  Positive Test: Code matches flag being played, correct QR code collected
  */
    public static boolean test17() {
        int QRCodeId = 2; //code matches flag being played
        int sessionId = 0;
        int userId = 1;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/check_feature?session_id="
                            + sessionId + "&user_id=" + userId + "&feature=" + QRCodeId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.equals("1") || response.equals("2") || response.equals("3");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
    Positive Test: Ending game
    */
    public static boolean test18() {
        int sessionId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/end_game?session_id="
                            + sessionId))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return !response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    /*
    This must be successfully completed before Integration Tests can be run.
     */
    public static boolean setup() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://127.0.0.1:8080/capture_the_flag/login?" +
                            "username=" + "nayana"
                            + "&password=" + "password"))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();

            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                //System.out.println(response);

                return Integer.parseInt(response) >= 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
