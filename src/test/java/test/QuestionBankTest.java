package test;

import dataProvider.QuestionBankDataProvider;
import endpoints.QuestionBankEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class QuestionBankTest {
    private static String questionBankId;

    @Test(priority = 1, dataProvider = "createQuestionBank",dataProviderClass = QuestionBankDataProvider.class)
    public void createQuestionBankTest(Map<String, String> formData, String filePath){

        Response response = QuestionBankEndpoint.createQuestionBank(formData,filePath); //send API request
        response.prettyPrint();
        questionBankId = response.jsonPath().getString("[0].id");// Extract the question bank ID from the response
        System.out.println("Extracted Question Bank ID: " + questionBankId);
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 200!");
        Assert.assertNotNull(response.jsonPath().getString("name"), "name property is missing!");// Check if name property exists in the response
        Assert.assertNotNull(response.jsonPath().getString("framework"), "framework property is missing!");// Check if framework property exists in the response


    }
    @Test(priority = 2, dependsOnMethods = "createQuestionBankTest")
    public void getAllQuestionBankTest(){
        Response response = QuestionBankEndpoint.getAllQuestionBank();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200!");



    }

    @Test( priority = 3,dependsOnMethods = "createQuestionBankTest")
    public void getSingleQuestionBankTest(){
        Response response = QuestionBankEndpoint.getSingleQuestionBank(questionBankId);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200!");



    }

    @Test( priority = 4,dependsOnMethods = "createQuestionBankTest")
    public void deleteQuestionBankTest(){
        Response response = QuestionBankEndpoint.deleteQuestionBank(questionBankId);
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getString("message"), "Question bank deleted successfully", "Deletion message mismatch!");




    }

}
