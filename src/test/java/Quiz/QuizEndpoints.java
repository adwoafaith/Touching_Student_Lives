package Quiz;

import Routes.Routes;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static WebAdminLoginTest.LoginTest.authToken;

public class QuizEndpoints {
    public static Response createQuiz(QuizPayload payload) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("x-client-type", "web")
                .contentType("multipart/form-data");

        // Add all form parts
        request.multiPart("title", payload.getTitle());
        request.multiPart("question_bank_id", payload.getQuestion_bank_id());
        request.multiPart("total_questions", payload.getTotal_questions());
        request.multiPart("time_per_question", payload.getTime_per_question());
        request.multiPart("start_time", payload.getStart_time());
        request.multiPart("end_at", payload.getEnd_at());
        request.multiPart("status", payload.getStatus());

        return request.post(Routes.createQuiz);
    }
}