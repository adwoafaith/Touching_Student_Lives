package Mobile;

import Payload.LoginPayload;
import endpoints.Routes;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class MobileLoginEndpoint {

    public  static Response mobileLogin(LoginPayload payload){
        Response response = given()
                .contentType("application/json")
                .header("x-client-type","mobile")
                .body(payload)
                .when()
                .post(Routes.login);

        return response;

    }

    public static Response verifyOTPMobileEndpoint(String otp){

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
