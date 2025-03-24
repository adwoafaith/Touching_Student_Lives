package dataProvider;

import Payload.CreateQuestionBankPayload;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class QuestionBankDataProvider {

    @DataProvider(name = "createQuestionBank")
    public static Object[][] provideDeveloperProfileData() {
        CreateQuestionBankPayload questionBankPayload = new CreateQuestionBankPayload(
                "Alice Johnson",
                "Full-Stack",
                "Spring Boot",
                "https://example.com/alice.jpg"
        );

        // Convert the profile into a Map for API request
        Map<String, String> profileData = new HashMap<>();
        profileData.put("name",  questionBankPayload.getName());
        profileData.put("stack", questionBankPayload.getStack());
        profileData.put("framework", questionBankPayload.getFramework());
        profileData.put("image", questionBankPayload.getImage());

        return new Object[][]{{profileData}};
    }
}
