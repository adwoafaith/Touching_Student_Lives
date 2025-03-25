package dataProvider;

import Payload.CreateQuestionPayload;
import org.testng.annotations.DataProvider;


import java.util.Arrays;
import java.util.List;

import static test.QuestionBankTest.questionBankId;

public class QuestionsDataProvider {

    @DataProvider(name = "createQuestionData")
    public static Object[][] provideQuestionData() {
        List<CreateQuestionPayload.Question> questions = Arrays.asList(
                new CreateQuestionPayload.Question(
                        "What is not DEVCOPS HG .js?",
                        "A front-end S",
                        "A server-side S runtime",
                        "A S system",
                        "A CSS S",
                        Arrays.asList("A", "C", "D"),
                        questionBankId,
                        true
                )
        );

        CreateQuestionPayload questionPayload = new CreateQuestionPayload(questions);

        return new Object[][]{{questionPayload}};
    }
}
