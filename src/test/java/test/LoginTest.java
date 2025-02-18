package test;

import LoginPayload.LoginPayload;

import endpoints.LoginEndpoint;
import io.restassured.response.Response;
import magicLinkMethods.MagicLinkmethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class LoginTest {

    //generate the data here
    LoginPayload loginPayload = new LoginPayload();

    @BeforeClass
    public void setUpData(){
        loginPayload.setEmail("womensahfaith@gmail.com");
    }

    @Test(priority = 1)
    public void LoginTestCase(){
        Response response = LoginEndpoint.login(loginPayload);
        response.then().log().all();
    }

    @Test(priority = 2)
        public void verifyMagicLinkTestCase(){
            // Fetch the magic link from the email

            String magicLink = null;
            try {
                magicLink = MagicLinkmethod.fetchMagicLinkFromEmail("womensahfaith@gmail.com", "");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fetched Magic Link: " + magicLink);

            // Extract token from the magic link
            String extractedToken = MagicLinkmethod.extractToken(magicLink);
            System.out.println("Extracted Token: " + extractedToken);

            // Assert if token is not null
            assert extractedToken != null : "Token extraction failed!";

            // Use the extracted token to verify the magic link
            if (extractedToken != null) {
                // Pass the extracted token to the API
                Response response = LoginEndpoint.verifyMagicLinkEndpoint(extractedToken);
                response.then().log().all();

                // Extract the new auth token from the response
                String authToken = response.jsonPath().getString("authToken");
                System.out.println("New Auth Token: " + authToken);

                // Assert if the auth token was returned successfully
                assert authToken != null : "Failed to retrieve new auth token!";
            } else {
                System.err.println("Token was not extracted. Magic link extraction failed.");
            }
        }
//    @Test(priority = 3)
////    public void useAuthTokenForOtherRequests() {
////        // Example of using the `authToken` in another API request
////        if (authToken != null) {
////            // Use authToken in another endpoint as needed
////            Response response = LoginEndpoint.someOtherApiRequest(authToken);
////            response.then().log().all();
////        } else {
////            System.err.println("Auth token not available for further requests.");
////        }
////    }
    }

