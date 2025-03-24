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


    //MCQS url
    public static String createQuestionBank = base_url+"v1/question-bank";
    public static String getAllQuestionBanks = base_url+"v1/question-bank";
    public static String getSingleQuestionBank = base_url+"v1/question-bank/{questionbank_id}";
    public static String EditQuestionBank = base_url+"v1/question-bank/{questionbank_id}";
    public static String deleteQuestionBank = base_url+"v1/question-bank/{questionbank_id}";
    public static String updateQuestionBank = base_url+"v1/question-bank/{questionbank_id}";





}
