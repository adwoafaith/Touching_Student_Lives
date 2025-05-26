package Events;

import java.util.List;

public class EventPayload {
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String virtualLink;
    private List<String> countries;
    private List<String> institutions;
    private String imageUrl;              // updated field
    private String status;
    private String isGlobal;              // changed from boolean to String
    private String notify_users;          // new field
    private Notifications notifications;  // new nested object

    public static class Notifications {
        private List<String> deliveryType;
        private List<String> recipients;

        public Notifications(List<String> deliveryType, List<String> recipients) {
            this.deliveryType = deliveryType;
            this.recipients = recipients;
        }

        public List<String> getDeliveryType() { return deliveryType; }
        public void setDeliveryType(List<String> deliveryType) { this.deliveryType = deliveryType; }

        public List<String> getRecipients() { return recipients; }
        public void setRecipients(List<String> recipients) { this.recipients = recipients; }
    }

    public EventPayload(String title, String description, String startDate, String endDate, String startTime, String endTime,
                        String virtualLink, List<String> countries, List<String> institutions,
                        String imageUrl, String status, String isGlobal, String notify_users,
                        Notifications notifications) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.virtualLink = virtualLink;
        this.countries = countries;
        this.institutions = institutions;
        this.imageUrl = imageUrl;
        this.status = status;
        this.isGlobal = isGlobal;
        this.notify_users = notify_users;
        this.notifications = notifications;
    }

    // Getters and Setters for all fields
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getVirtualLink() { return virtualLink; }
    public void setVirtualLink(String virtualLink) { this.virtualLink = virtualLink; }

    public List<String> getCountries() { return countries; }
    public void setCountries(List<String> countries) { this.countries = countries; }

    public List<String> getInstitutions() { return institutions; }
    public void setInstitutions(List<String> institutions) { this.institutions = institutions; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getIsGlobal() { return isGlobal; }
    public void setIsGlobal(String isGlobal) { this.isGlobal = isGlobal; }

    public String getNotify_users() { return notify_users; }
    public void setNotify_users(String notify_users) { this.notify_users = notify_users; }

    public Notifications getNotifications() { return notifications; }
    public void setNotifications(Notifications notifications) { this.notifications = notifications; }
}
