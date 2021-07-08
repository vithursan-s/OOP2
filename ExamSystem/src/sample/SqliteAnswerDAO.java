package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteAnswerDAO implements IAnswerDAO{

    private static final List<AnswerModel> EMPTY = new ArrayList<>();
    private Connection connection;

    @Override
    public void setup() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS answers ("
                + "	id integer PRIMARY KEY,"
                + "	user_id integer NOT NULL,"
                + "	exam_id integer NOT NULL,"
                + "	question_id integer NOT NULL,"
                + " given_answer text NOT NULL,"
                + " marks text NOT NULL,"
                + "	is_marked integer NOT NULL,"
                + "	FOREIGN KEY ('user_id') REFERENCES 'users'('id') ON DELETE CASCADE,"
                + "	FOREIGN KEY ('exam_id') REFERENCES 'exam'('id') ON DELETE CASCADE,"
                + "	FOREIGN KEY ('question_id') REFERENCES 'questions'('id') ON DELETE CASCADE"
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
    public boolean insertAnswer(AnswerModel answerModel) {
        try{
            String sql = "INSERT INTO answers (user_id, exam_id, question_id, given_answer, marks, is_marked) VALUES('"+answerModel.getUserID()+"','"+answerModel.getExamID()+"','"+answerModel.getQuestionID()+"','"+answerModel.getGivenAnswer()+"','"+answerModel.getMarks()+"','"+answerModel.getIsMarked()+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateAnswer(AnswerModel answerModel) throws SQLException {
        String sql = "UPDATE answers SET user_id = ? , exam_id = ? , question_id = ? , given_answer = ? , marks = ? , is_marked = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, answerModel.getUserID());
            preparedStatement.setInt(2, answerModel.getExamID());
            preparedStatement.setInt(3, answerModel.getQuestionID());
            preparedStatement.setString(4, answerModel.getGivenAnswer());
            preparedStatement.setInt(5, answerModel.getMarks());
            preparedStatement.setInt(6, answerModel.getIsMarked());
            preparedStatement.setInt(6, answerModel.getUniqueID());

            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public List<AnswerModel> findAllAnswers() {
        return null;
    }
}
