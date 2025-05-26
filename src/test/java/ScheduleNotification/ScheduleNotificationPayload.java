package ScheduleNotification;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static NotificationType.NotificationTypeTest.notificationTypeId;

public class ScheduleNotificationPayload {

    public static Map<String, Object> validNotificationPayload() {

        ZonedDateTime futureTime = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60);
        String futureTimeString = futureTime.format(DateTimeFormatter.ISO_INSTANT);

        Map<String, Object> payload = new HashMap<>();

        payload.put("notification_type_id", notificationTypeId);
        payload.put("title","Automation notification");
        payload.put("body", "Our system will be undergoing maintenance on May 15th from 2:00 AM to 4:00 AM UTC. Some services may be temporarily unavailable during this time.");

        // Nested 'data' field
        Map<String, Object> data = new HashMap<>();
        data.put("maintenanceId", "maint-2025-05-15");
        data.put("startTime", "2025-05-15T02:00:00Z");
        data.put("endTime", "2025-05-15T04:00:00Z");
        data.put("affectedServices", Arrays.asList("user-portal", "mobile-app"));
        data.put("contactEmail", "etesting584@gmail.com");
        data.put("priority", "high");
        data.put("actionUrl", "https://example.com/maintenance-details");
        payload.put("data", data);

        // List of channels
        payload.put("channels", Arrays.asList("email", "push", "in_app"));
        Map<String, Object> filters = new HashMap<>();
        filters.put("countries", Collections.singletonList("79adbf9e-af00-4eba-9080-227d7dc87c06"));
        filters.put("institutions", Collections.singletonList("dc9e2f57-268b-4108-9862-f6cb9948b16e"));

        Map<String, Object> targetAudience = new HashMap<>();
        targetAudience.put("roles", Arrays.asList("student", "student_admin"));
        targetAudience.put("filters", filters);
        payload.put("target_audience", targetAudience);

        payload.put("scheduled_for", futureTimeString);

        return payload;
    }
}
