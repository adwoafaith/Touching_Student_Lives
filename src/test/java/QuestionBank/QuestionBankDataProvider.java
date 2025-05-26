package QuestionBank;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class QuestionBankDataProvider {

    @DataProvider(name = "createQuestionBank")
    public static Object[][] provideQuestionBankData() {
        CreateQuestionBankPayload questionBankPayload = new CreateQuestionBankPayload(
                "Alice Johnson",
                "Full-Stack",
                "Spring Boot",
                "src/main/resources/1742285372304.jpeg" // File path instead of URL
        );

        // Convert the profile into a Map for API request
        Map<String, String> profileData = new HashMap<>();
        profileData.put("name",  questionBankPayload.getName());
        profileData.put("stack", questionBankPayload.getStack());
        profileData.put("framework", questionBankPayload.getFramework());

        String filePath = questionBankPayload.getImage(); // Store the file path separately

        return new Object[][]{{profileData, filePath}};
    }
}
