package sample;

import java.sql.SQLException;
import java.util.List;

public interface IAnswerDAO extends IDAO {
    boolean insertAnswer(AnswerModel answerModel);
    boolean updateAnswer(AnswerModel answerModel) throws SQLException;

//    List<ExamModel> findExamByProperty(Types.ExamSearchType examSearchType, Object value);
    List<AnswerModel> findAllAnswers();
}
