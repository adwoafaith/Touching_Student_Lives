package WaitList;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static WebAdminLoginTest.LoginTest.authToken;

public class ApproveWaitListEndpoint {

    public static Response approveWaitList(WaitListPayload payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .when()
                .patch(Routes.approveWaitList);
        return response;

    }
}
