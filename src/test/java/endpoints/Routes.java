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



}
