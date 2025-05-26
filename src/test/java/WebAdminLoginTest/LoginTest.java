package WebAdminLoginTest;

import io.restassured.response.Response;
import magicLinkMethods.MagicLinkmethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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
    public void loginAndVerifyMagicLink() {
        // Record exact time right before sending request
        long requestStartTime = System.currentTimeMillis();

        // Step 1: Trigger login email
        System.out.println("🔐 Sending login request at: " + new Date(requestStartTime));
        Response loginResponse = LoginEndpoint.login(loginPayload);

        // Step 2: Fetch token with precise timing
        String extractedToken = null;
        int maxRetries = 10;
        int retryDelay = 5000;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("📬 Attempt " + attempt + " at: " + new Date());
                extractedToken = MagicLinkmethod.fetchTokenFromEmail(email, appPassword, requestStartTime);

                if (extractedToken != null) {
                    // Immediate verification after extraction
                    System.out.println("✅ Verifying magic link token...");
                    Response verifyResponse = LoginEndpoint.verifyMagicLinkEndpoint(extractedToken);

                    if (verifyResponse.getStatusCode() == 201) {
                        authToken = verifyResponse.jsonPath().getString("accessToken");
                        System.out.println("✅ Verification successful!");
                        return; // Exit on success
                    } else {
                        System.out.println("⚠️ Verification failed, retrying...");
                        extractedToken = null; // Reset token to force retry
                    }
                }

                Thread.sleep(retryDelay);
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        throw new RuntimeException("❌ Failed to complete magic link verification");
    }
}
