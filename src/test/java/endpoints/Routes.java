package endpoints;

public class Routes {
    public static String base_url = "https://api-tslp.amalitech-dev.net/api/";

    //login
    public static String login = base_url+"/v1/auth/login";
    public static String verifyMagicLink = base_url+"/v1/auth/verify-magic-link";

    //clubendpoint

    public static String createClub = base_url+"/v1/club";
    public static String getClubs = base_url+"/v1/club";
    public static String  getClub = base_url+"/v1/club/{club_id}";
    public static String deleteClub = base_url+"/v1/club/{club_id}";


    //creating of opportunity
    public static String createOpportunity = base_url+"/v1/opportunity";
    public static String updateOpportunity = base_url+"/v1/opportunity/{post_Id}";

    //General post endurl
    public static String createGeneralPost = base_url+"/v1/post";
    public static String deleteGeneralPost = base_url+"/v1/post/{deletePostId}";
    public static String updateGeneralPost = base_url+"/v1/post/{id}";


    //MCQS Question banks url
    public static String createQuestionBank = base_url+"v1/question-bank";
    public static String getAllQuestionBanks = base_url+"v1/question-bank";
    public static String getSingleQuestionBank = base_url+"v1/question-bank/{questionbank_id}";
    public static String deleteQuestionBank = base_url+"v1/question-bank/{questionbank_id}";
    public static String updateQuestionBank = base_url+"v1/question-bank/{questionbank_id}";


    //Questions url
    public static String uploadQuestionCsv = base_url+"v1/questions/upload";
    public static String getQuestionById = base_url+"v1/questions/{questionId}";
    public static String getAllQuestions = base_url+"v1/questions";
    public static String getQuestionByBankId = base_url+"v1/questions/questionBank/{questionId}";
    public static String deleteQuestion = base_url+"v1/questions/{questionId}";
    public static String createQuestion = base_url+"v1/questions/";
    public static String getGoldenQuestions = base_url+"v1/questions/golden/{questionId}";


    //Quizzes url
    public static String createQuiz = base_url+"v1/quiz/";
    public static String deleteQuiz = base_url+"v1/quiz/{quizId}";
    public static String getAllQuiz = base_url+"v1/quiz/";
    public static String GetSingleQuiz = base_url+"v1/quiz/{quizId}";
    public static String updateQuiz = base_url+"v1/quiz/{quizId}";

    //Leaderboard url
    public static String leaderboard = base_url+"/v1/leader-board/web";

    //get institutions url
    public static String getInstitutions = base_url+"/v1/institution/";

    //raffle url
    public static String createRaffle = base_url+"/v1/raffle";
    public static String getActiveRaffle = base_url+"/v1/raffle";
    public static String getRaffleParticipantsDetails = base_url+"/v1/raffle";
    public static String getAllRaffle = base_url+"/v1/raffle";









}
