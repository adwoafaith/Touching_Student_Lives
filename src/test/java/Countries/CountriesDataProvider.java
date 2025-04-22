package Countries;

import Organizations.OrganizationPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

public class CountriesDataProvider {

    public class CountryDataProvider {

        private static final ObjectMapper mapper = new ObjectMapper(); // Jackson ObjectMapper for JSON conversion


        // Utility method to convert payload to JSON string
        private static String convertToJson(OrganizationPayload organizationPayload) {
            try {
                return mapper.writeValueAsString(organizationPayload);
            } catch (Exception e) {
                throw new RuntimeException("Error while converting object to JSON: " + e.getMessage(), e);
            }
        }
    }

}
