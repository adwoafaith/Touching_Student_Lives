package Events;

import org.testng.annotations.DataProvider;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EventDataProvider {

    @DataProvider(name = "eventDataProvider")
    public static Object[][] provideEventData() {
        LocalDate futureDate = LocalDate.now().plusDays(10);
        LocalDate endDate = futureDate.plusDays(2);
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = startTime.plusHours(3);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        EventPayload payload = new EventPayload(
                "Tech Conference 2025",
                "A global tech meetup for innovators.",
                futureDate.format(dateFormatter),
                endDate.format(dateFormatter),
                startTime.format(timeFormatter),
                endTime.format(timeFormatter),
                "https://virtualevent.com/tech2025",
                Collections.emptyList(),
                Collections.emptyList(),
                "1742285372304.jpeg",
                "active",
                true
        );

        return new Object[][] {{ convertToFormData(payload) }};
    }

    private static Map<String, Object> convertToFormData(EventPayload payload) {
        Map<String, Object> formData = new HashMap<>();

        // Text fields
        formData.put("title", payload.getTitle());
        formData.put("description", payload.getDescription());
        formData.put("startDate", payload.getStartDate());
        formData.put("endDate", payload.getEndDate());
        formData.put("startTime", payload.getStartTime());
        formData.put("endTime", payload.getEndTime());
        formData.put("virtualLink", payload.getVirtualLink());
        formData.put("status", payload.getStatus());
        formData.put("isGlobal", String.valueOf(payload.isGlobal()));

        // Array fields - send as comma-separated values
        formData.put("countries", "[]");
        formData.put("institutions", "[]");

        try {
            // Create a small test image file
            File tempFile = File.createTempFile("test-image", ".jpg");
            tempFile.deleteOnExit();

            // Write minimal JPEG data
            try (var out = new java.io.FileOutputStream(tempFile)) {
                out.write(new byte[] {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE0}); // JPEG header
            }

            System.out.println("Created temp image file at: " + tempFile.getAbsolutePath());
            formData.put("image", tempFile);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create test image", e);
        }



        return formData;
    }
}
