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
        private String image;
        private String status;
        private boolean isGlobal;

        public EventPayload(String title, String description, String startDate, String endDate, String startTime, String endTime,
                            String virtualLink, List<String> countries, List<String> institutions,
                            String image, String status, boolean isGlobal) {
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
            this.startTime = startTime;
            this.endTime = endTime;
            this.virtualLink = virtualLink;
            this.countries = countries;
            this.institutions = institutions;
            this.image = image;
            this.status = status;
            this.isGlobal = isGlobal;
        }

        // Getters and Setters
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

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public boolean isGlobal() { return isGlobal; }
        public void setGlobal(boolean global) { isGlobal = global; }
    }

