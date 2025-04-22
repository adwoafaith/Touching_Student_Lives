package Institution;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Countries.CountriesTest.countryId;


public class InstitutionsTest {
    public static String institutionId ;
    public static String institutionName;


    @Test(dataProvider = "institutionPayloadProvider",dataProviderClass = InstitutionDataProvider.class,priority = 1)
    public void createInstitutionTest(String institutionPayload) {
        Response response = InstitutionEndpoint.createInstitution(institutionPayload);
        int statusCode = response.statusCode();

        if (statusCode ==201 || statusCode ==200){
            String country = response.jsonPath().getString("country");
            institutionId = response.jsonPath().getString("id");
            institutionName = response.jsonPath().getString("name");
            String country_Id = response.jsonPath().getString("country_id");

            //assertions
            Assert.assertEquals(institutionName,"Automation University", "Name does not match created name");
            Assert.assertEquals(country, "Automation Country", "Country does not match ");
            Assert.assertEquals(country_Id,countryId,"country id does not match");
            Assert.assertNotNull(institutionId,"Institution Id must not be null");
            System.out.println(institutionId);
            response.then().log().body();

        }
        else if (statusCode == 409){
            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Institution with name Ernest Rwanda University of Science already exists","something went wrong");
            Assert.assertEquals(error,"Conflict","This is not a conflict recheck code");
        }
        else {
            Assert.fail("Something unexpected happened "+ statusCode);
        }

    }

    @Test(priority = 2)
    public void getSingleInstitutionsTest() {
        Response response = InstitutionEndpoint.getSingleInstitution(institutionId);
        response.then().log().body();

        int statusCode = response.statusCode();

        if (statusCode ==201 || statusCode ==200){
            String id  = response.jsonPath().getString("id");
            String name = response.jsonPath().getString("name");
            String country_Id = response.jsonPath().getString("country_id");

            //assertions
            Assert.assertEquals(institutionName,name, "Name does not match created name");
            Assert.assertEquals(institutionId, id, "Instituition Id does not match ");
            Assert.assertEquals(country_Id,countryId,"country id does not match");

        }
        else if (statusCode == 404){
            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Institution with ID b3e55408-9c3a-4e10-bef6-46b3f009586b not found","something went wrong");
            Assert.assertEquals(error,"Not Found","Something went wrong");
        }
        else if (statusCode == 400){
            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Validation failed (uuid is expected)","something went wrong");
            Assert.assertEquals(error,"Bad Request","This is not a conflict recheck code");
        }
        else {
            Assert.fail("Something unexpected happened "+ statusCode);
        }
    }



    @Test(priority = 3)
    public void getAllInstitutionsTest() {
        Response response = InstitutionEndpoint.getAllInstitutions();
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "updateInstitutionPayloadProvider",dataProviderClass = UpdateInstitutionDataProvider.class,priority = 4)
    public void updateInstitutionTest(String institutionPayload) {
        Response response = InstitutionEndpoint.updateInstitution(institutionPayload,institutionId);
        response.then().log().body();
        int statusCode = response.statusCode();

        if (statusCode ==201 || statusCode ==200){
            String name = response.jsonPath().getString("name");
            String location = response.jsonPath().getString("location");


            //assertions
            Assert.assertEquals(name,"Updated Automation University", "Name does not match created name");
            Assert.assertEquals(location,"kigali","Location does not match");


        }
        else if (statusCode == 409){
            String message = response.jsonPath().getString("message");
            String error = response.jsonPath().getString("error");
            response.then().log().body();

            Assert.assertEquals(message,"Institution with name Ernest Rwanda University of Science already exists","something went wrong");
            Assert.assertEquals(error,"Conflict","This is not a conflict recheck code");
        }
        else {
            Assert.fail("Something unexpected happened "+ statusCode);
        }

    }



    @Test(priority = 5)
    public void deleteInstitutionsTest() {
        Response response = InstitutionEndpoint.deleteInstitution(institutionId);
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
