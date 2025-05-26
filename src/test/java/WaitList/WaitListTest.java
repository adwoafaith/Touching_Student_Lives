package WaitList;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WaitListTest {

    public static String email;
    public static String authToken;
    public static String waitListId;

    private void loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            email = properties.getProperty("mobileEmailNew_user");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    }

    @BeforeClass
    public void setUpData() {
        try {
            loadConfig();
        } catch (Exception e) {
            System.err.println("Setup failed in WaitListTest: " + e.getMessage());
            e.printStackTrace(); // This shows the full stack trace in your console
            throw new RuntimeException("Setup failed: " + e.getMessage());
        }
    }


    @Test(priority = 1)
    public void getWaitListTest() {
        Response response = WaitListEndpoint.getWaitList();
        response.then().log().body();

        List<Map<String, Object>> users = response.jsonPath().getList("data");
        boolean isEmailFound = false;

        for (Map<String, Object> user : users) {
            String dataEmail = (String) user.get("email");

            if (dataEmail.equalsIgnoreCase(email)) {
                isEmailFound = true;

                waitListId = (String) user.get("id");
                String role = (String) user.get("role");
                String state = (String) user.get("state");
                Boolean deleted = (Boolean) user.get("deleted");
                Object deletedAt = user.get("deleted_at");
                Object profilePicUrl = user.get("profile_pic_url");

                // Assertions
                Assert.assertNotNull(waitListId,"Id is null");
                Assert.assertEquals(state, "pending", "State is not pending");
                Assert.assertEquals(role, "student", "Role is not student");
                Assert.assertFalse(deleted, "User should not be marked as deleted");
                Assert.assertNull(deletedAt, "deleted_at should be null");
                Assert.assertNull(profilePicUrl, "profile_pic_url should be null");

                break;
            }
        }

        Assert.assertTrue(isEmailFound, "Email " + email + " not found in waitlist response.");
    }
}
