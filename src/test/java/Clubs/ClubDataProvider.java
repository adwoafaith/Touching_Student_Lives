package Clubs;


import static Clubs.ClubTest.countryId;
import static Clubs.ClubTest.institutionId;

public class ClubDataProvider {
    @org.testng.annotations.DataProvider(name = "clubDataProvider")
    public Object[][] provideClubData() {
        ClubPayload clubPayload = new ClubPayload();
        clubPayload.setName("Automation Club");
        clubPayload.setDescription("A test club created using RestAssured");
        clubPayload.setInstitution_id(institutionId);  // Use extracted institution ID
        clubPayload.setCountry_id(countryId);  // Use extracted country ID
        clubPayload.setIs_active(true);
        clubPayload.setClub_logo_url("https://example.com/logo.png");
        clubPayload.setClub_banner_url("https://example.com/banner.png");

        return new Object[][]{{clubPayload}};
    }
}
