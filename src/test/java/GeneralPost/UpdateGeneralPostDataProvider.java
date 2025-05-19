package GeneralPost;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class UpdateGeneralPostDataProvider {

    @DataProvider(name = "updatePostDataProvider")
    public static Object[][] provideUpdatedPostData() {

        Map<String, String> updatedData1 = new HashMap<>();
        updatedData1.put("title", "Updated Title - Automated Testing");
        updatedData1.put("description", "Updated description for the test automation opportunity.");
        updatedData1.put("status", "active");

        return new Object[][]{{updatedData1}};

    }
}
