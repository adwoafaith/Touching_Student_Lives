package endpoints;


import LoginPayload.LoginPayload;
import io.restassured.response.Response;

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
        Response response = given()
                    .contentType("application/json")
                    .header("x-client-type","web")
                    .body(magicLinkToken)
                .when()
                .post(Routes.verifyMagicLink);
        return response;

    }
}
