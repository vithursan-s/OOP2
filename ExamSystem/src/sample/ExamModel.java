package sample;

public class ExamModel {
    private int uniqueID;
    private String name;
    private String createdBy;
    private String activity;
    private int questionAmount;
    private int login;
    private int passcode;

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(int questionAmount) {
        this.questionAmount = questionAmount;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getPasscode() {
        return passcode;
    }

    public void setPasscode(int passcode) {
        this.passcode = passcode;
    }

    @Override
    public String toString() {
        return "ExamModel{" +
                "uniqueID=" + uniqueID +
                ", name='" + name + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", activity='" + activity + '\'' +
                ", questionAmount=" + questionAmount +
                ", login=" + login +
                ", passcode=" + passcode +
                '}';
    }
}
