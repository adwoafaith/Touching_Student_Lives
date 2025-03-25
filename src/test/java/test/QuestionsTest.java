package test;

import Payload.CreateQuestionPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataProvider.QuestionsDataProvider;
import endpoints.QuestionsEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class QuestionsTest {
    @Test(priority = 1, dependsOnMethods = "createQuestionBankTest",
         dataProvider = "createQuestionData", dataProviderClass = QuestionsDataProvider.class)
    public void QuestionTest(CreateQuestionPayload questions) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String questionJson = objectMapper.writeValueAsString(questions); // Convert to JSON string

        Response response = QuestionsEndpoint.createQuestionsEndpoint(questionJson); // Send API request
        response.then().log().body(); //print response
        Assert.assertEquals(response.statusCode(),201, "Status code mismatch "); //validate status code

        List<List<Map<String, Object>>> outerList = response.jsonPath().getList("$");
        Assert.assertFalse(outerList.isEmpty(), "Response body is empty");

        List<Map<String, Object>> responseBody = outerList.get(0); // ✅ Extract the inner list
        Assert.assertFalse(responseBody.isEmpty(), "Inner list is empty");

        Map<String, Object> questionData = responseBody.get(0); // ✅ Extract the first question
        Assert.assertNotNull(questionData.get("id"), "Question ID is missing"); // Validate ID
        Assert.assertEquals(questionData.get("answers"), "A,C,D", "Answers mismatch!");
    }
}
