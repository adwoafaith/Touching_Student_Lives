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

        EventPayload.Notifications notifications = new EventPayload.Notifications(
                Collections.singletonList("email"),
                Collections.singletonList("student_admin")
        );

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
                "true",
                "true",
                notifications
        );

        return new Object[][] {{ (payload) }};
    }



}
