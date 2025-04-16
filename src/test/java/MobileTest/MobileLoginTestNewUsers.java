package MobileTest;

import Mobile.MobileLoginEndpoint;
import Mobile.MobileLoginNewUserEndpoint;
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
            email = properties.getProperty("mobileEmailNew_user");
            appPassword = properties.getProperty("newUser_app_password");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void LoginNewUser() {

        Response response = MobileLoginNewUserEndpoint.mobileLoginNewUser(loginPayload);

        //Assertions
        String message = response.jsonPath().getString("message");
        String statusCode = response.jsonPath().getString("statusCode");

        // Check that message is one of the expected outcomes
        boolean isExpectedMessage = message.equalsIgnoreCase("Joined waiting list Successful") || message.equalsIgnoreCase("Already on waiting list");


        Assert.assertTrue(isExpectedMessage,
                "Unexpected message received: " + message + ". Expected 'Joined waiting list Successful' or 'You are already on the wait list'");
        Assert.assertEquals(statusCode,"200","Staus code mismatch");




    }
}
