package GeneralPost;

import Institution.InstitutionsEndpoint;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetInstitutionsTest {
    public static String instituitionId;

    @Test(priority = 1)
    public void getInstitutionId(){

        Response response = InstitutionsEndpoint.getInstitutions();
        response.prettyPrint();

        instituitionId = response.jsonPath().getString("id[0]");
        System.out.println(instituitionId);

    }
}
