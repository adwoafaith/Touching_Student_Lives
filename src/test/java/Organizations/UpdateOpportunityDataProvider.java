package Organizations;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UpdateOpportunityDataProvider {

    @DataProvider(name = "UpdateOpportunityDataProvider")
    public static Object[][] provideOpportunityData() {
        Map<String, String> formData = new HashMap<>();
        formData.put("startDate", "2026-11-10");
        formData.put("endDate", "2026-11-10");
        formData.put("startTime", "15:25:00");
        formData.put("endTime", "15:45:00");
        formData.put("country_id", "918b51c9-c3de-4653-834b-bb0fd16ff15b");
        formData.put("institution_id", "0716ce43-0996-4447-bfa3-ab57ecf7fc25");
        formData.put("title", "Update opportunity");
        formData.put("description", "Test automation opportunity description update");
        formData.put("countries", Arrays.toString(new String[]{}));
        formData.put("institutions", Arrays.toString(new String[]{}));
        formData.put("eligibility", Arrays.toString(new String[]{}));
        formData.put("applicationUrl", "https://www.youtube.com/watch?v=mHsHHFP-k34");
        formData.put("image", "https://example.com/image.jpg");
        formData.put("status", "active");
        formData.put("isGlobal", "true"); // Set isGlobal to true

        return new Object[][]{{formData}};
    }
}