package sample;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO extends IDAO{
    boolean insertUser(UserModel userModel);
    boolean updateUser(UserModel userModel) throws SQLException;
    boolean deleteUser(UserModel userModel);

    List<UserModel> findUserByProperty(Types.UserSearchType userSearchType, Object value);
    List<UserModel> findAll();

    UserModel login(String username, String password, int userType);
}
