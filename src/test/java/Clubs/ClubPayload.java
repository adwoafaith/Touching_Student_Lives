package Clubs;

public class ClubPayload {
    private String name;
    private String description;
    private String institution_id;
    private String country_id;
    private boolean is_active;
    private String club_logo_url;
    private String club_banner_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(String institution_id) {
        this.institution_id = institution_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getClub_logo_url() {
        return club_logo_url;
    }

    public void setClub_logo_url(String club_logo_url) {
        this.club_logo_url = club_logo_url;
    }

    public String getClub_banner_url() {
        return club_banner_url;
    }

    public void setClub_banner_url(String club_banner_url) {
        this.club_banner_url = club_banner_url;
    }
}
