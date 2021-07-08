package sample;

import java.sql.SQLException;
import java.util.List;

public interface IClassRoomDAO extends IDAO{
    boolean insertClassRoom(ClassRoomModel classRoomModel);
    boolean updateClassRoom(ClassRoomModel classRoomModel) throws SQLException;
    boolean deleteClassRoom(ClassRoomModel classRoomModel);

    boolean assignClassroom(int classID, int studentID);

    List<ClassRoomModel> findClassroomByProperty(Types.ClassroomSearchType classroomSearchType, Object value);
    List<ClassRoomModel> findAll();
}
