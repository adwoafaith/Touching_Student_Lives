package Institution;

public class InstitutionPayload {
    private String name;

    public InstitutionPayload(String name, String country_id, String location, String address, String city, String state) {
        this.name = name;
        this.country_id = country_id;
        this.location = location;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    private String country_id;
    private String location;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String city;
    private String state;
}
