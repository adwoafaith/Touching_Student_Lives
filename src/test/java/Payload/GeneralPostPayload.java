package Payload;

public class GeneralPostPayload {
    public String title;
    public String description;
    public String image;
    public String status;

    public GeneralPostPayload(String title, String description, String image, String status) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
