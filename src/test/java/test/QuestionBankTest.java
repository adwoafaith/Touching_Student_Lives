package test;

import dataProvider.QuestionBankDataProvider;
import endpoints.QuestionBankEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class QuestionBankTest {
    private static String questionBankId;

    @Test(dataProvider = "createQuestionBank",dataProviderClass = QuestionBankDataProvider.class)
    public void createQuestionBank(Map<String, String> formData){

        Response response = QuestionBankEndpoint.createQuestionBank(formData); //send API request
        questionBankId = response.jsonPath().getString("[0].id");// Extract the question bank ID from the response
        System.out.println("Extracted Question Bank ID: " + questionBankId);
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 200!");
        Assert.assertNotNull(response.jsonPath().getString("name"), "name property is missing!");// Check if name property exists in the response
        Assert.assertNotNull(response.jsonPath().getString("framework"), "framework property is missing!");// Check if framework property exists in the response


    }
}
