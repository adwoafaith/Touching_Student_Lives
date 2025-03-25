package test;

import Payload.QuizPayload;
import com.google.gson.Gson;
import dataProvider.QuizDataProvider;
import endpoints.QuizEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class QuizTest {

    public static String quizId;
    @Test(priority = 2, dataProvider = "quizData", dataProviderClass = QuizDataProvider.class)
    public void createQuiz(QuizPayload quizPayload) {

        System.out.println("Sending payload: " + new Gson().toJson(quizPayload)); // Print payload before sending
        Response response = QuizEndpoints.createQuiz(quizPayload);

        quizId = response.jsonPath().getString("id"); // Extract quiz id and store in quizId variable
        response.then().log().body();
        //perform assertion
        String id = response.jsonPath().getString("id");
        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title,"Advance React");
        System.out.println(quizId);
    }
}
