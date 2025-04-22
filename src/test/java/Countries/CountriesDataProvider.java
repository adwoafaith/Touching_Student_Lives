package Countries;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

public class CountriesDataProvider {

        private static final ObjectMapper mapper = new ObjectMapper();

        @DataProvider(name = "countryPayloadProvider")
        public Object[][] userPayloadProvider() {
            return new Object[][] {
                    {
                            convertToJson(new CountryPayload(
                                    "Automation Country",
                                    "AC"

                            ))
                    },

            };
        }

        // Utility method to convert payload to JSON string
        private static String convertToJson(CountryPayload countryPayload) {
            try {
                return mapper.writeValueAsString(countryPayload);
            } catch (Exception e) {
                throw new RuntimeException("Error while converting object to JSON: " + e.getMessage(), e);
            }
        }
    }

