package Quiz;

public class QuizPayload {
    private String title;
    private String question_bank_id; // Fixed field name consistency
    private int total_questions;
    private int time_per_question;
    private String start_time;
    private String end_at;
    private String status;

    public QuizPayload(String title, String question_bank_id, int total_questions,
                       int time_per_question, String start_time, String end_at, String status) {
        this.title = title;
        this.question_bank_id = question_bank_id; // Use actual parameter
        this.total_questions = total_questions;
        this.time_per_question = time_per_question;
        this.start_time = start_time;
        this.end_at = end_at;
        this.status = status;
    }

    // Getters - must match exact field names
    public String getTitle() { return title; }
    public String getQuestion_bank_id() { return question_bank_id; }
    public int getTotal_questions() { return total_questions; }
    public int getTime_per_question() { return time_per_question; }
    public String getStart_time() { return start_time; }
    public String getEnd_at() { return end_at; }
    public String getStatus() { return status; }
}