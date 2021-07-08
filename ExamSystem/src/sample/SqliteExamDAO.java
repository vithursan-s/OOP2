package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteExamDAO implements IExamDAO{

    private static final List<ExamModel> EMPTY = new ArrayList<>();
    private Connection connection;

    @Override
    public void setup() {
        String sql = "CREATE TABLE IF NOT EXISTS exam ("
                + "	id integer PRIMARY KEY,"
                + "	name text NOT NULL,"
                + " created_by text NOT NULL,"
                + " activity text NOT NULL,"
                + "	question_amount integer NOT NULL,"
                + "	login integer NOT NULL,"
                + "	passcode integer NOT NULL"
                + ");";

        String sqlLinkTbl = "CREATE TABLE IF NOT EXISTS exam_classroom ("
                + "	id integer PRIMARY KEY,"
                + "	classroom_id integer NOT NULL,"
                + "	exam_id integer NOT NULL,"
                + "	FOREIGN KEY ('exam_id') REFERENCES 'exam'('id') ON DELETE CASCADE,"
                + "	FOREIGN KEY ('classroom_id') REFERENCES 'classroom'('id') ON DELETE CASCADE"
                + ");";

        String databaseURL = "jdbc:sqlite:ExamSystem.sqlite";
        try (Connection conn = DriverManager.getConnection(databaseURL); Statement statement = conn.createStatement()) {
            statement.execute(sql);
            statement.execute(sqlLinkTbl);
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
    public boolean insertExam(ExamModel examModel) {
        try{
            String sql = "INSERT INTO exam (name, created_by, activity, question_amount , login, passcode) VALUES('"+examModel.getName()+"','"+examModel.getCreatedBy()+"','"+examModel.getActivity()+"','"+examModel.getQuestionAmount()+"','"+examModel.getLogin()+"','"+examModel.getPasscode()+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateExam(ExamModel examModel) throws SQLException {
        String sql = "UPDATE exam SET name = ? , created_by = ? , activity = ? , question_amount = ? , login = ? , passcode = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, examModel.getName());
            preparedStatement.setString(2, examModel.getCreatedBy());
            preparedStatement.setString(3, examModel.getActivity());
            preparedStatement.setInt(4, examModel.getQuestionAmount());
            preparedStatement.setInt(5, examModel.getLogin());
            preparedStatement.setInt(6, examModel.getPasscode());
            preparedStatement.setInt(7, examModel.getUniqueID());

            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteExam(ExamModel examModel) {
        String sql = "DELETE FROM exam WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, examModel.getUniqueID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    @Override
    public List<ExamModel> findExamByProperty(Types.ExamSearchType examSearchType, Object value) {
        String whereClause = "";
        String valueClause = "";
        switch (examSearchType){

            case ID -> {
                whereClause = "id = ";
                valueClause = value.toString();
            }
            case NAME -> {
                whereClause = "name LIKE ";
                valueClause = "%" + value.toString() + "%";
            }
            case CREATEDBY -> {
                whereClause = "created_by LIKE ";
                valueClause = "%" + value.toString() + "%";
            }
            case ACTIVITY -> {
                whereClause = "activity LIKE ";
                valueClause = "%" + value.toString() + "%";
            }
            case LOGIN -> {
                whereClause = "login =";
                valueClause = value.toString();
            }
        }

        String sql = "SELECT * FROM classroom WHERE "+whereClause+"'"+valueClause+"'";
        return getExamModelList(sql);
    }

    @Override
    public List<ExamModel> findAllExams(String createdBy) {
        String valueClause = "%" + createdBy + "%";
        String sql = "SELECT * FROM exam WHERE created_by LIKE '"+valueClause+"'";
        return getExamModelList(sql);
    }

    private List<ExamModel> getExamModelList(String sql) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<ExamModel> examModelList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ExamModel examModel = new ExamModel();
                examModel.setUniqueID(resultSet.getInt(1));
                examModel.setName(resultSet.getString(2));
                examModel.setCreatedBy(resultSet.getString(3));
                examModel.setActivity(resultSet.getString(4));
                examModel.setQuestionAmount(resultSet.getInt(5));
                examModel.setLogin(resultSet.getInt(6));
                examModel.setPasscode(resultSet.getInt(7));
                examModelList.add(examModel);
            }
            return examModelList;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public boolean assignExamToClass(int examID, int classID) {
        try{
            String sql = "INSERT INTO exam_classroom (exam_id, classroom_id) VALUES('"+examID+"','"+classID+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
