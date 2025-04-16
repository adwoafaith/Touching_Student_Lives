package MobileTest;


import Mobile.MobileLoginNewUserEndpoint;
import Payload.LoginPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MobileLoginTestNewUsers {
    public static String email;
    public static String appPassword;

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
        System.out.println(email);
        Response response = MobileLoginNewUserEndpoint.mobileLoginNewUser(loginPayload);
        System.out.println(loginPayload.getEmail());
        response.then().log().all();

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
