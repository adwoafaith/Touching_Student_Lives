package Raffle;



import java.util.List;

public class RafflePayload {
    private String name;

    private String startDate;

    private String endDate;

    private int maxParticipants;

    private int totalWinners;

    private int ticketPrice;

    private List<Integer> institutionId;

    private List<String> degree;

    private List<Integer> level;

    private List<String> programme;

    private boolean scheduled;

    private String status;

    private String description;

    // Constructor
    public RafflePayload(String name, String startDate, String endDate, int maxParticipants,
                         int totalWinners, int ticketPrice, List<Integer> institutionId,
                         List<String> degree, List<Integer> level, List<String> programme,
                         boolean scheduled, String status, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxParticipants = maxParticipants;
        this.totalWinners = totalWinners;
        this.ticketPrice = ticketPrice;
        this.institutionId = institutionId;
        this.degree = degree;
        this.level = level;
        this.programme = programme;
        this.scheduled = scheduled;
        this.status = status;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getTotalWinners() {
        return totalWinners;
    }

    public void setTotalWinners(int totalWinners) {
        this.totalWinners = totalWinners;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<Integer> getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(List<Integer> institutionId) {
        this.institutionId = institutionId;
    }

    public List<String> getDegree() {
        return degree;
    }

    public void setDegree(List<String> degree) {
        this.degree = degree;
    }

    public List<Integer> getLevel() {
        return level;
    }

    public void setLevel(List<Integer> level) {
        this.level = level;
    }

    public List<String> getProgramme() {
        return programme;
    }

    public void setProgramme(List<String> programme) {
        this.programme = programme;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

