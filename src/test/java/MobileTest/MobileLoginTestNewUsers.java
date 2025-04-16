package MobileTest;

import Mobile.MobileLoginEndpoint;
import Payload.LoginPayload;
import io.restassured.response.Response;
import magicLinkMethods.OtpFetcher;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MobileLoginTestNewUsers {
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
            email = properties.getProperty("mobileEmail");
            appPassword = properties.getProperty("mobile_app_password");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void loginWithOtpVerificationMobile() {
        // ⏰ Record time before login
        long requestStartTime = System.currentTimeMillis();

        // 📨 Step 1: Trigger OTP email
        System.out.println("📲 Sending mobile login request at: " + new Date(requestStartTime));
        Response loginResponse = MobileLoginEndpoint.mobileLogin(loginPayload);

        if (loginResponse.getStatusCode() != 201) {
            throw new RuntimeException("❌ Login request failed with status: " + loginResponse.getStatusCode());
        }

        // 🧠 Step 2: Fetch OTP
        String otpCode = null;
        int maxRetries = 8;
        int retryDelay = 5000;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("🔍 Attempting to fetch OTP, try #" + attempt + "...");
                otpCode = OtpFetcher.fetchOtpFromEmail(email, appPassword, requestStartTime);

                if (otpCode != null) {
                    break;
                }

                Thread.sleep(retryDelay);
            } catch (Exception e) {
                System.out.println("❌ Error while fetching OTP: " + e.getMessage());
            }
        }

        if (otpCode == null) {
            throw new RuntimeException("❌ Failed to retrieve OTP after " + maxRetries + " attempts.");
        }

        // 🚀 Step 3: Submit OTP verification (assuming a separate verifyOtp method handles this)
        Response otpVerifyResponse = MobileLoginEndpoint.verifyOTPMobileEndpoint(otpCode);

        if (otpVerifyResponse.getStatusCode() == 201) {
//            authToken = otpVerifyResponse.jsonPath().getString("accessToken");
            System.out.println(authToken);
            System.out.println("✅ Mobile OTP verification successful!");

            //perform assertions
            JSONObject jsonObject = new JSONObject(otpVerifyResponse.getBody().asString());

            String accessToken = jsonObject.optString("accessToken");
            JSONObject user = jsonObject.getJSONObject("user");
            String emailReturned = user.optString("email");
            JSONObject studentProfile = user.optJSONObject("student_profile");

            Assert.assertNotNull(accessToken, "Access token should not be null");
            Assert.assertFalse(accessToken.isEmpty(), "Access token should not be empty");
            Assert.assertNotNull(user, "User object should not be null");
            Assert.assertEquals(emailReturned, email, "Returned email should match login email");
            Assert.assertNotNull(studentProfile, "Student profile should not be null");
            Assert.assertFalse(user.optBoolean("deleted"), "User should not be marked as deleted");
            Assert.assertFalse(studentProfile.optBoolean("deleted"), "Student profile should not be marked as deleted");

        } else {
            throw new RuntimeException("❌ OTP verification failed with status: " + otpVerifyResponse.getStatusCode());
        }
    }

}
