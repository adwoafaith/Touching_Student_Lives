package Institution;

import Countries.CountriesEndpoint;
import Countries.CountriesTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;


public class CountryCleanUP {

    @AfterSuite
    public void deleteCountryAfterAllTests() {

        // Check if the countryId is not null and not empty
        if (CountriesTest.countryId != null && !CountriesTest.countryId.isEmpty()) {
            System.out.println("⚙️ Cleaning up country with ID: " + CountriesTest.countryId);

            Response response = CountriesEndpoint.deleteCountry(CountriesTest.countryId);
            int statusCode = response.getStatusCode();

            switch (statusCode) {
                case 200:
                    Assert.assertEquals(statusCode, 200, "Country deleted successfully.");
                    break;

                case 404:
                    String message404 = response.jsonPath().getString("message");
                    String error404 = response.jsonPath().getString("error");
                    response.then().log().body();

                    Assert.assertEquals(message404, "Country not found", "Country was already deleted or missing.");
                    Assert.assertEquals(error404, "Not Found", "Error type mismatch.");
                    break;

                case 400:
                    String message400 = response.jsonPath().getString("message");
                    String error400 = response.jsonPath().getString("error");
                    response.then().log().body();

                    Assert.assertEquals(message400, "Validation failed (uuid is expected)", "Invalid UUID format");
                    Assert.assertEquals(error400, "Bad Request", "Error type mismatch");
                    break;

                default:
                    Assert.fail("Something unexpected happened. Status code: " + statusCode);
            }

            response.then().log().body();
        } else {
            System.out.println("No country ID available for cleanup. Skipping delete.");
        }
    }
}
