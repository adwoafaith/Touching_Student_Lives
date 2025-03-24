package dataProvider;

import Payload.GeneralPostPayload;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class GeneralPostDataProvider {

    @DataProvider(name = "generalPostDataProvider")
    public static Object[][] providePostData(){
        GeneralPostPayload payload = new GeneralPostPayload(
                "Automated Testing Opportunity",
                "This is a test automation opportunity for QA engineers.",
                "https://example.com/test-image.jpg",
                "active"
        );

        //Convert the payload to a Map for API request
        Map<String, String> formData = new HashMap<>();
        formData.put("title", payload.getTitle());
        formData.put("description", payload.getDescription());
        formData.put("image", payload.getImage());
        formData.put("status", payload.getStatus());

        return new Object[][]{{formData}};
    }
}
