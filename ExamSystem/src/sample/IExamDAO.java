package sample;

import java.sql.SQLException;
import java.util.List;

public interface IExamDAO extends IDAO{
    boolean insertExam(ExamModel examModel);
    boolean updateExam(ExamModel examModel) throws SQLException;
    boolean deleteExam(ExamModel examModel);

    List<ExamModel> findExamByProperty(Types.ExamSearchType examSearchType, Object value);
    List<ExamModel> findAllExams(String createdBy);

    boolean assignExamToClass(int examID, int classID);

    //Find all questions by exam id?
}
