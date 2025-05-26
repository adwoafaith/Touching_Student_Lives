package MobileTest;

import WebAdminLoginTest.LoginPayload;
import Routes.Routes;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class MobileLoginNewUserEndpoint {
    public  static Response mobileLoginNewUser(LoginPayload payload){
        Response response = given()
                .contentType("application/json")
                .header("x-client-type","mobile")
                .body(payload)
                .when()
                .post(Routes.login);

        return response;

    }

    public static Response verifyOTPMobileNewUserEndpoint(String otp){

        JSONObject requestBody = new JSONObject();
        requestBody.put("otp", otp);

        Response response = given()
                .contentType("application/json")
                .header("x-client-type","mobile")
                .body(requestBody.toString())
                .when()
                .post(Routes.verifyOTP);
        return response;


    }
}
