package Payload;

public class CreateQuestionBankPayload {
    private String name;
    private String stack;
    private String framework;
    private String filePath;

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
        return filePath;
    }

    public void setImage(String image) {
        this.filePath = image;
    }


    // Constructor
    public CreateQuestionBankPayload(String name, String stack, String framework, String image) {
        this.name = name;
        this.stack = stack;
        this.framework = framework;
        this.filePath = image;
    }
}
