package dataProvider;

import Payload.QuizPayload;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static test.QuestionBankTest.questionBankId;

public class QuizDataProvider {

    // Method to get future timestamp
    private static String getFutureTimestamp(int minutesAhead) {
        LocalDateTime futureTime = LocalDateTime.now().plusMinutes(minutesAhead);
        // Match exactly the format your API expects: "yyyy-MM-dd HH:mm:ss.SSS"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return futureTime.format(formatter);
    }


    @DataProvider(name = "quizData")
    public Object[][] getQuizData() {
        return new Object[][] {
                {
                        new QuizPayload(
                                "Advance React",
                                questionBankId,
                                10,
                                1,
                                getFutureTimestamp(10),   // Start time 5 mins ahead
                                getFutureTimestamp(65),  // End time 65 mins ahead
                                "active"
                        )
                }
        };
    }
}
