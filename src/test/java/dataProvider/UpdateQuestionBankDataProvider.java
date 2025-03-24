package dataProvider;

import Payload.CreateQuestionBankPayload;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class UpdateQuestionBankDataProvider {

    @DataProvider(name = "updateQuestionBank")
    public static Object[][] provideUpdatedQuestionBankData() {
        CreateQuestionBankPayload updatedPayload = new CreateQuestionBankPayload(
                "Updated Alice Johnson",
                "Updated Full-Stack",
                "Updated Spring Boot",
                "src/main/resources/1742285372304.jpeg"
        );

        // Convert the profile into a Map for API request
        Map<String, String> profileData = new HashMap<>();
        profileData.put("name", updatedPayload.getName());
        profileData.put("stack", updatedPayload.getStack());
        profileData.put("framework", updatedPayload.getFramework());

        String filePath = updatedPayload.getImage(); // Store the file path separately

        return new Object[][]{{profileData, filePath}};
    }
}