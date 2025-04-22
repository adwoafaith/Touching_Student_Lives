package Countries;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountriesTest {

    @Test
    public void getAllCountriesTest() {
        Response response = CountriesEndpoint.getAllCountries();
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);


    }



}
