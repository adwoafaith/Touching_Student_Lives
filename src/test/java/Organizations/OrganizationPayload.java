package Organizations;

public class OrganizationPayload {
    private String email;
    private String name;
    private String address;
    private String contact;

    // Constructors
    public OrganizationPayload() {
        // Default constructor needed for Jackson/Gson
    }

    public OrganizationPayload(String email, String name, String address, String contact) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
