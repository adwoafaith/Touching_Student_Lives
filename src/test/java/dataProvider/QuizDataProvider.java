package dataProvider;

import Payload.QuizPayload;
import org.testng.annotations.DataProvider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static test.QuestionBankTest.questionBankId;

public class QuizDataProvider {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @DataProvider(name = "quizData")
    public Object[][] getQuizData() {
        // Use a known valid question bank ID from your Postman test


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusMinutes(10);
        LocalDateTime endTime = startTime.plusHours(1);

        return new Object[][] {
                {
                        new QuizPayload(
                                "Advance React II",
                                questionBankId,
                                1,
                                1,
                                startTime.format(formatter),
                                endTime.format(formatter),
                                "active"
                        )
                }
        };
    }
}