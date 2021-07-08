package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {

    private static final List<UserModel> EMPTY = new ArrayList<>();
    private Connection connection;

    @Override
    public void setup() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "	id integer PRIMARY KEY,"
                + "	name text NOT NULL,"
                + "	username text NOT NULL,"
                + "	password text NOT NULL,"
                + "	role integer NOT NULL"
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
    public boolean insertUser(UserModel userModel) {
        try{
            String sql = "INSERT INTO users (name, username, password, role) VALUES('"+userModel.getName()+"','"+userModel.getUsername()+"','"+userModel.getPassword()+"','"+userModel.getUserRole()+"')";
            connection = DriverManager.getConnection("jdbc:sqlite:ExamSystem.sqlite");
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateUser(UserModel userModel) throws SQLException {
        String sql = "UPDATE users SET name = ? , username = ? , password = ? , role = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userModel.getName());
            preparedStatement.setString(2, userModel.getUsername());
            preparedStatement.setString(3, userModel.getPassword());
            preparedStatement.setInt(4, userModel.getUserRole());
            preparedStatement.setInt(5, userModel.getUniqueID());

            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteUser(UserModel userModel) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userModel.getUniqueID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    @Override
    public List<UserModel> findUserByProperty(Types.UserSearchType userSearchType, Object value) {
        String whereClause = "";
        String valueClause = "";
        switch (userSearchType) {
            case ID -> {
                whereClause = "id = ";
                valueClause = value.toString();
            }
            case NAME -> {
                whereClause = "name LIKE ";
                valueClause = "%" + value.toString() + "%";
            }
            case USERNAME -> {
                whereClause = "username LIKE ";
                valueClause = "%" + value.toString() + "%";
            }
            case ROLE -> {
                whereClause = "role = ";
                valueClause = value.toString();
            }
            default -> System.out.println("Unknown search type.");
        }

        String sql = "SELECT * FROM users WHERE "+whereClause+"'"+valueClause+"'";
        return getUserModelList(sql);
    }

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        return getUserModelList(sql);
    }

    @Override
    public UserModel login(String username, String password, int userType) {
        String sql = "SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"' AND role = "+userType+"";
//        List<UserModel> userModels = getUserModelList(sql);
//        if (userModels.isEmpty()) {
//            return null;
//        }else{
//            return userModels.get(0);
//        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<UserModel> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserModel userModel = new UserModel();
                userModel.setUniqueID(resultSet.getInt(1));
                userModel.setName(resultSet.getString(2));
                userModel.setUsername(resultSet.getString(3));
                userModel.setPassword(resultSet.getString(4));
                userModel.setUserRole(resultSet.getInt(5));
                users.add(userModel);
            }
            return users.get(0);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    private List<UserModel> getUserModelList(String sql) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<UserModel> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserModel userModel = new UserModel();
                userModel.setUniqueID(resultSet.getInt(1));
                userModel.setName(resultSet.getString(2));
                userModel.setUsername(resultSet.getString(3));
                userModel.setPassword(resultSet.getString(4));
                userModel.setUserRole(resultSet.getInt(5));
                users.add(userModel);
            }
            return users;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return EMPTY;
    }
}
