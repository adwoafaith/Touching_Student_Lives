package endpoints;

public class Routes {
    public static String base_url = "https://api-tslp-staging.amalitech-dev.net/api";
    public static String base_url_dev = "https://api-tslp.amalitech-dev.net/api";


    //login dev
    public static String loginDev = base_url_dev+"/v1/auth/login";
    //login staging
    public static String login = base_url+"/v1/auth/login";
    public static String verifyMagicLink = base_url_dev+"/v1/auth/verify-magic-link";

    //Waitlist
    public static String getWaitList = base_url_dev+"/v1/auth/waiting";
    public static String approveWaitList = base_url_dev +"/v1/auth/{wait_list_id}/approve";

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

    //Events post endurl
    public static String creatingEventsPost = base_url+"/v1/event";
    public static String deleteEventsPost = base_url+"/v1/post/{deletePostId}";


    //MCQS Question banks url
    public static String createQuestionBank = base_url+"/v1/question-bank";
    public static String getAllQuestionBanks = base_url+"/v1/question-bank";
    public static String getSingleQuestionBank = base_url+"/v1/question-bank/{questionbank_id}";
    public static String deleteQuestionBank = base_url+"/v1/question-bank/{questionbank_id}";
    public static String updateQuestionBank = base_url+"/v1/question-bank/{questionbank_id}";


    //Questions url
    public static String uploadQuestionCsv = base_url+"/v1/questions/upload";
    public static String getQuestionById = base_url+"/v1/questions/{questionId}";
    public static String getAllQuestions = base_url+"/v1/questions";
    public static String getQuestionByBankId = base_url+"/v1/questions/questionBank/{questionId}";
    public static String deleteQuestion = base_url+"/v1/questions/{questionId}";
    public static String createQuestion = base_url+"/v1/questions/";
    public static String getGoldenQuestions = base_url+"/v1/questions/golden/{questionId}";


    //Quizzes url
    public static String createQuiz = base_url+"/v1/quiz/";
    public static String deleteQuiz = base_url+"/v1/quiz/{quizId}";
    public static String getAllQuiz = base_url+"/v1/quiz/";
    public static String GetSingleQuiz = base_url+"/v1/quiz/{quizId}";
    public static String updateQuiz = base_url+"/v1/quiz/{quizId}";

    //Leaderboard url
    public static String leaderboard = base_url+"/v1/leader-board/web";

    //get institutions url
    public static String getInstitutions = base_url+"/v1/institution/";

    //raffle url
    public static String createRaffle = base_url+"/v1/raffle";
    public static String getRaffleStatus = base_url+"/v1/raffle";
    public static String getRaffleParticipantsDetails = base_url+"/v1/raffle/participants";
    public static String getAllRaffle = base_url+"/v1/raffle";
    public static String getAllRaffleWinners = base_url+"/v1/raffle/winners/";

    //mobile
    public static String verifyOTP = base_url+"/v1/auth/verify-otp";








}
