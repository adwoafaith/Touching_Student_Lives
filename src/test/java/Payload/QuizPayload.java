package Payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

public class QuizPayload{

    @SerializedName("title")
    private String title;

    @SerializedName("question_bank_id")
    private String questionBankId;

    @SerializedName("total_questions")
    private int totalQuestions;

    @SerializedName("time_per_question")
    private int timePerQuestion;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("end_at")
    private String endAt;

    @SerializedName("status")
    private String status;

    // Constructor
    public QuizPayload(String title, String questionBankId, int totalQuestions, int timePerQuestion, String startTime, String endAt, String status) {
        this.title = title;
        this.questionBankId = questionBankId;
        this.totalQuestions = totalQuestions;
        this.timePerQuestion = timePerQuestion;
        this.startTime = startTime;
        this.endAt = endAt;
        this.status = status;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(int timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
