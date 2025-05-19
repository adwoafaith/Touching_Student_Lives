package WebAdminLoginTest;


import Routes.Routes;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class LoginEndpoint {
    public  static Response login(LoginPayload payload){
        Response response = given()
                    .contentType("application/json")
                    .header("x-client-type","web")
                    .body(payload)
                .when()
                .post(Routes.login);

        return response;

    }

    public static Response verifyMagicLinkEndpoint(String magicLinkToken){

        JSONObject requestBody = new JSONObject();
        requestBody.put("token", magicLinkToken);

        Response response = given()
                    .contentType("application/json")
                    .header("x-client-type","web")
                    .body(requestBody.toString())
                .when()
                .post(Routes.verifyMagicLink);
        return response;


    }

}
