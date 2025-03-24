package Payload;

public class CreateQuestionBankPayload {
    private String name;
    private String stack;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String framework;
    private String image;

    // Constructor
    public CreateQuestionBankPayload(String name, String stack, String framework, String image) {
        this.name = name;
        this.stack = stack;
        this.framework = framework;
        this.image = image;
    }
}
