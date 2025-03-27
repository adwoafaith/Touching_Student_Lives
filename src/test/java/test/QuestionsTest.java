package test;

import Payload.CreateQuestionPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dataProvider.QuestionsDataProvider;
import endpoints.QuestionsEndpoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QuestionsTest {
    public static String questionCreationId; //store question id in this variable

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

        List<Map<String, Object>> responseBody = outerList.get(0); //  Extract the inner list
        Assert.assertFalse(responseBody.isEmpty(), "Inner list is empty");

        Map<String, Object> questionData = responseBody.get(0); // Extract the first question
        Assert.assertNotNull(questionData.get("id"), "Question ID is missing"); // Validate ID
        Assert.assertEquals(questionData.get("answers"), "A,C,D", "Answers mismatch!");

        questionCreationId = (String) questionData.get("id");

    }

    //@Test(priority = 2)
    public void GetAllQuestionsTest()  {
        Response response = QuestionsEndpoint.GetAllQuestionsEndpoint(); // Send API request
        response.then().log().body(); //print response
        Assert.assertEquals(response.statusCode(),200, "Status code mismatch "); //validate status code

    }

    //@Test(priority = 3)
    public void GetSingleQuestionsTest()  {
        Response response = QuestionsEndpoint.GetSingleQuestionEndpoint(questionCreationId); // Send API request
        response.then().log().body(); //print response
        Assert.assertEquals(response.statusCode(),200, "Status code mismatch "); //validate status code

    }

    //@Test(priority = 4)
    public void deleteQuestionsTest()  {
        Response response = QuestionsEndpoint.deleteQuestionsEndpoint(questionCreationId); // Send API request
        response.then().log().body(); //print response
        Assert.assertEquals(response.statusCode(),200, "Status code mismatch "); //validate status code
        String message = response.jsonPath().getString("message"); //store response message in a variable
        Assert.assertEquals(message,"Question deleted successfully","Message does not match"); //validate response message
        int code = response.jsonPath().getInt("statusCode"); //store status code in the body in a variable
        Assert.assertEquals(code, 200, "Status code is not 200");

    }

   // @Test(priority = 5)
    public void uploadQuestionsCsvTest() throws IOException {
        File csvFile = new File("src/main/resources/questions (1).csv");

        // Verify file exists and is readable
        if (!csvFile.exists() || !csvFile.canRead()) {
            throw new RuntimeException("Cannot read CSV file at " + csvFile.getAbsolutePath());
        }

        // Get the questionBankId from the QuestionBankTest class
        String questionBankId = QuestionBankTest.questionBankId;
        System.out.println("Using Question Bank ID: " + questionBankId);

        Response response = QuestionsEndpoint.uploadQuestionCsvEndpoint(csvFile, questionBankId);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();




        response.then().log().body(); //print response
        response.then().log().all().extract().response();
        Assert.assertEquals(response.statusCode(),201, "Status code mismatch "); //validate status code
        String message = response.jsonPath().getString("message"); //store message in a variable
        Assert.assertEquals(message,"Questions processed successfully.","message deos not match");

    }

}
