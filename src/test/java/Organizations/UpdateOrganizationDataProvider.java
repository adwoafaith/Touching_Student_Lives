package Organizations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

public class UpdateOrganizationDataProvider {

    private static final ObjectMapper mapper = new ObjectMapper(); // Jackson ObjectMapper for JSON conversion

    @DataProvider(name = "updatedOrganizationPayloadProvider")
    public Object[][] userPayloadProvider() {
        return new Object[][] {
                {
                        convertToJson(new OrganizationPayload(
                                "authomation@gmail.com",
                                " Updated Authomation Admin",
                                "Address office Kumasi",
                                "0233333999"
                        ))
                },
                {
                        convertToJson(new OrganizationPayload(
                                "another.user@example.com",
                                " Updated Automation Admin2",
                                "Somewhere in Accra",
                                "0541234567"
                        ))
                },
                {
                        convertToJson(new OrganizationPayload(
                                "testy.test@test.com",
                                " Updated Automation Admin3",
                                "Hidden Leaf Village",
                                "0200000000"
                        ))
                }
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
