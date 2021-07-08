package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteFeedbackDAO implements IFeedbackDAO{

    private static final List<FeedbackModel> EMPTY = new ArrayList<>();
    private Connection connection;

    @Override
    public void setup() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS feedbacks ("
                + "	id integer PRIMARY KEY,"
                + "	exam_id integer NOT NULL,"
                + "	user_id integer NOT NULL,"
                + " feedback text NOT NULL,"
                + "	given integer NOT NULL,"
                + "	read integer NOT NULL,"
                + "	FOREIGN KEY ('user_id') REFERENCES 'users'('id') ON DELETE CASCADE,"
                + "	FOREIGN KEY ('exam_id') REFERENCES 'exam'('id') ON DELETE CASCADE"
                + ");";

        String databaseURL = "jdbc:sqlite:ExamSystem.sqlite";
        try (Connection conn = DriverManager.getConnection(databaseURL); Statement statement = conn.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void connect() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public boolean insertFeedback(FeedbackModel feedbackModel) {
        try{
            String sql = "INSERT INTO feedbacks (exam_id, user_id, feedback, given, read) VALUES('"+feedbackModel.getExamID()+"','"+feedbackModel.getUserID()+"','"+feedbackModel.getFeedback()+"','"+feedbackModel.getFeedbackGiven()+"','"+feedbackModel.getFeedbackRead()+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateFeedback(FeedbackModel feedbackModel) throws SQLException {
        String sql = "UPDATE feedbacks SET exam_id = ? , user_id = ? , feedback = ? , given = ? , read = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, feedbackModel.getExamID());
            preparedStatement.setInt(2, feedbackModel.getUserID());
            preparedStatement.setString(3, feedbackModel.getFeedback());
            preparedStatement.setInt(4, feedbackModel.getFeedbackGiven());
            preparedStatement.setInt(5, feedbackModel.getFeedbackRead());
            preparedStatement.setInt(6, feedbackModel.getUniqueID());

            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteFeedback(FeedbackModel feedbackModel) {
        String sql = "DELETE FROM feedbacks WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, feedbackModel.getUniqueID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    @Override
    public List<FeedbackModel> findAllAnswers() {
        return null;
    }
}
