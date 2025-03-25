package endpoints;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;
import static test.QuestionBankTest.questionBankId;
import static test.QuestionsTest.questionCreationId;

public class QuestionsEndpoint {

    public static Response createQuestionsEndpoint(String question) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .body(question)
                .header("x-client-type", "web");

        Response response = request.when().post(Routes.createQuestion);
        return response;
    }
    public static Response uploadQuestionCsvEndpoint(File csvFile, String questionBankId) throws IOException {
        byte[] fileContent = Files.readAllBytes(csvFile.toPath());
        RequestSpecification request = given()
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + authToken)
                .multiPart(new MultiPartSpecBuilder(fileContent)
                        .fileName(csvFile.getName())
                        .controlName("file")  // This must match what the server expects
                        .mimeType("text/csv") // Try "application/vnd.ms-excel" if this doesn't work
                        .build())
                .formParam("questionBankId", questionBankId)  // Changed from multiPart to formParam
                .header("x-client-type", "web");


        Response response = request.when().post(Routes.uploadQuestionCsv);
        return response;
    }
    public static Response GetAllQuestionsEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getAllQuestions);
        return response;
    }
    public static Response GetSingleQuestionEndpoint(String question_id) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .pathParam("questionId",question_id)
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getQuestionById);
        return response;
    }
    public static Response deleteQuestionsEndpoint(String question_id) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .pathParam("questionId",question_id)
                .header("x-client-type", "web");

        Response response = request.when().delete(Routes.deleteQuestion);
        return response;
    }


}
