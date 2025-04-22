package Institution;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import static Countries.CountriesTest.countryId;

public class InstitutionDataProvider {

        private static final ObjectMapper mapper = new ObjectMapper();

        @DataProvider(name = "institutionPayloadProvider")
        public Object[][] userPayloadProvider() {
            return new Object[][] {
                    {
                            convertToJson(new InstitutionPayload(
                                    "Automation University",
                                    countryId,
                                    "kigali",
                                    "Amalitech",
                                    "Takoradi",
                                    "Takoradi city"

                            ))
                    },

            };
        }

        // Utility method to convert payload to JSON string
        private static String convertToJson(InstitutionPayload institutionPayload) {
            try {
                return mapper.writeValueAsString(institutionPayload);
            } catch (Exception e) {
                throw new RuntimeException("Error while converting object to JSON: " + e.getMessage(), e);
            }
        }
    }


