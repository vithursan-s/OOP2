package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteClassRoomDAO implements IClassRoomDAO{

    private static final List<ClassRoomModel> EMPTY = new ArrayList<>();
    private Connection connection;

    @Override
    public void setup() {
        String sql = "CREATE TABLE IF NOT EXISTS classroom ("
                + "	id integer PRIMARY KEY,"
                + "	name text NOT NULL,"
                + "	level integer NOT NULL,"
                + "	division text NOT NULL"
                + ");";

        String sqlLinkTbl = "CREATE TABLE IF NOT EXISTS classroom_student ("
                + "	id integer PRIMARY KEY,"
                + "	classroom_id integer NOT NULL,"
                + "	user_id integer NOT NULL,"
                + "	FOREIGN KEY ('user_id') REFERENCES 'users'('id') ON DELETE CASCADE,"
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
    public boolean insertClassRoom(ClassRoomModel classRoomModel) {
        try{
            String sql = "INSERT INTO classroom (name, level, division) VALUES('"+classRoomModel.getName()+"','"+classRoomModel.getLevel()+"','"+classRoomModel.getDivision()+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateClassRoom(ClassRoomModel classRoomModel) throws SQLException {
        String sql = "UPDATE classroom SET name = ? , level = ? , division = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, classRoomModel.getName());
            preparedStatement.setInt(2, classRoomModel.getLevel());
            preparedStatement.setString(3, classRoomModel.getDivision());
            preparedStatement.setInt(4, classRoomModel.getUniqueID());

            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteClassRoom(ClassRoomModel classRoomModel) {
        String sql = "DELETE FROM classroom WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, classRoomModel.getUniqueID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean assignClassroom(int classID, int studentID) {
        try{
            String sql = "INSERT INTO classroom_student (classroom_id, user_id) VALUES('"+classID+"','"+studentID+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<ClassRoomModel> findClassroomByProperty(Types.ClassroomSearchType classroomSearchType, Object value) {
        String whereClause = "";
        String valueClause = "";
        switch (classroomSearchType) {
            case NAME -> {
                whereClause = "name LIKE ";
                valueClause = "%" + value.toString() + "%";
            }
            case LEVEL -> {
                whereClause = "level = ";
                valueClause = value.toString();
            }
            case DIVISION -> {
                whereClause = "division LIKE ";
                valueClause = "%" + value.toString() + "%";
            }
        }

        String sql = "SELECT * FROM classroom WHERE "+whereClause+"'"+valueClause+"'";
        return getUserModelList(sql);
    }

    @Override
    public List<ClassRoomModel> findAll() {
        String sql = "SELECT * FROM classroom";
        return getUserModelList(sql);
    }

    private List<ClassRoomModel> getUserModelList(String sql) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<ClassRoomModel> classRoomModelList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ClassRoomModel classRoomModel = new ClassRoomModel();
                classRoomModel.setUniqueID(resultSet.getInt(1));
                classRoomModel.setName(resultSet.getString(2));
                classRoomModel.setLevel(resultSet.getInt(3));
                classRoomModel.setDivision(resultSet.getString(4));
                classRoomModelList.add(classRoomModel);
            }
            return classRoomModelList;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return EMPTY;
    }
}
