package Organizations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

public class OrganizationDataProvider {

    private static final ObjectMapper mapper = new ObjectMapper(); // Jackson ObjectMapper for JSON conversion

    @DataProvider(name = "organizationPayloadProvider")
    public Object[][] userPayloadProvider() {
        return new Object[][] {
                {
                        convertToJson(new OrganizationPayload(
                                "authomation@gmail.com",
                                "Authomation Admin",
                                "Address office Kumasi",
                                "0233333999"
                        ))
                },

        };
    }

    // Utility method to convert payload to JSON string
    private static String convertToJson(OrganizationPayload organizationPayload) {
        try {
            return mapper.writeValueAsString(organizationPayload);
        } catch (Exception e) {
            throw new RuntimeException("Error while converting object to JSON: " + e.getMessage(), e);
        }
    }
}
