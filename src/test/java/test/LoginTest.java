package test;

import Payload.LoginPayload;
import endpoints.LoginEndpoint;
import io.restassured.response.Response;
import magicLinkMethods.MagicLinkmethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class LoginTest {
    public static String email;
    private static String appPassword;

    public static String authToken;

    LoginPayload loginPayload = new LoginPayload();

    @BeforeClass
    public void setUpData() {
        loadConfig();
        loginPayload.setEmail(email);
    }

    private void loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            email = properties.getProperty("email");
            appPassword = properties.getProperty("app_password");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void LoginTestCase() {
        Response response = LoginEndpoint.login(loginPayload);
        response.then().log().all();
    }

    @Test(priority = 2)
    public void verifyMagicLinkTestCase() {
        String magicLink = null;
        int maxRetries = 10;
        int retryDelay = 15000; // 15 seconds delay

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Attempt " + attempt + ": Checking inbox for the magic link...");
                List<String> emails = Collections.singletonList(MagicLinkmethod.fetchMagicLinkFromEmail(email, appPassword));
                System.out.println("Retrieved Emails: " + emails);

                magicLink = MagicLinkmethod.fetchMagicLinkFromEmail("womensahfaith@gmail.com", "ssqsqdkjweyoqois");
                if (magicLink != null && !magicLink.isEmpty()) {
                    break;
                }

                System.out.println("No new unread emails found. Retrying in " + (retryDelay / 1000) + " seconds...");
                Thread.sleep(retryDelay);
            } catch (Exception e) {
                System.out.println("Error fetching magic link: " + e.getMessage());
            }
        }

        if (magicLink == null || magicLink.isEmpty()) {
            throw new RuntimeException("No magic link found after multiple attempts!");
        }

        System.out.println("🔗 Fetched Magic Link: " + magicLink);

        String extractedToken = MagicLinkmethod.extractToken(magicLink);
        System.out.println("Extracted Token: " + extractedToken);

        Response response = LoginEndpoint.verifyMagicLinkEndpoint(extractedToken);
        response.then().log().all();

        // Log status code and headers
        int statusCode = response.getStatusCode();
        System.out.println("📡 Response Status Code: " + statusCode);
        System.out.println("🔍 Set-Cookie Header: " + response.getHeaders().getValue("Set-Cookie"));

        if (statusCode != 201) {
            throw new RuntimeException("Login verification failed with status: " + statusCode);
        }

        // Extract token with correct key
        authToken = response.jsonPath().getString("accessToken");
        System.out.println("New Auth Token: " + authToken);

        if (authToken == null) {
            throw new RuntimeException("Failed to retrieve new auth token!");
        }
    }
}