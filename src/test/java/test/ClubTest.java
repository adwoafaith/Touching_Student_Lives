package test;


import endpoints.ClubEndpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ClubTest {
    private static String clubAdminId;
    private static String institutionId;
    private static String countryId;

    @Test(priority = 3)
    public void getClubTestCase(){

        Response response = ClubEndpoint.getClubs();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);

        // Parse JSON response
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> clubs = jsonPath.getList("data");

        boolean clubFound = false;



        //let's extract the id's from the admin currently logging in
        // Find a club where club_admin matches the logged-in email
        for (Map<String, Object> club : clubs) {

            Map<String, Object> createdBy = (Map<String, Object>) club.get("created_by"); // Extract created_by object
            String clubAdminEmail = createdBy.get("email").toString();  // Get admin email

            if (clubAdminEmail.equals(LoginTest.email)) {  // Match the logged-in email
                clubAdminId = club.get("club_admin").toString();
                institutionId = club.get("institution_id").toString();
                countryId = club.get("country_id").toString();

                System.out.println(" Extracted club_admin: " + clubAdminId);
                System.out.println("Extracted institution_id: " + institutionId);
                System.out.println("Extracted country_id: " + countryId);

                clubFound = true;
                break;

            }

        }
        if (!clubFound) {
            throw new RuntimeException("No club found with the logged-in email as club_admin!");
        }

    }



    }

//    @DataProvider(name="clubDataProvider")
//    public Object[][]{
//            new ClubPayload()
//    }
