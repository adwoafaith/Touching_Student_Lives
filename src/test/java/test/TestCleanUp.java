package test;

import endpoints.QuestionBankEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class TestCleanUp {

    @AfterSuite
    public void deleteQuestionBankAfterAllTests() {
        if (QuestionBankTest.questionBankId != null) {
            System.out.println("Cleaning up Question Bank with ID: " + QuestionBankTest.questionBankId);
            Response response = QuestionBankEndpoint.deleteQuestionBank(QuestionBankTest.questionBankId);
            response.prettyPrint();
            Assert.assertEquals(response.getStatusCode(), 200, "Failed to delete question bank");
            Assert.assertEquals(response.jsonPath().getString("message"),
                    "Question bank deleted successfully");
        } else {
            System.out.println("No Question Bank to clean up");
        }
    }
}
