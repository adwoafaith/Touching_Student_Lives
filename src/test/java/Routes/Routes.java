package Routes;

public class Routes {

    public static String base_url = "https://api-tslp-staging.amalitech-dev.net/api/v1";
    //public static String base_url_dev = "https://api-tslp.amalitech-dev.net/api";


    //login dev
    //public static String loginDev = base_url_dev+"/v1/auth/login";
    //login staging
    public static String login = base_url+"/auth/login";
    public static String verifyMagicLink = base_url+"/auth/verify-magic-link";

    //Waitlist
    public static String getWaitList = base_url+"/auth/waiting";
    public static String approveWaitList = base_url +"/auth/approve-users";

    //club endpoint

    public static String createClub = base_url+"/club";
    public static String getClubs = base_url+"/club";
    public static String  getClub = base_url+"/club/{club_id}";
    public static String deleteClub = base_url+"/club/{club_id}";


    //creating of opportunity
    public static String createOpportunity = base_url+"/opportunity";
    public static String updateOpportunity = base_url+"/opportunity/{post_Id}";

    //General post endurl
    public static String createGeneralPost = base_url+"/post";
    public static String deleteGeneralPost = base_url+"/post/{deletePostId}";
    public static String updateGeneralPost = base_url+"/post/{id}";

    //Events post endurl
    public static String creatingEventsPost = base_url+"/event";
    public static String deleteEventsPost = base_url+"/post/{deletePostId}";


    //MCQS Question banks url
    public static String createQuestionBank = base_url+"/question-bank";
    public static String getAllQuestionBanks = base_url+"/question-bank";
    public static String getSingleQuestionBank = base_url+"/question-bank/{questionbank_id}";
    public static String deleteQuestionBank = base_url+"/question-bank/{questionbank_id}";
    public static String updateQuestionBank = base_url+"/question-bank/{questionbank_id}";


    //Questions url
    public static String uploadQuestionCsv = base_url+"/questions/upload";
    public static String getQuestionById = base_url+"/questions/{questionId}";
    public static String getAllQuestions = base_url+"/questions";
    public static String getQuestionByBankId = base_url+"/questions/questionBank/{questionId}";
    public static String deleteQuestion = base_url+"/questions/{questionId}";
    public static String createQuestion = base_url+"/questions/";
    public static String getGoldenQuestions = base_url+"/questions/golden/{questionId}";


    //Quizzes url
    public static String createQuiz = base_url+"/quiz/";
    public static String deleteQuiz = base_url+"/quiz/{quizId}";
    public static String getAllQuiz = base_url+"/quiz/";
    public static String GetSingleQuiz = base_url+"/quiz/{quizId}";
    public static String updateQuiz = base_url+"/quiz/{quizId}";

    //Leaderboard url
    public static String leaderboard = base_url+"/leader-board/web";

    //get institutions url
    public static String getInstitutions = base_url+"/institution/";

    //raffle url
    public static String createRaffle = base_url+"/raffle";
    public static String getRaffleStatus = base_url+"/raffle";
    public static String getRaffleParticipantsDetails = base_url+"/raffle/participants";
    public static String getAllRaffle = base_url+"/raffle";
    public static String getAllRaffleWinners = base_url+"/raffle/winners/";

    //mobile
    public static String verifyOTP = base_url+"/auth/verify-otp";


    //Organizations
    public static String getAllOrganizations = base_url+"/organisation";
    public static String createOrganizations = base_url+"/organisation";
    public static String updateOrganizations = base_url+"/organisation/{organization_id}";


    // Countries
    public static String getAllCountries = base_url + "/country";
    public static String createCountry = base_url + "/country";
    public static String deleteCountry = base_url+"/country/{country_id}";
    public static String getSingleCountry = base_url +"/country/{country_id}";

    //Institutions
    public static String getAllInstitutions = base_url +"/institution";
    public static String createInstitution = base_url +"/institution";
    public static String deleteInstitution = base_url + "/institution/{institution_id}";
    public static String updateInstitution = base_url + "/institution/{institution_id}";
    public static String getSingleInstitution = base_url + "/institution/{institution_id}";









}
