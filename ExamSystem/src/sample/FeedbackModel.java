package sample;

public class FeedbackModel {
    private int uniqueID;
    private int examID;
    private int userID;
    private String feedback;
    private int feedbackGiven;
    private int feedbackRead;

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getFeedbackGiven() {
        return feedbackGiven;
    }

    public void setFeedbackGiven(int feedbackGiven) {
        this.feedbackGiven = feedbackGiven;
    }

    public int getFeedbackRead() {
        return feedbackRead;
    }

    public void setFeedbackRead(int feedbackRead) {
        this.feedbackRead = feedbackRead;
    }
}
