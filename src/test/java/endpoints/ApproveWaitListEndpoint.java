package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class ApproveWaitListEndpoint {

    public static Response approveWaitList(String waitListId){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .pathParams("wait_list_id",waitListId)
                .when()
                .patch(Routes.approveWaitList);
        return response;

    }
}
