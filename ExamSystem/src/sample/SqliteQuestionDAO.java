package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteQuestionDAO implements IQuestionDAO{

    private static final List<QuestionModel> EMPTY = new ArrayList<>();
    private Connection connection;

    @Override
    public void setup() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS questions ("
                + "	id integer PRIMARY KEY,"
                + "	type text NOT NULL,"
                + " question text NOT NULL,"
                + " option1 text NOT NULL,"
                + " option2 text NOT NULL,"
                + " option3 text NOT NULL,"
                + " option4 text NOT NULL,"
                + " answer text NOT NULL,"
                + "	marks integer NOT NULL,"
                + "	exam_id integer NOT NULL,"
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
    public boolean insertQuestion(QuestionModel questionModel) {
        try{
            String sql = "INSERT INTO questions (type, question, option1, option2 , option3, option4, answer, marks, exam_id) VALUES('"+questionModel.getType()+"','"+questionModel.getQuestion()+"','"+questionModel.getOption1()+"','"+questionModel.getOption2()+"','"+questionModel.getOption3()+"','"+questionModel.getOption4()+"','"+questionModel.getAnswer()+"','"+questionModel.getMarks()+"','"+questionModel.getExamID()+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateQuestion(QuestionModel questionModel) throws SQLException {
        String sql = "UPDATE questions SET type = ? , question = ? , option1 = ? , option2 = ? , option3 = ? , option4 = ?, answer = ?, marks = ?, exam_id = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, questionModel.getType());
            preparedStatement.setString(2, questionModel.getQuestion());
            preparedStatement.setString(3, questionModel.getOption1());
            preparedStatement.setString(4, questionModel.getOption2());
            preparedStatement.setString(5, questionModel.getOption3());
            preparedStatement.setString(6, questionModel.getOption4());
            preparedStatement.setString(7, questionModel.getAnswer());
            preparedStatement.setInt(8, questionModel.getMarks());
            preparedStatement.setInt(9, questionModel.getExamID());

            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteQuestion(QuestionModel questionModel) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, questionModel.getUniqueID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    @Override
    public List<QuestionModel> findQuestionsByProperty(Types.QuestionSearchType questionSearchType, Object value) {
        return null;
    }

    @Override
    public List<QuestionModel> findAllQuestions(int examID) {
        String sql = "SELECT * FROM questions WHERE id = '"+examID+"'";
        return getQuestionModelList(sql);
    }

    private List<QuestionModel> getQuestionModelList(String sql) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<QuestionModel> questionModelList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                QuestionModel questionModel = new QuestionModel();
                questionModel.setUniqueID(resultSet.getInt(1));
                questionModel.setType(resultSet.getString(2));
                questionModel.setQuestion(resultSet.getString(3));
                questionModel.setOption1(resultSet.getString(4));
                questionModel.setOption2(resultSet.getString(5));
                questionModel.setOption3(resultSet.getString(6));
                questionModel.setOption4(resultSet.getString(7));
                questionModel.setAnswer(resultSet.getString(8));
                questionModel.setMarks(resultSet.getInt(9));
                questionModel.setExamID(resultSet.getInt(10));
                questionModelList.add(questionModel);
            }
            return questionModelList;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return EMPTY;
    }
}
