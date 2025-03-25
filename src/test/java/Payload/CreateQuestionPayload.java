package Payload;

import java.util.List;

public class CreateQuestionPayload {
    private List<Question> questions;

    public CreateQuestionPayload(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public static class Question {
        private String question;
        private String option_a;
        private String option_b;
        private String option_c;
        private String option_d;
        private List<String> answers;
        private String questionBank_id;
        private boolean is_golden;

        public Question(String question, String option_a, String option_b, String option_c, String option_d,
                        List<String> answers, String questionBank_id, boolean is_golden) {
            this.question = question;
            this.option_a = option_a;
            this.option_b = option_b;
            this.option_c = option_c;
            this.option_d = option_d;
            this.answers = answers;
            this.questionBank_id = questionBank_id;
            this.is_golden = is_golden;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getOption_a() {
            return option_a;
        }

        public void setOption_a(String option_a) {
            this.option_a = option_a;
        }

        public String getOption_b() {
            return option_b;
        }

        public void setOption_b(String option_b) {
            this.option_b = option_b;
        }

        public String getOption_c() {
            return option_c;
        }

        public void setOption_c(String option_c) {
            this.option_c = option_c;
        }

        public String getOption_d() {
            return option_d;
        }

        public void setOption_d(String option_d) {
            this.option_d = option_d;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }

        public String getQuestionBank_id() {
            return questionBank_id;
        }

        public void setQuestionBank_id(String questionBank_id) {
            this.questionBank_id = questionBank_id;
        }

        public boolean isIs_golden() {
            return is_golden;
        }

        public void setIs_golden(boolean is_golden) {
            this.is_golden = is_golden;
        }
    }
}
