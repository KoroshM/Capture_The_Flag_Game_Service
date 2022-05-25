package edu.uwb.css533.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import edu.uwb.css533.service.core.GameListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger LOGGER;
    static int PORT;

    // Runner method for this suite
    // Update with tests to run
    public static void main(String[] args) {
        LOGGER = LoggerFactory.getLogger(TestSuite.class);
        LOGGER.info("Setting up...");

        HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        PORT = 8080;

        try {
            GameListenerThread gameListenerThread = new GameListenerThread(PORT);
            gameListenerThread.start();
        } catch (IOException e) {
            LOGGER.error("Problem with server setup.");
            e.printStackTrace();
        }
        LOGGER.info("Servers running. Beginning test suite.");

        int total = 0;
        int pass = 0;

        setup()
        if(test1()) pass += 1;
        total++;
//        if(test2()) pass += 1;
//        total++;
        if(test3()) pass += 1;
        total++;
        if(test4()) pass += 1;
        total++;
//        if(test5()) pass += 1;
//        total++;
        if(test6()) pass += 1;
        total++;
        if(test7()) pass += 1;
        total++;
        if(test8()) pass += 1;
        total++;
        if(test9()) pass += 1;
        total++;
        if(test10()) pass += 1;
        total++;
        if(test11()) pass += 1;
        total++;
        if(test12()) pass += 1;
        total++;
        if(test13()) pass += 1;
        total++;
        if(test14()) pass += 1;
        total++;

        LOGGER.info("Test suite complete.");
        System.out.println("\n==========================================================================\n");
        System.out.println("Tests: " + total);
        System.out.println("Passed: " + pass);
        System.out.println("Failed: " + (total - pass));
    }

    public static boolean test1() {
        //Test 1 - normal path
        LOGGER.info("Testing register new user.");
        String username = "korosh"; //must be new user
        String password = "password";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/login?" +
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

    // This test has been made redundant
    // Attempting to register with an existing username is treated as an incorrect login attempt
//    public static boolean test2() {
//        //Test 2 - error condition
//        LOGGER.info("Testing register existing user.");
//        String username = "nayana"; //must be existing user
//        String password = "password";
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI("http://localhost:8080/capture_the_flag/login?" +
//                            "username=" + username
//                            + "&password=" + password))
//                    .GET()
//                    .timeout(Duration.ofSeconds(10))
//                    .build();
//            try {
//                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
//                System.out.println(response);
//
//                return response.toInt() < 0;
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        } catch (URISyntaxException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }

    public static boolean test3() {
        //Test 3 - normal path
        LOGGER.info("Testing correct login.");
        String username = "nayana"; //must be existing user
        String password = "password"; //must be correct password
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/login?" +
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

    public static boolean test4() {
        //Test 4 - error condition
        LOGGER.info("Testing incorrect login.");
        String username = "nayana"; //must be existing user
        String password = "123"; //must be incorrect password

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

                return Integer.parseInt(response) < 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // This test has been made redundant
    // The login function now registers the user if there is no such user in the database
//    public static boolean test5() {
//        //Test 5 - error condition
//        LOGGER.info("Testing login with unrecognized username.");
//        String username = "palmer"; //must be user that does not currently exist
//        String password = "password";
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI("http://localhost:8080/capture_the_flag/login?username=" + username
//                            + "&password=" + password))
//                    .GET()
//                    .timeout(Duration.ofSeconds(10))
//                    .build();
//            try {
//                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
//                System.out.println(response);
//
//                return response.equals("User does not exist.");
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        } catch (URISyntaxException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }

    public static boolean test6() {
        //Test 6 - normal path
        LOGGER.info("Testing create room when user is NOT active in another session.");
        int userId = 0; //user is not part of another active session
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/new_room?user_id=" + userId))
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

    public static boolean test7() {
        //Test 7 - error condition
        LOGGER.info("Testing create room when user is active in another session.");
        int userId = 0; //user is part of another active session
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/new_room?user_id=" + userId))
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

    public static boolean test8() {
        //Test 8 - normal path
        LOGGER.info("Testing join existing room with availability.");
        int userId = 1;
        int sessionId = 0; //session exists
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

                return !response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean test9() {
        //Test 9 - error condition
        LOGGER.info("Testing join non-existent room.");
        int userId = 2;
        int sessionId = 124; //session does not exist
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

                return response.startsWith("-");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean test10() {
        //Test 10 - normal path
        LOGGER.info("Testing start game with valid room.");
        int sessionId = 0; //at least two players are in the session
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/start_game?session_id=" + sessionId))
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

    // --------------------------------------------------------------------- Nayana we need to either change this
    // --------------------------------------------------------------------- back to requiring min 2 players or remove
    // --------------------------------------------------------------------- this test
    public static boolean test11() {
        //Test 11 - error condition
        LOGGER.info("Testing start game without enough players.");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/new_room?user_id=" + 2))
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
        int sessionId = 1; //session does not have at least two players
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/start_game?session_id=" + sessionId))
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

    public static boolean test12() {
        //Test 12 - normal path
        LOGGER.info("Testing correct QR code.");
        int QRCodeId = 1; //code matches flag being played
        int sessionId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/check_feature_code?code_id=" + QRCodeId
                            + "&session_id=" + sessionId))
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

    public static boolean test13() {
        //Test 13 - error condition
        LOGGER.info("Testing incorrect QR code.");
        int QRCodeId = 5; //code does not match flag being played
        int sessionId = 0;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/check_feature_code?code_id=" + QRCodeId
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

    public static boolean test14) {
        //Test 14 - error condition
        LOGGER.info("Testing start invalid session.");
        int sessionId = 123;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/start_game?session_id=" + sessionId))
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

    public static void setup() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/capture_the_flag/login?" +
                            "username=" + "nayana"
                            + "&password=" + "password"))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();
            try {
                String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
                System.out.println(response);

                return response.toInt() >= 0;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }
}
