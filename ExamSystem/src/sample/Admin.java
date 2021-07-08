package sample;

import java.sql.SQLException;
import java.util.List;

public class Admin {

    private final IUserDAO iUserDAO;
    private final IClassRoomDAO iClassRoomDAO;

    public Admin(IUserDAO iUserDAO, IClassRoomDAO iClassRoomDAO) throws Exception {
        this.iUserDAO = iUserDAO;
        this.iClassRoomDAO = iClassRoomDAO;
        iUserDAO.connect();
        iClassRoomDAO.connect();
    }

    public void addNewUser(String name, String username, String password, int userRole){
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setUserRole(userRole);

        iUserDAO.insertUser(userModel);
    }

    public boolean updateUser(UserModel userModel) throws SQLException {
        return iUserDAO.updateUser(userModel);
    }

    public List<UserModel> search(Types.UserSearchType userSearchType, String value){
        return iUserDAO.findUserByProperty(userSearchType, value);
    }

    public List<UserModel> findAll(){
        return iUserDAO.findAll();
    }

    public void addNewClassRoom(String name, int level, String division){
        ClassRoomModel classRoomModel = new ClassRoomModel();
        classRoomModel.setName(name);
        classRoomModel.setLevel(level);
        classRoomModel.setDivision(division);
        iClassRoomDAO.insertClassRoom(classRoomModel);
    }
    public boolean updateClassroom(ClassRoomModel classRoomModel) throws SQLException {
        return iClassRoomDAO.updateClassRoom(classRoomModel);
    }

    public List<ClassRoomModel> searchClassroom(Types.ClassroomSearchType classroomSearchType, String value){
        return iClassRoomDAO.findClassroomByProperty(classroomSearchType, value);
    }

    public List<ClassRoomModel> findAllClassrooms(){
        return iClassRoomDAO.findAll();
    }

    public boolean assignClass(int classID, int studentID){
        return iClassRoomDAO.assignClassroom(classID, studentID);
    }

    public UserModel userSignIn(String username, String password, int userType){
        return iUserDAO.login(username, password, userType);
    }

    public void close() throws Exception {
        iUserDAO.close();
    }
}
