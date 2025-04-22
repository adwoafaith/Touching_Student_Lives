package Countries;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountriesTest {
    public static String countryId;
    public static String countryCode;

    @Test(priority = 3)
    public void getAllCountriesTest() {
        Response response = CountriesEndpoint.getAllCountries();
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(dataProvider = "countryPayloadProvider",dataProviderClass = CountriesDataProvider.class,priority = 1)
    public void createCountryTest(String countryPayload) {
        Response response = CountriesEndpoint.createCountry(countryPayload);
        int statusCode = response.statusCode();


        if (statusCode ==201 || statusCode ==200){
            String name = response.jsonPath().getString("name");
            countryId = response.jsonPath().getString("id");
            countryCode = response.jsonPath().getString("code");
            boolean disabled = response.jsonPath().getBoolean("disabled");

            //assertions
            Assert.assertEquals(name,"Automation Country", "Name does not match created name");
            Assert.assertEquals(countryCode, "AC", "Code does not match created code");
            Assert.assertEquals(disabled,false,"disabled is not ");
            System.out.println(countryId);
            response.then().log().body();

        }
        else if (statusCode == 409){
            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Country already exists");
            Assert.assertEquals(error,"Conflict","This is not a conflict recheck code");
        }
        else {
            Assert.fail("Something unexpected happened "+ statusCode);
        }

    }

    @Test(priority = 2)
    public void getSingleCountriesTest() {
        Response response = CountriesEndpoint.getSingleCountry(countryId);

        int statusCode = response.getStatusCode();

        if(statusCode == 200){
            String name = response.jsonPath().getString("name");
            String Id = response.jsonPath().getString("id");
            String code = response.jsonPath().getString("code");
            boolean disabled = response.jsonPath().getBoolean("disabled");

            Assert.assertEquals(name,"Automation Country");
            Assert.assertEquals(countryId,Id,"Id does not match");
            Assert.assertEquals(countryCode,code," Code does not match");
            Assert.assertEquals(disabled,false,"country disabled");

        }

        else if (statusCode == 404){

            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Country not found","Something went wrong");
            Assert.assertEquals(error,"Not Found","Something went wrong");
        }

        else if (statusCode == 400){

            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Validation failed (uuid is expected)","Something went wrong");
            Assert.assertEquals(error,"Bad Request","Something went wrong");
        }
        else {
            Assert.fail("Something unexpected happened "+ statusCode);
        }

        response.then().log().body();
    }

    //@Test(priority = 4)
    public void deleteCountriesTest() {
        Response response = CountriesEndpoint.deleteCountry(countryId);

        int statusCode = response.getStatusCode();

        if(statusCode == 200){

            Assert.assertEquals(response.statusCode(),200,"User deleted successfully");

        }

        else if (statusCode == 404){

            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Country not found","Something went wrong");
            Assert.assertEquals(error,"Not Found","Something went wrong");
        }

        else if (statusCode == 400){

            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Validation failed (uuid is expected)","Something went wrong");
            Assert.assertEquals(error,"Bad Request","Something went wrong");
        }
        else {
            Assert.fail("Something unexpected happened "+ statusCode);
        }

        response.then().log().body();
    }



}
