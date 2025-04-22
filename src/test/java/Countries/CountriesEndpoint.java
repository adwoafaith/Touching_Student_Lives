package Countries;

import endpoints.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static SuperAdmin.SuperAdminLogin.authToken;
import static io.restassured.RestAssured.given;

public class CountriesEndpoint {

    public static Response getAllCountries(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .when()
                .get(Routes.getAllCountries);
        return response;

    }
    public static Response createCountry(String data){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .body(data)
                .when()
                .post(Routes.createCountry);
        return response;

    }
    public static Response getSingleCountry(String countryId){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .pathParam("country_id",countryId)
                .when()
                .get(Routes.getSingleCountry);
        return response;

    }
}
