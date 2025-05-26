package QuestionBank;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static WebAdminLoginTest.LoginTest.authToken;

public class QuestionBankEndpoint {

    public static Response createQuestionBank(Map<String, String> formData, String filePath) {

        File file = new File(filePath);
        System.out.println("File exists: " + file.exists() + " | Path: " + file.getAbsolutePath());

        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .multiPart("image",file,"image/jpeg")
                .contentType(ContentType.MULTIPART);

        // Add updated form-data dynamically
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            request.multiPart(entry.getKey(), entry.getValue());
        }

        return request.when().post(Routes.createQuestionBank);

    }

    public static Response getAllQuestionBank() {

         RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .contentType(ContentType.MULTIPART);
        return request.when().get(Routes.getAllQuestionBanks);

    }

    public static Response getSingleQuestionBank(String questionBankId) {

        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParam("questionbank_id",questionBankId)
                .contentType(ContentType.MULTIPART);
        return request.when().get(Routes.getSingleQuestionBank);

    }

    public static Response updateQuestionBank(Map<String, String> formData, String filePath, String questionBankId) {

        File file = new File(filePath);
        System.out.println("File exists: " + file.exists() + " | Path: " + file.getAbsolutePath());

        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParam("questionbank_id",questionBankId)
                .multiPart("image",file,"image/jpeg")
                .contentType(ContentType.MULTIPART);

        // Add updated form-data dynamically
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            request.multiPart(entry.getKey(), entry.getValue());
        }

        return request.when().put(Routes.updateQuestionBank);

    }


    public static Response deleteQuestionBank(String questionBankId) {

        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParam("questionbank_id",questionBankId)
                .contentType(ContentType.MULTIPART);
        return request.when().delete(Routes.deleteQuestionBank);

    }


}
